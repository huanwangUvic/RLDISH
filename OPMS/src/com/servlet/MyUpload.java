package com.servlet;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.TaskDAO;
import com.dao.impl.TaskDAOImpl;
import com.domain.Task;

/**
* @author 唐朝
* 上传进度条程序
*/
public class MyUpload extends HttpServlet {

private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	request.setCharacterEncoding("utf-8");//设置页面编码 
	String id = (String)request.getSession().getAttribute("uploadtaskid");
	//System.out.println(id);
	
	int taskid = -1;
	try
	{
		taskid = Integer.parseInt(id);
	}catch (Exception e)
	{};
	//System.out.println("this is taskid"+taskid);
	TaskDAO dao = new TaskDAOImpl();
	Task task = new Task();
	task = dao.gettask(taskid);
	task.setFileCon(1);
	dao.updatetask(task);
	
        response.setContentType("text;charset=utf-8");//内容类型

        if ("status".equalsIgnoreCase(request.getParameter("status"))) {
        //ajax监听上传进度
            status(response,request);
        } else {
        //上传文件
            long totalSize = request.getContentLength(); 
            request.getSession().setAttribute("totalsize", String.valueOf(totalSize));//保存所有文件的上传尺寸
            try {
                upload(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            //response.sendRedirect("/implpage/uploadresult.jsp");    
            request.getRequestDispatcher("/implpage/uploadresult.jsp").forward(request, response);
        }
        
    }

    /**@author 唐朝
     * @param request
     * @throws IOException
     * @throws FileUploadException 
     * void Nov 25, 2008 1:46:21 PM
     * 上传文件
     */
    private void upload(HttpServletRequest request) throws IOException, FileUploadException {
    
   
    	
    	
    	long completedSize=0;//初始化已完成上传的容量为0
    initSession("completedsize","0",request);//初始化session，已经完成的上传尺寸
    
        FileItemFactory factory = new DiskFileItemFactory();
        
        // 通过该工厂对象创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        List items = upload.parseRequest(request);
        
        String result="";//文件提示信息
        
    HashMap map=new HashMap();
        
        for(Iterator i = items.iterator(); i.hasNext();){
        
            FileItem fileItem = (FileItem) i.next();
            
            // 如果该FileItem不是表单域
            if(!fileItem.isFormField()){
	
            if(fileItem.getName()!=null&&fileItem.getSize()>0){

               result="正在上传第"+fileItem.getFieldName()+"份文件,请不要刷新或者离开此页面...<br />";
               initSession("result",result,request);
              
                    String fileName = fileItem.getName().substring(fileItem.getName().lastIndexOf("\\") + 1);

              
                   String id = (String)request.getSession().getAttribute("uploadtaskid");
                   //System.out.println(id);
                   String path = request.getRealPath("/");
                   path+="upload\\";
                   path+=id;
                   
                   //System.out.println(path);
                    
                    System.out.println("uploadpage1 "+fileName);
                    File d = new File(path);
                    if(!d.exists())
                    {
                    	d.mkdir();
                    	
                    }
                    
                   // String filePath = System.currentTimeMillis()+ "." + fileName.substring(fileName.lastIndexOf(".") + 1);;
                    //System.out.println("uploadpage "+filePath);
                    File file = new File(path, fileName);//上传的文件
              
             //文件名
                 InputStream in = fileItem.getInputStream();
                 FileOutputStream out = new FileOutputStream(file);      
                 byte[] buffer = new byte[1024]; 

                 int n;
                 while((n = in.read(buffer))!= -1){ 
                 
                     out.write(buffer, 0, n);
                     completedSize += (long) n;//上传的文件尺寸累加，用于计算上传百分比
                     
                     request.getSession().setAttribute("completedsize", String.valueOf(completedSize));//存入session，带ajax监听调用

                     try{
        Thread.sleep(2);//让进程等待，用于测试
       }catch(InterruptedException e){
        e.printStackTrace();
       }
                 }
                 in.close();
                 out.close(); 
                 fileItem.delete();// 内存中删除该数据流 删除临时文件
             }   

            }else{
            
            String name=fileItem.getFieldName();
            String value=fileItem.getString("utf-8");
            //String value=fileItem.getString("GBK");//带编码参数，用于解析
            map.put(name, value);
            
            }
        }
       

        
    }

    /**@author 唐朝
     * @param response
     * @param request
     * @throws IOException 
     * void Nov 25, 2008 1:46:33 PM
     * 获取上传进度
     */
    private void status(HttpServletResponse response,HttpServletRequest request) throws IOException {
        int percent = (int) (getCompletedSize(request) * 100 / (getTotalSize(request) + 0.0001));
        String result=request.getSession().getAttribute("result")==null?"文件":(String)request.getSession().getAttribute("result");
        response.getWriter().print(percent+"-"+result);
    }
    
    
    /**@author 唐朝
     * @param request
     * @return 
     * long Nov 25, 2008 1:46:44 PM
     * 上传文件总大小
     */
    private long getTotalSize(HttpServletRequest request){
    return request.getSession().getAttribute("totalsize")==null?0L:Long.parseLong((String)request.getSession().getAttribute("totalsize"));
    }
    
    /**@author 唐朝
     * @param request
     * @return 
     * long Nov 25, 2008 1:47:00 PM
     * 已经上传的大小
     */
    private long getCompletedSize(HttpServletRequest request){
    return request.getSession().getAttribute("completedsize")==null?0L:Long.parseLong((String)request.getSession().getAttribute("completedsize"));

    }

    /**@author 唐朝
     * @param sessionName
     * @param sessionValue
     * @param request 
     * void Nov 25, 2008 1:47:19 PM
     * 初始化session中的值
     */
    private void initSession(String sessionName,String sessionValue,HttpServletRequest request){
    request.getSession().setAttribute(sessionName, sessionValue);
    }

}

