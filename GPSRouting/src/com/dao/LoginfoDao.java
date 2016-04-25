package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Branch;
import com.bean.LogInfo;
import com.util.DB;

public class LoginfoDao {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 *  查询系统日志信息
	 * @return
	 * @throws SQLException
	 */
	public List<LogInfo> fill() throws SQLException {
        List<LogInfo> list = new ArrayList<LogInfo>();
        String sql = "select * from syslog";
        conn = DB.getConn();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        LogInfo l = null;
        while (rs.next()) {
            l = new LogInfo();              
            l.setId(rs.getString("LogId"));
            l.setUsername(rs.getString("UserName"));
            l.setCreattime(rs.getString("CreatTime"));
            l.setMsg(rs.getString("MSG"));
            list.add(l);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
}
