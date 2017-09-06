package test;

import java.util.List;

import org.junit.Test;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;

public class UserListTest {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
////		UserService us = new UserServiceImpl();
////		List<User> list=us.userList();
////		for (int i = 0; i <list.size(); i++) {
////			System.out.println(list.get(i).toString());
////		}
//
//	}
	@Test
	public void testuser(){
		OrderDetailService ods=new OrderDetailServiceImpl();
		List<OrderDetail> list=ods.orderdetaillist("f8d280fb-f07a-41b0-baf6-283ddee850f2");
		System.out.println(list.size());
	}

}
