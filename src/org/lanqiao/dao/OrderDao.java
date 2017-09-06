package org.lanqiao.dao;


import java.util.List;

import org.lanqiao.entity.Order;


public interface OrderDao {
	public void insert(Order order);
	public List<Order> list(String userid);
	public List<Order> list();
	public void removeorder(String orderid);
	public void updateorder(Order order);
	public Order getOrderByOrderid(String orderid);
}
