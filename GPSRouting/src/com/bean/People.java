package com.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DB;

//人员信息
public class People {
	private String id;
	private String username;
	private String name;
	private String password;
	private String code;
	private String peopRemark;
	private int branchId;
	private int teamId;
	private int generId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	
}
