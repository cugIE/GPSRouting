package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//连接数据库类
public class DB {
//获取连接
	public static Connection getConn(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;		
	}
	//获取连接状态
	public static Statement getStatement(Connection conn){
		Statement stmt = null;
		try {
			if(conn != null){
				stmt = conn.createStatement();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stmt;	
	}
	//根据sql语句获取查询结果
	public static ResultSet getResultSet(Statement stmt, String sql) {
		ResultSet rs = null;
		try {
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;	
	}
	//关闭数据库连接
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//关闭连接状态
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//关闭查询
	public static void  close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
}
