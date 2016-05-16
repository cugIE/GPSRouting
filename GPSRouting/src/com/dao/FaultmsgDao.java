package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.bean.Faultmsg;
import com.util.DB;

public class FaultmsgDao {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pre;
	
	/**
	 *  查询故障信息
	 * @return
	 * @throws SQLException
	 */
	public List<Faultmsg> fill() throws SQLException {
        List<Faultmsg> list = new ArrayList<Faultmsg>();
        String sql = "select * from faultmsg";
        conn = DB.getConn();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        Faultmsg f = null;
        while (rs.next()) {
        	f = new Faultmsg();
        	f.setId(rs.getString("id"));
        	f.setFaultTitle(rs.getString("title"));
        	f.setFaultWord(rs.getString("word"));
        	f.setFaultTime(rs.getString("time"));
        	f.setFaultState(rs.getString("state"));
        	f.setFaultUrL(rs.getString("url"));
        	f.setGenerId(rs.getString("gener_id"));
        	list.add(f);
            
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Faultmsg fill(String id)throws SQLException {
		conn = DB.getConn();
        String sql="select * from faultmsg where id=?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, id);
        rs=pre.executeQuery();
        Faultmsg f = null;
        if(rs.next()){
        	f = new Faultmsg();
        	f.setId(rs.getString("id"));
        	f.setFaultTitle(rs.getString("title"));
        	f.setFaultWord(rs.getString("word"));
        	f.setFaultTime(rs.getString("time"));
        	f.setFaultState(rs.getString("state"));
        	f.setFaultUrL(rs.getString("url"));
        	f.setGenerId(rs.getString("gener_id"));
        }
        rs.close();
        pre.close();
        conn.close();
        return f;
	}
	
	/**
	 * 根据故障信息id更新状态
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updatestatus(String id) throws SQLException{
		String sql = "UPDATE faultmsg SET state=? WHERE id=?";
		conn=DB.getConn();
		pre = conn.prepareStatement(sql);
		pre.setString(1, "1");
		pre.setString(2, id);
		int count =pre.executeUpdate();
		pre.close();
		conn.close();
		return count;
	}
	
	/**
	 * 统计未读的故障信息条数
	 * @return
	 * @throws SQLException
	 */
	public int faultcount() throws SQLException{
		int count = 0;
		conn = DB.getConn();
		String sql = "select * from faultmsg where state = ?";
		pre=  conn.prepareStatement(sql);
		pre.setString(1, "0");
		rs= pre.executeQuery();
		while (rs.next()) {
			count ++;
		}
		return count;
	}
}
