package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dao.OperecordDAO;
import com.domain.Operecord;
import com.domain.Respro;
import com.util.DBConn;

public class OperecordDAOImpl  implements OperecordDAO{
	
	public boolean addope(Operecord ope)
	{
		// 声明数据连接
		Connection conn = null;
		// 声明实例
		PreparedStatement pre = null;

		try {
			String sql = "insert into operecord (opetime,opecontent,opeman,opetype) values(?,?,?,?)";
			// 获取Connection
			conn = DBConn.getConn();
			// 创建实例
			pre = conn.prepareStatement(sql);
			pre.setString(1, ope.getOpetime());
			pre.setString(2, ope.getOpecontent());
			pre.setInt(3, ope.getOpeman());
			pre.setInt(4, ope.getOpetype());
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
	
	public ArrayList<Operecord> getAllope(String sql)
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
			ArrayList<Operecord> list = new ArrayList<Operecord>();
			
			while(res.next()){
				Operecord ope= new Operecord();
				ope.setOpeid(res.getInt("opeid"));
				ope.setOpecontent(res.getString("opecontent"));
				ope.setOpeman(res.getInt("opeman"));
				ope.setOpetime(res.getString("opetime"));
				ope.setOpetype(res.getInt("opetype"));
				list.add(ope);
				
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
