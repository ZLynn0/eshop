package org.lanqiao.service;


import java.util.List;

import org.lanqiao.entity.Order;


public interface OrderService {
	public void insertOrder(Order order );
	public List<Order> orderlist(String userid);
	public List<Order> orderlist();
	public void removeorder(String orderid);
	public void updateorder(Order order);
	public Order getOrderByOrderid(String orderid);

}
