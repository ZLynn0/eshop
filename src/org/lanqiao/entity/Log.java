package org.lanqiao.entity;

import java.util.Date;

public class Log {
	private String logid;
	private String uloginid;
	private String options;
	private Date odate;
	public Log() {
		super();
	}
	public Log(String logid, String uloginid, String options, Date odate) {
		super();
		this.logid = logid;
		this.uloginid = uloginid;
		this.options = options;
		this.odate = odate;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getUloginid() {
		return uloginid;
	}
	public void setUloginid(String uloginid) {
		this.uloginid = uloginid;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	@Override
	public String toString() {
		return "Log [logid=" + logid + ", uloginid=" + uloginid + ", options="
				+ options + ", odate=" + odate + "]";
	}
	
	

}
