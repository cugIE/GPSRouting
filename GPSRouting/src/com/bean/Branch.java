package com.bean;
//部门信息
public class Branch {
	private int id;
	private String branchName;
	private String branchType;
	private String comName;
	private int comId;
	private int generId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchType() {
		return branchType;
	}
	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public int getGenerId() {
		return generId;
	}
	public void setGenerId(int generId) {
		this.generId = generId;
	}
}
