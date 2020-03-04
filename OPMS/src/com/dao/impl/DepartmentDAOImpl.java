package com.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dao.*;
import com.domain.Department;
import com.domain.Respro;
import com.util.DBConn;
public class DepartmentDAOImpl implements DepartmentDAO{
	

	public boolean adddep(Department dep)
	{
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "insert into department (depnum,depname,deprole,deppwd) values(?,?,?,?)";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, dep.getDepnum());
			pre.setString(2,dep.getDepname());
			pre.setInt(3, dep.getDeprole());
			pre.setString(4, dep.getDeppwd());
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
	
	public boolean deldep(int num)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "delete from department where depnum=?";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, num);

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
	
	public boolean updatedep(Department dep)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "update department set depname=?,deprole=?,deppwd=? where depnum=?";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			
			pre.setString(1, dep.getDepname());
			pre.setInt(2, dep.getDeprole());
			pre.setString(3, dep.getDeppwd());
			pre.setInt(4, dep.getDepnum());

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
	
	public Department getdep(int num)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;
		//声明结果集
		ResultSet res = null;
		
		try {
			String sql = "select * from department where depnum=? ";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setInt(1, num);
			
			//执行sql
			res = pre.executeQuery();
			
			if(res.next()){
				Department dep = new Department();
				dep.setDepnum(num);
				dep.setDepname(res.getString("depname"));
				dep.setDeprole(res.getInt("deprole"));
				dep.setDeppwd(res.getString("deppwd"));
				return dep;
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
	
	public ArrayList<Department> getAlldep(String sql)
	{
		// 声明数据连接
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
			ArrayList<Department> list = new ArrayList<Department>();
			
			while(res.next()){
				Department dep = new Department();
				dep.setDepnum(res.getInt("depnum"));
				dep.setDepname(res.getString("depname"));
				dep.setDeprole(res.getInt("deprole"));
				dep.setDeppwd(res.getString("deppwd"));
				list.add(dep);
				
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

}
