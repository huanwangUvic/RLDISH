package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dao.TaskDAO;
import com.domain.Department;
import com.domain.Respro;
import com.domain.Task;
import com.util.DBConn;

public class TaskDAOImpl implements TaskDAO{
	
	
	public Task gettask(int id)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;
		//声明结果集
		ResultSet res = null;
		
		try {
			String sql = "select * from task where taskid=? ";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			
			//执行sql
			res = pre.executeQuery();
			
			if(res.next()){
				Task task = new Task();
				task.setEndTime(res.getString("end_time"));
				task.setFileCon(res.getInt("file_con"));
				task.setPlanTime(res.getString("plan_time"));
				task.setProid(res.getInt("proid"));
				task.setStartTime(res.getString("start_time"));
				task.setSubFile(res.getString("sub_file"));
				task.setTaskCondition(res.getInt("task_condition"));
				task.setTaskid(res.getInt("taskid"));
				task.setTaskname(res.getString("taskname"));
				task.setTaskexplain(res.getString("taskexplain"));
				task.setFilePath(res.getInt("file_path"));
				return task;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeResultSet(res);
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return null;
	}
	
	
	public boolean addtask(Task task)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "insert into task (proid,taskname,plan_time,start_time,end_time,task_condition,sub_file,file_con,taskexplain,file_path) values(?,?,?,?,?,?,?,?,?,?)";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, task.getProid());
			pre.setString(2, task.getTaskname());
			pre.setString(3, task.getPlanTime());
			pre.setString(4, task.getStartTime());
			pre.setString(5, task.getEndTime());
			pre.setInt(6, task.getTaskCondition());
			pre.setString(7, task.getSubFile());
			pre.setInt(8, task.getFileCon());
			pre.setString(9, task.getTaskexplain());
			pre.setInt(10, task.getFilePath());
			// 执行sql
			int i = pre.executeUpdate();
			System.out.println("影响行数："+i);
			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}

		return false;
	}
	
	public boolean updatetask(Task task)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "update task set proid=?,taskname=?,plan_time=?,start_time=?,end_time=?,task_condition=?,sub_file=?,file_con=?,taskexplain=?,file_path=? where taskid=?";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			
			pre.setInt(1, task.getProid());
			pre.setString(2, task.getTaskname());
			pre.setString(3, task.getPlanTime());
			pre.setString(4, task.getStartTime());
			pre.setString(5, task.getEndTime());
			pre.setInt(6, task.getTaskCondition());
			pre.setString(7, task.getSubFile());
			pre.setInt(8, task.getFileCon());
			pre.setString(9, task.getTaskexplain());
			pre.setInt(10, task.getFilePath());
			pre.setInt(11, task.getTaskid());
			// 执行sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		return false;
	}
	
	
	public boolean deltask2(int taskid)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "delete from task where taskid=?";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, taskid);

			// 执行sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return false;
	}
	
	
	public boolean deltask(int id)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "delete from task where proid=?";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);

			// 执行sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return false;
	}
	
	//public Task gettask(int id);
	
	public ArrayList<Task> getAlltask(String sql)
	{
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;
		//声明结果集
		ResultSet res = null;
		
		try {
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			//执行sql
			res = pre.executeQuery();
			//创建集合
			ArrayList<Task> list = new ArrayList<Task>();
			
			while(res.next()){
				Task task = new Task();
				task.setEndTime(res.getString("end_time"));
				task.setFileCon(res.getInt("file_con"));
				task.setPlanTime(res.getString("plan_time"));
				task.setProid(res.getInt("proid"));
				task.setStartTime(res.getString("start_time"));
				task.setSubFile(res.getString("sub_file"));
				task.setTaskCondition(res.getInt("task_condition"));
				task.setTaskid(res.getInt("taskid"));
				task.setTaskname(res.getString("taskname"));
				task.setTaskexplain(res.getString("taskexplain"));
				task.setFilePath(res.getInt("file_path"));
				list.add(task);
				
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeResultSet(res);
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return null;
	}
	
	public boolean updatetask2()
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "update task set file_path = DATEDIFF(end_time,now())";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			
			
			// 执行sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		return false;
	}
	
	public boolean updatetask3()
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "update task set task_condition=2 where task_condition=1";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			
			
			// 执行sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		return false;
	}
}
