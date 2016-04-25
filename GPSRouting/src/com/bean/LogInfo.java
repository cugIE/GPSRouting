package com.bean;

public class LogInfo {
	private String id;
	private String logname;
	private String username;//操作人
	private String logclass;
	private String mothod;//操作方法
	private String creattime;//创建时间
	private String loglevel;//日志级别
	private String msg;//日志内容
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogclass() {
		return logclass;
	}
	public void setLogclass(String logclass) {
		this.logclass = logclass;
	}
	public String getMothod() {
		return mothod;
	}
	public void setMothod(String mothod) {
		this.mothod = mothod;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getLoglevel() {
		return loglevel;
	}
	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
