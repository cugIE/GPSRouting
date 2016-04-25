package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.People;
import com.bean.Site;
import com.util.DB;

public class SiteDao {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pre;
	
	public Site fillsite(String Id) throws SQLException{
        conn = DB.getConn();
        String sql="select * from website where web_id=?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, Id);
        rs=pre.executeQuery();
        Site s = null;
        if(rs.next()){
        	s = new Site();
			s.setId(rs.getString("web_id")); 
			s.setWebtitle(rs.getString("web_title"));
			s.setWebad(rs.getString("web_ad"));
			s.setWebword(rs.getString("web_word"));
			s.setComname(rs.getString("com_name"));
			s.setGenerid(rs.getString("gener_id"));
        }
        rs.close();
        pre.close();
        conn.close();
        return s;
    }
	
	/**
	 * 更新网站信息
	 * @param site
	 * @return
	 * @throws SQLException
	 */
	public int updateSite(Site site) throws SQLException{
		String sql="UPDATE website SET web_title=?,web_ad=?,web_word=?,com_name=? WHERE web_id=?";
        conn=DB.getConn();
        pre = conn.prepareStatement(sql);
        pre.setString(1,  site.getWebtitle());
        pre.setString(2,  site.getWebad());
        pre.setString(3,  site.getWebword());
        pre.setString(4,  site.getComname());
        pre.setString(5,  site.getId());
        int count=pre.executeUpdate();
        pre.close();
        conn.close();
        return count;  
	}
}
