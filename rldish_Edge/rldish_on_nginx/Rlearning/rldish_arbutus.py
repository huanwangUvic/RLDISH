import subprocess
import threading
#from discount_ucb import DiscountedUCB
import re
import numpy as np
import time
import copy
from os import system
import socket

WARNING_lOG = {}
#speed_limit_list = [8192]
speed_limit_list_hd = [256, 512, 768, 1024, 1280] #1MB /s
speed_limit_list_2k = [512, 1024, 1536, 2048, 2560] #2MB/s
speed_limit_list_4k = [1024, 2048, 3072, 4096, 5120] #3MB/s

ORIGIN_SERVER = 'arbutus' # or arbutus or east or googljp
contentType = 'contenthd/hd10' # or 'apple1/3340', (1840, 2540) 
target_duration = 10 # in seconds
rewardEvaluateDuration = 9 # The num of segment requested and responsed for one connection's reward evaluation duration
N_ARM = 11 # [0, 1, 2, ..., 10] ==> [-7, -6, ...,-1, 0, 1, 2, 3]
Horizon = N_ARM * 3
Last_Segment = 21

startup_latency_min = 2
startup_latency_max = 30
buffering_time_min = 0
buffering_time_max = target_duration * 6


Positive_Edge_MAX = 3
sampleConn_enough = 1	# how many connections' reward will be enough for one trial

chrome_hls_mode = True # if we use chrome hls as the hls client or we use self-developed hls client
newest_cacheFile_num = 10
test_bitrate = 3340
first_ivs_size = None

ALPHA = 1
GAMMA = 0.95
reward_alpha = 0.1 # the percentage of weight of 3 types of QoE metrics in the reward function: startup latency, buffertime, general_latency
reward_beta = 0.6
reward_gamma = 0.3

#SamplingDone = False

general_latency_min = 0
general_latency_max = startup_latency_max / 2 + (N_ARM - 1) * target_duration

history_performance_dict = {}
history_count = 0

def setup_edgeCache():
    system('/home/huanwanghuanwang/nginx/flush')
    
    system('cp /home/huanwanghuanwang/nginx/ngcache/{0}/{1}/* /home/huanwanghuanwang/nginx/ngcache/'.format(ORIGIN_SERVER, contentType))
    
    return

def httpReqSegment(segment_num):
    if 'apple1' in contentType:
        segment_num = str(test_bitrate) + str(segment_num)
    req = 'curl -s -I http://localhost/{0}/{1}/{2}.ts'.format(ORIGIN_SERVER, contentType, segment_num)
    print('Head request for caching ', segment_num)
    system(req + '> /dev/null')
    global onRequestedSegMax
    onRequestedSegMax = int(segment_num)
    

def update_playList_routine(next_segNum):
    _ivs = next_segNum
    max_cur_cached_segNum = newest_cacheFile_num
    print('update thread working now..')
    sleep_first_flag = True
    while True:
        if not update_playList_flag:
            print('update thread exit now...')
            break
        try:
            with open(m3u8_path + 'test.m3u8', 'a') as fp:
                while True:
                    if next_segNum > Last_Segment:
                        break
                    fp.write('#EXTINF:' + str(target_duration) + ',\n')
                    if 'apple' not in contentType:
                        fp.write(str(next_segNum).zfill(2) + '.ts\n')
                    else: # apple test content
                        fp.write(str(test_bitrate) + str(next_segNum) + '.ts\n')
                    if next_segNum < newest_cacheFile_num + Positive_Edge_MAX:
                        next_segNum += 1
                    else:
                        break
            if sleep_first_flag is True:
                print('sleep 0.6')
                time.sleep(int(target_duration * 0.4))
                sleep_first_flag = False
            else:
                print('sleep 1')
                time.sleep(int(target_duration * 1))
                if not update_playList_flag:
                    print('update thread exit now..')
                    return
            if (max_cur_cached_segNum + 1) < _ivs + rewardEvaluateDuration:
                httpReqSegment(max_cur_cached_segNum + 1) # Emulate the process that new segment being cached into the server
        except Exception as e:
            print(e)
            break
        else:
            if not update_playList_flag:
                break
            max_cur_cached_segNum += 1
            next_segNum += 1
            #time.sleep(int(target_duration * 1.1))
    return
    
