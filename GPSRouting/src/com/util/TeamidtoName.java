package com.util;

import java.sql.SQLException;

import com.bean.Branch;
import com.bean.People;
import com.service.BranchService;
import com.service.PeopleService;

public class TeamidtoName {
	private static final String ADMIN = "管理员";
	private static final String ENGINEER = "工程师";
	private static final String ROOMER_MONITOR = "值班干部";
	private static final String ROOMER = "值班员";
	private static final String WORKER = "巡检员";
	
	public String idtoname(String teamid) {
		String value = "";
		switch (teamid) {
		case "0001":
			value = ADMIN;
			break;
		case "0002":
			value = ENGINEER;
			break;
		case "0003":
			value = ROOMER_MONITOR;
			break;
		case "0004":
			value = ROOMER;
			break;
		case "0005":
			value = WORKER;
			break;
		default:
			break;
		}
		return value;
	}
	
	/**
	 * 根据branchid取部门名称
	 * @param branchid
	 * @return
	 */
	public String braid2name(String branchid) {
		String bname =  "";
		BranchService bs = new BranchService();
		try {
			Branch branch = bs.fill(branchid);
			bname = branch.getBranchName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bname;
	}
	
	public String peoid2name(String id) {
		String pname = "";
		PeopleService ps = new PeopleService();
		try {
			People people = ps.fill(id);
			pname = people.getName();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pname;
	}
	
	
}
