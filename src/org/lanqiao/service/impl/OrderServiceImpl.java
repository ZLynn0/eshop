package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.entity.Order;
import org.lanqiao.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private org.lanqiao.dao.OrderDao dao=new org.lanqiao.dao.impl.OrderDaoImpl();
	@Override
	public void insertOrder(Order order) {
		dao.insert(order);
		
	}
	@Override
	public List<Order> orderlist(String userid) {
		// TODO Auto-generated method stub
		return dao.list(userid);
	}
	@Override
	public List<Order> orderlist() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	@Override
	public void removeorder(String orderid) {
		// TODO Auto-generated method stub
		dao.removeorder(orderid);
	}
	@Override
	public void updateorder(Order order) {
		dao.updateorder(order);
		
	}
	@Override
	public Order getOrderByOrderid(String orderid) {
		// TODO Auto-generated method stub
		return dao.getOrderByOrderid(orderid);
	}


}
