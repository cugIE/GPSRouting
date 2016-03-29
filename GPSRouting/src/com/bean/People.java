package com.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DB;

//人员信息
public class People {
	private int id;
	private String username;
	private String name;
	private String password;
	private String peopRemark;
	private int branchId;
	private int teamId;
	private int generId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPeopRemark() {
		return peopRemark;
	}
	public void setPeopRemark(String peopRemark) {
		this.peopRemark = peopRemark;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getGenerId() {
		return generId;
	}
	public void setGenerId(int generId) {
		this.generId = generId;
	}
	public boolean check(String username,String password) 
		throws Exception{
		boolean i = false;
		People p = null;
		Connection conn = DB.getConn();
		String sql = "select * from people where people_username = '"+ username +"'";
		Statement stmt = DB.getStatement(conn);
		//根据sql语句获取查询结果
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			if (!rs.next()) {
				throw new Exception("用户不存在：" + username);	
			} else {
				if (!password.equals(rs.getString("people_password"))) {
					throw new Exception("密码不正确！");
				}
				i = true;
				p = new People();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));	
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return i;
	}
	
	public boolean checkIs(String username)
			throws Exception {
		boolean b = true;
		Connection conn = DB.getConn();
		String sql = "select * from people where people_username = '"+ username +"'";
		Statement stmt = DB.getStatement(conn);
		//根据sql语句获取查询结果
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			if (rs.next()) {
				throw new Exception("用户已存在");	
			} else {
				b = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return b;
	}
	
	public boolean insertPeop(String username,String name,String password)
			throws Exception{	
		boolean ok = true;
		Connection conn = DB.getConn();
		String sql = "insert into people(people_username,people_name,people_password) values ('"+ username +"','"+ name +"','"+ password +"')";
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			rs.insertRow();
		} catch (Exception e) {
			// TODO: handle exception
			ok = false;
		}
		
		return ok;
		
	}
}
