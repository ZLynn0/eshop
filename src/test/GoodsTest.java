package test;

import java.util.Date;

import org.junit.Test;
import org.lanqiao.dao.impl.OrderDaoImpl;
import org.lanqiao.entity.Order;


public class GoodsTest {
	@Test
	public void testGoods(){
		org.lanqiao.dao.impl.OrderDaoImpl orderDaoImpl =new OrderDaoImpl();
		Order order=new Order("1","1", "1", 10, new Date());
		orderDaoImpl.insert(order);
	}
}
