package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.OrderDetail;

public interface OrderDetailDao {
	public void insertOrderDetail(OrderDetail detail);
	public List<OrderDetail> list(String orderid);
}
