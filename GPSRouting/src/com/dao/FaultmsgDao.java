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
        	f.setDutyPeople(rs.getString("duty_man"));
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
        	f.setVideoUrl(rs.getString("video"));
        	f.setDutyPeople(rs.getString("duty_man"));
        	f.setGenerId(rs.getString("gener_id"));
        }
        rs.close();
        pre.close();
        conn.close();
        return f;
	}
	
	/**
	 * 根据上报人id查找故障信息
	 * @param generid
	 * @return
	 * @throws SQLException
	 */
	public List<Faultmsg> fillgenId(String generid)throws SQLException {
		List<Faultmsg> list = new ArrayList<Faultmsg>();
		conn = DB.getConn();
        String sql="select * from faultmsg where gener_id=?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, generid);
        rs=pre.executeQuery();
        Faultmsg f = null;
        while(rs.next()){
        	f = new Faultmsg();
        	f.setId(rs.getString("id"));
        	f.setFaultTitle(rs.getString("title"));
        	f.setFaultWord(rs.getString("word"));
        	f.setFaultTime(rs.getString("time"));
        	f.setFaultState(rs.getString("state"));
        	f.setFaultUrL(rs.getString("url"));
        	f.setDutyPeople(rs.getString("duty_man"));
        	f.setGenerId(rs.getString("gener_id"));
        	list.add(f);
        }
        rs.close();
        pre.close();
        conn.close();
        return list;
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
	
	/**
	 * @param faultmsg
	 * @return
	 * @throws SQLException
	 */
	public int add(Faultmsg faultmsg)throws SQLException{
		String sql = "insert into faultmsg(title,word,time,url,video,gener_id) values ('" + faultmsg.getFaultTitle() +"','"
				+ faultmsg.getFaultWord() +"','"
				+ faultmsg.getFaultTime()+"','"
				+ faultmsg.getFaultUrL() +"','"
				+ faultmsg.getVideoUrl() +"','"
				+ faultmsg.getGenerId()+ "')";
		conn= DB.getConn();
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
		return result;
	}
	
	/**
	 * 添加故障责任人
	 * @param faultid
	 * @param dutyman
	 * @return
	 * @throws SQLException
	 */
	public int handleFault(String faultid,String dutyman) throws SQLException{
		String sql = "UPDATE faultmsg SET duty_man=? WHERE id=?";
		conn=DB.getConn();
		pre = conn.prepareStatement(sql);
		pre.setString(1, dutyman);
		pre.setString(2, faultid);
		int count =pre.executeUpdate();
		pre.close();
		conn.close();
		return count;
	}
}
