package org.lanqiao.entity;

import java.util.Date;

public class Order {
	private String orderid;
	private String gid;
	private String userid;
	private double totalprice;
	private Date orderDate;
	public Order(String orderid, String gid, String userid, double totalprice,
			Date orderDate) {
		this.orderid = orderid;
		this.gid = gid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderDate = orderDate;
	}
	public Order() {
		super();
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Date getOrderDate() {
		return  orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", gid=" + gid + ", userid="
				+ userid + ", totalprice=" + totalprice + ", orderDate="
				+ orderDate + "]";
	}
	
	
	
}
