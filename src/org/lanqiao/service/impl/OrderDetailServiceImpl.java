package org.lanqiao.service.impl;


import java.util.List;

import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	private org.lanqiao.dao.OrderDetailDao dao=new org.lanqiao.dao.impl.OrderDetaildaoImpl();
	@Override
	public void insertOrderDetail(OrderDetail detail) {
		dao.insertOrderDetail(detail);
		
	}
	@Override
	public List<OrderDetail> orderdetaillist(String orderid) {
		// TODO Auto-generated method stub
		return dao.list(orderid);
	}



}
