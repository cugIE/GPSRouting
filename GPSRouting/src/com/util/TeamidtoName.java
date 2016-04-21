package com.util;

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
	
	
}
