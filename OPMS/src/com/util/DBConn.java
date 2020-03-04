package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接对象
 * 
 * @author liu
 * 
 */
public class DBConn {

	/**
	 * 获取数据库连接
	 * @return 数据连接
	 */
	public static Connection getConn() {

		try {
			// 注册一个驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 与数据库建立一个连接
			Connection con = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/epb?user=root&password=52123");

			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 关闭数据库操作对象
	 * @param pst
	 */
	public static void closeStatement(java.sql.PreparedStatement pst){
		if(pst != null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭结果集
	 * @param res
	 */
	public static void closeResultSet(ResultSet res){
		if(res != null){
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(DBConn.getConn()); 
	}*/
}
