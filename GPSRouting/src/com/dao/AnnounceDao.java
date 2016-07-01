package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Announcement;
import com.bean.Faultmsg;
import com.util.DB;

public class AnnounceDao {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pre;
	
	/**
	 * 查询所有的公告信息
	 * @return
	 * @throws SQLException
	 */
	public List<Announcement> fill() throws SQLException {
        List<Announcement> list = new ArrayList<Announcement>();
        String sql = "select * from announcement";
        conn = DB.getConn();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        Announcement a = null;
        while (rs.next()) {
        	a = new Announcement();
        	a.setId(rs.getString("id"));
        	a.setTitle(rs.getString("title"));
        	a.setContent(rs.getString("content"));
        	a.setTime(rs.getString("time"));
        	a.setGenerId(rs.getString("gener_id"));
        	list.add(a);
            
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
	
	/**
	 * 根据发布人查询公告信息
	 * @param generid
	 * @return
	 * @throws SQLException
	 */
	public List<Announcement> fillgenId(String generid)throws SQLException {
		List<Announcement> list = new ArrayList<Announcement>();
		conn = DB.getConn();
        String sql="select * from announcement where gener_id=?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, generid);
        rs=pre.executeQuery();
        Announcement a = null;
        while(rs.next()){
        	a = new Announcement();
        	a.setId(rs.getString("id"));
        	a.setTitle(rs.getString("title"));
        	a.setContent(rs.getString("content"));
        	a.setTime(rs.getString("time"));
        	a.setGenerId(rs.getString("gener_id"));
        	list.add(a);
        }
        rs.close();
        pre.close();
        conn.close();
        return list;
	}
	
	/**
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public Announcement fill(String Id)throws SQLException {
		Announcement a = null;
		String sql="select * from announcement where id=?";
		conn = DB.getConn();
		pre = conn.prepareStatement(sql);
        pre.setString(1, Id);
        rs=pre.executeQuery();
        if (rs.next()) {
        	a = new Announcement();
        	a.setId(rs.getString("id"));
        	a.setTitle(rs.getString("title"));
        	a.setContent(rs.getString("content"));
        	a.setTime(rs.getString("time"));
        	a.setGenerId(rs.getString("gener_id"));
		}
        rs.close();
        pre.close();
        conn.close();
		return a;
	}
	
}
