package org.lanqiao.entity;

public class Category {
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public Category() {
		super();
	}
	public Category(String cid, String cname) {

		this.cid = cid;
		this.cname = cname;
	}
	
	

}