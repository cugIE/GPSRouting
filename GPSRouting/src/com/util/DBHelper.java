package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A helper to generate results;
 * @author XinLi
 *
 */
public class DBHelper {
	private Connection conn;
	private Statement sttmt;
	
	public DBHelper(){
		conn = DB.getConn();
		sttmt = DB.getStatement(conn);
	}
	public void DBClose(ResultSet rs) throws SQLException{
		rs.close();
		sttmt.close();
		conn.close();
	}
	public ResultSet getResultSet(String sql){
		return DB.getResultSet(sttmt, sql);
	}
	public int updateDatabase(String sql) throws SQLException{
		return sttmt.executeUpdate(sql);
		
	}

}
