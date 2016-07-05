package com.util;

public class PrivilegeHelper {
	public static String privProcessor(int i) {
		switch (i) {
		case 1:
			return "管理员";
		case 2:
			return "工程师";
		case 3:
			return "值班干部";
		case 4:
			return "巡检员";
		case 5:
			return "值班员";

		default:
			return "error";
		}
	}
}