def generate_m3u8(arm_trial, dstPath): # the arm_trial means how many segments hold back
    with open(dstPath + 'test.m3u8', 'w+') as fp:
        fp.write('#EXTM3U\n#EXT-X-TARGETDURATION:' + str(target_duration) + '\n#EXT-X-MEDIA-SEQUENCE:0\n')
    #left_edge = newest_dotts_num + arm_trial
        if chrome_hls_mode:
            right_edge = min(Positive_Edge_MAX, arm_trial + 2) + newest_cacheFile_num
            left_edge = arm_trial + newest_cacheFile_num 
            for i in range(left_edge, right_edge + 1): #-3 to make sure the edge is enough 
                fp.write('#EXTINF:' + str(target_duration) + ',\n')
                if 'apple' not in contentType: # 4k test content
                    fp.write(str(i).zfill(2) + '.ts\n')
                else: # apple test content
                    fp.write(str(test_bitrate) + str(i) + '.ts\n')
    return i+1
    #fp.write('#EXT-X-ENDLIST\n')
    
def arm_transform(arm, positive_edge = Positive_Edge_MAX, n_arm = N_ARM): # transform from (0 to N) to the real trial
    retval = arm - (n_arm - positive_edge) + 1
    return retval
    
def extractValue(line, para='cid'):
        pattern = para + '->"(.+?)"'
        matcher = re.search(pattern, line)
        if matcher is None:
            print(line)
            return False
        return matcher.group(1)

def getReqTsName(httpReq):
    pattern = ".+/(.+).ts"
    matcher = re.search(pattern, httpReq)
    return int(matcher.group(1))

