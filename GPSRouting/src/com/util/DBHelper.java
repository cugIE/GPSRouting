package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private PreparedStatement psttmt;
	
	public DBHelper(){
		conn = DB.getConn();
		sttmt = DB.getStatement(conn);
	}
	public void DBClose(ResultSet rs) throws SQLException{
		rs.close();
		sttmt.close();
		conn.close();
	}
	public void DBClose() throws SQLException{
		sttmt.close();
		conn.close();
	}
	public ResultSet getResultSet(String sql){
		return DB.getResultSet(sttmt, sql);
	}
	public int updateDatabase(String sql) throws SQLException{
		return sttmt.executeUpdate(sql);
		
	}
//	public Statement getPreparedStatement() {
//		return sttmt;
//	}
//	public void setPreparedStatement (String sql) throws SQLException{
//		psttmt = (PreparedStatement)conn.prepareStatement(sql);
//	}
//	public int getPstmtUpdateResult() throws SQLException{
//		int i = psttmt.executeUpdate();
//		return i;
//	}

}
