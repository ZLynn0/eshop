package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.OrderDetail;

public interface OrderDetailService {
	public void insertOrderDetail(OrderDetail detail);
	public List<OrderDetail> orderdetaillist(String orderid);
}