class LogToReward():
    def __init__(self, sampleConn_enough_cond = 1, reward_evaluate_duration = 10, 
                 tar_duration = 10, newest_dotts_num = -1,
                 last_seg_num_in_m3u8 = -1):
        self.connDict = {} # The coonection dictionary
        self.sampleEnoughCond = sampleConn_enough_cond
        self.eval_duration = reward_evaluate_duration
        self.samplingDone = False
        self.tar_duration = tar_duration
        self.nbs_qualified_connection = 0
        self.newest_cachedDotTs_num = newest_dotts_num
        self._last_seg_num_in_m3u8 = last_seg_num_in_m3u8
        self._connId = -1
        self.latestCahedSegment = -1
        #self._timer = None
		
    def req_processing(self, line = None):
        if line is None:
            return False
        #print(line)
        r_ip = extractValue(line, para = 'rip')
        if r_ip != '127.0.0.1' and ('144.214.37' not in r_ip):
            print('unknown IP', r_ip)
            return False

        conn_id = int(extractValue(line, para = 'cid'))
        r_port = int(extractValue(line, para = 'rport'))
        
        if 'GET /' not in line:
            if 'HEAD' in line:
                tsname = getReqTsName(line)
                print('Head: {0} Cached by Head request'.format(tsname))
                if int(tsname) > self.latestCahedSegment:
                    self.latestCahedSegment = int(tsname)
            return False
        if '.ts' in line: # old connection
            conn_id = self._connId # HLS client generally launch 2 connections to concurrently access .ts and playlist, these two connections with port1 = port2+1 or port2-1             
            if self.connDict[conn_id]['finished_flag']:
                return False
            hit_status = extractValue(line, para='hitFlag')
            seg_num = getReqTsName(line)
            if self.connDict[conn_id]['newestSegNum'] < seg_num:
                if self.connDict[conn_id]['newestSegNum'] == 0: # the IVS request
                    self.connDict[conn_id]['firstReqSegNum'] = seg_num
                    ivs_size = int(extractValue(line, para = 'bytes'))
                    global first_ivs_size
                    if first_ivs_size is None:
                        first_ivs_size = ivs_size
                    self.connDict[conn_id]['ivs_size'] = ivs_size
                else:
                    try:
                        assert seg_num == (self.connDict[conn_id]['newestSegNum'] + 1)
                    except AssertionError as error:
                        print(error)
                        print(seg_num, self.connDict[conn_id]['newestSegNum'])
                        return False
                self.connDict[conn_id]['newestSegNum'] = seg_num
                self.connDict[conn_id]['seg_count'] += 1
                print('\nSegment_id: {0}, {1}, count: {2}\n'.format(seg_num, hit_status, self.connDict[conn_id]['seg_count']))
                # calc time different with connect start time
                cur_time = float(extractValue(line, para = 'mtime'))
                init_time = self.connDict[conn_id]['initTimeStmp']
                self.connDict[conn_id]['timeStampList'].append(cur_time - init_time)
                if self.connDict[conn_id]['seg_count'] >= self.eval_duration:
                    self.connDict[conn_id]['finished_flag'] = True
                    self.nbs_qualified_connection += 1
                    if self.nbs_qualified_connection >= self.sampleEnoughCond:
                        self.samplingDone = True         
            else:
                return False
                
        elif '.m3u8' in line: # New connection
            if len(self.connDict) == 0: # start a new program to update the playlist file
                #timer_ = threading.Timer(0.1, update_playList_routine, [self._last_seg_num_in_m3u8])
                self._u_thread = threading.Thread(target = update_playList_routine, args=(self._last_seg_num_in_m3u8,))
                self._u_thread.start()
                self._connId = conn_id
        
                self.connDict[conn_id] = {}
                self.connDict[conn_id]['newestSegNum'] = 0
                self.connDict[conn_id]['seg_count'] = 0
                self.connDict[conn_id]['initTimeStmp'] = float(extractValue(line, para = 'mtime'))
                self.connDict[conn_id]['timeStampList'] = []
                self.connDict[conn_id]['finished_flag'] = False
                self.connDict[conn_id]['rport'] = r_port
                self.connDict[conn_id]['ivs_size'] = 0
        
        else:
            return False
        
        return True
    
    def __calc_buffering_time(self, tsl): # timestampList
        total_buffering_time = 0
        real_play_start_time = 0
        for t in range(1, len(tsl)):
            if t == 1:
                required_play_time = tsl[0] + self.tar_duration
            else:
                required_play_time = real_play_start_time + self.tar_duration
                
            segment_trans_finished_time = tsl[t]
            
            if required_play_time < segment_trans_finished_time:
                total_buffering_time += segment_trans_finished_time - required_play_time
                real_play_start_time = segment_trans_finished_time
            else:
                real_play_start_time = required_play_time
            
        return total_buffering_time
        
    def calReward(self):
        startup_latency_sum = 0
        buffering_time_sum = 0
        general_latency_sum = 0
        count_connection = 0
        local_ivs = 0
        ivs_size = 0
        for conn, c_dict in self.connDict.items():
            if not c_dict['finished_flag']:
                continue
                
            count_connection += 1
            ivs_size = c_dict['ivs_size']
            startup_latency = c_dict['timeStampList'][0] / ivs_size * first_ivs_size
            buffering_time = self.__calc_buffering_time(c_dict['timeStampList'])
            local_ivs = c_dict['firstReqSegNum']
            general_latency = (self.newest_cachedDotTs_num + Positive_Edge_MAX - local_ivs) * self.tar_duration
            
            # add to sum
            startup_latency_sum += startup_latency
            buffering_time_sum += buffering_time
            general_latency_sum += general_latency
        
        assert self.nbs_qualified_connection == count_connection
        
        avg_award_list = np.array([startup_latency_sum, buffering_time_sum, general_latency_sum]) / count_connection
        
        history_performance_dict[history_count] = []
        
        history_performance_dict[history_count].append(copy.copy(avg_award_list).tolist())
        
        # do normalization
        if not (startup_latency_max >= avg_award_list[0] >= startup_latency_min):
            # Add warning...
            WARNING_lOG[history_count] = avg_award_list[0]
            if avg_award_list[0] < startup_latency_min:
                avg_award_list[0] = startup_latency_min
            else:
                avg_award_list[0] = startup_latency_max
    
        avg_award_list[0] = round((avg_award_list[0] - startup_latency_min) / (startup_latency_max - startup_latency_min), 5)
        avg_award_list[1] = round((avg_award_list[1] - buffering_time_min) / (buffering_time_max - buffering_time_min), 5)
        avg_award_list[2] = round((avg_award_list[2] - general_latency_min) / (general_latency_max - general_latency_min), 5)
        
        history_performance_dict[history_count].append(avg_award_list.tolist())
        
        reward = 1 - np.dot(avg_award_list, [reward_alpha, reward_beta, reward_gamma])
        history_performance_dict[history_count].append(local_ivs)
        history_performance_dict[history_count].append(reward)
        
        return reward


