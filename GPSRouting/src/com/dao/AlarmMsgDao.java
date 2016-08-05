package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.AlarmMsg;
import com.bean.Branch;
import com.util.DB;

public class AlarmMsgDao {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pre;
	
	/**
	 * 添加新的报警信息
	 * @param alarmMsg
	 * @return
	 * @throws SQLException
	 */
	public int addAlarm(AlarmMsg alarmMsg) throws SQLException{
		String sql = "insert into alarmmsg(address,time,gener_id) values ('"+ alarmMsg.getAlarmAddress() +"','" 
				+ alarmMsg.getAlarmTime()+ "','"
				+ alarmMsg.getGener_id()+"')";
		conn = DB.getConn();
		stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
		return result;		
	}
	/**
	 * 查询警告信息
	 * @return
	 * @throws SQLException
	 */
	public List<AlarmMsg> fillAlarm()throws SQLException {
		List<AlarmMsg> list = new ArrayList<AlarmMsg>();
		String sql = "select * from alarmmsg";
		conn = DB.getConn();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		AlarmMsg a= null;
		while (rs.next()) {
			a = new AlarmMsg();
			a.setId(rs.getString("id"));
			a.setAlarmAddress(rs.getString("address"));
			a.setAlarmTime(rs.getString("time"));
			a.setState(rs.getString("state"));
			a.setGener_id(rs.getString("gener_id"));
			list.add(a);
		}
		rs.close();
		stmt.close();
		conn.close();
		return list;
	}
	
	public AlarmMsg fillAlarm(String id) throws SQLException{
		AlarmMsg a = null;
		String sql = "select * from alarmmsg where id=?";
		conn = DB.getConn();
		pre = conn.prepareStatement(sql);
	    pre.setString(1, id);
	    rs=pre.executeQuery();
	    if (rs.next()) {
			a = new AlarmMsg();
			a.setId(rs.getString("id"));
			a.setAlarmAddress(rs.getString("address"));
			a.setAlarmTime(rs.getString("time"));
			a.setState(rs.getString("state"));
			a.setGener_id(rs.getString("gener_id"));
		}
	    rs.close();
	    pre.close();
	    conn.close();
	    return a;		
	}
	
	/**
	 * 根据警报上传人id查找警报信息
	 * @param generid
	 * @return
	 * @throws SQLException
	 */
	public List<AlarmMsg> fillpeoAlarm(String generid) throws SQLException{
		List<AlarmMsg> list = new ArrayList<AlarmMsg>();
		AlarmMsg a = null;
		String sql = "select * from alarmmsg where gener_id=?";
		conn = DB.getConn();
		pre = conn.prepareStatement(sql);
	    pre.setString(1, generid);
	    rs=pre.executeQuery();
	    while (rs.next()) {
			a = new AlarmMsg();
			a.setId(rs.getString("id"));
			a.setAlarmAddress(rs.getString("address"));
			a.setAlarmTime(rs.getString("time"));
			a.setState(rs.getString("state"));
			a.setGener_id(rs.getString("gener_id"));
			list.add(a);
		}
	    rs.close();
	    pre.close();
	    conn.close();
	    return list;		
	}
	
	
	
	/**
	 * 统计未查看的警告信息条数
	 * @return
	 * @throws SQLException
	 */
	public int Alarmscount() throws SQLException{
		int count = 0;
		conn = DB.getConn();
		String sql = "select * from alarmmsg where state = ?";
		pre=  conn.prepareStatement(sql);
		pre.setString(1, "0");
		rs= pre.executeQuery();
		while (rs.next()) {
			count ++;
		}
		return count;
	}
	
	/**
	 * 根据警报信息id更新状态
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateAlarm(String id) throws SQLException{
		String sql = "UPDATE alarmmsg SET state=? WHERE id=?";
		conn=DB.getConn();
		pre = conn.prepareStatement(sql);
		pre.setString(1, "1");
		pre.setString(2, id);
		int count =pre.executeUpdate();
		pre.close();
		conn.close();
		return count;
	}
}
