package org.lanqiao.entity;

public class UserState {
	private String ustateid;
	private String ustatename;
	public String getUstateid() {
		return ustateid;
	}
	public void setUstateid(String ustateid) {
		this.ustateid = ustateid;
	}
	public String getUstatename() {
		return ustatename;
	}
	public void setUstatename(String ustatename) {
		this.ustatename = ustatename;
	}
	public UserState(String ustateid, String ustatename) {
		super();
		this.ustateid = ustateid;
		this.ustatename = ustatename;
	}
	public UserState() {
		super();
	}
	@Override
	public String toString() {
		return "UserState [ustateid=" + ustateid + ", ustatename=" + ustatename
				+ "]";
	}
	

}