def request_speed_limit(server_name, speed):
    request_msg = str(speed) + 'k'
    server_ip = None
    if 'arbutus' in server_name:
        server_ip = '206.12.91.168'
    elif 'east' in server_name:
        server_ip = '206.167.183.202'
    else:
        server_ip = '35.243.89.232'
    server_port = 8081
    BUFFER_SIZE = 100
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        s.connect((server_ip, server_port))
        s.send(request_msg.encode())
        response = s.recv(BUFFER_SIZE).decode()
    except Exception as e:
        print(e)
        return False
    else:
        if 'OK' in response:
            s.close()
        else:
            return False
    
    return True

if __name__ == "__main__":

    #ducb = DiscountedUCB(nbArms = N_ARM, alpha = ALPHA, gamma = GAMMA)
    for content in ['contenthd/hd10']:
        contentType = content
        if 'hd' in contentType:
            speed_limit_list = speed_limit_list_hd
        elif '2K' in contentType:
            speed_limit_list = speed_limit_list_2k
        else:
            speed_limit_list = speed_limit_list_4k
        m3u8_path = '/home/huanwanghuanwang/nginx/m3u8/{0}/{1}/'.format(ORIGIN_SERVER, contentType)
        for speed_limit in speed_limit_list:
            history_count = 0
            history_performance_dict = {}
            if not request_speed_limit(ORIGIN_SERVER, speed_limit):
                break
            onRequestedSegMax = 0
            for t in range(0, Horizon):
                # play arm = t
                #if t >= N_ARM:
                 #   ducb.computeAllIndex()
                  #  cur_choice = np.argmax(ducb.index)
                #else:
                onRequestedSegMax = 0
                thread_stop_flag = False
                cur_choice = t % N_ARM
                print('\n\nPlay arm {0} now....!\n'.format(cur_choice))
                print('Setting up the edge caches...')
                setup_edgeCache() # setup the edge cache, delete the access.log
                time.sleep(2)
                
                child_process = subprocess.Popen('tail -F -n 200 /home/huanwanghuanwang/nginx/logs/access.log', shell=True, stdout = subprocess.PIPE, stderr=subprocess.PIPE)
                update_playList_flag = True
                last_seg_num_in_m3u8 = generate_m3u8(arm_trial = arm_transform(cur_choice), dstPath = m3u8_path)
                if 'apple' in contentType:
                    edgeNewest_complete_seqnum = int(str(test_bitrate) + str(newest_cacheFile_num))
                else:
                    edgeNewest_complete_seqnum = newest_cacheFile_num
                ltr = LogToReward(sampleConn_enough_cond = sampleConn_enough, 
                                  newest_dotts_num = edgeNewest_complete_seqnum, 
                                  reward_evaluate_duration = rewardEvaluateDuration, tar_duration = target_duration,
                                  last_seg_num_in_m3u8 = last_seg_num_in_m3u8)
                
                print('Waiting for access log...')
                while True:
                    #print('Waiting for access log...')
                    line = child_process.stdout.readline().decode()
                    if line:
                        ltr.req_processing(line)
                    if ltr.samplingDone:
                        if not thread_stop_flag:
                            update_playList_flag = False
                            ltr._u_thread.join()
                            system('> ' + m3u8_path + 'test.m3u8')
                            thread_stop_flag = True
                        if ltr.latestCahedSegment >= onRequestedSegMax: # waiting for all the head request's response finish
                            child_process.kill()
                            break
                    
                cur_reward = ltr.calReward()
                #ducb.getReward(cur_choice, cur_reward)
                del ltr
                print('Ready to sleep....\n')
                print('warning: ', WARNING_lOG)
                time.sleep(2)
                file_name = './res/' + str(speed_limit)  + '_' \
                + ORIGIN_SERVER + '_' + contentType.replace('/', '_')
                with open(file_name, 'a') as fp:
                    print('performance: ', history_count, history_performance_dict[history_count])
                    fp.write(str(history_count) + str(history_performance_dict[history_count]) + '\n')
                    #fp.close()
                history_count += 1
            
            
        
