package com.bean;
//部门信息
public class Branch {
	private String id;
	private String branchCode;
	private String branchName;
	private String branchType;
	private String comName;
	private String comId;
	private int generId;
	public String getId() {
		return id;
	}
	public void setId(String branch_id) {
		this.id = branch_id;
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
	public String getComId() {
		return comId;
	}
	public void setComId(String com_id) {
		this.comId = com_id;
	}
	public int getGenerId() {
		return generId;
	}
	public void setGenerId(int generId) {
		this.generId = generId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
}
