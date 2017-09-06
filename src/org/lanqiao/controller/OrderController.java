package org.lanqiao.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "orderController", urlPatterns = { "/ordercontroller.do" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("list")) {
		OrderService os =new OrderServiceImpl();
		List<Order> list=os.orderlist();
		//将数据转为json字符串；
		Gson gson=new Gson();
		String json=gson.toJson(list);
		response.getWriter().write(json);
		}else if (type!=null && type.equals("remove")) {
			OrderService os =new OrderServiceImpl();
			String orderid =request.getParameter("orderid");
			os.removeorder(orderid);
			response.getWriter().write("1");
		}else if (type!=null && type.equals("add")) {
			OrderService os =new OrderServiceImpl();
			String orderid =request.getParameter("orderid");
			String userid = request.getParameter("userid");
			String gid =request.getParameter("gid");
			String totalprice =request.getParameter("totalprice");
			String orderdate=request.getParameter("orderDate");
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate=null;
			try {
				utilDate = format.parse(orderdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Order order=new Order(orderid, gid, userid, Integer.parseInt(totalprice), utilDate);
			os.insertOrder(order);
			response.getWriter().write("1");
		}else if (type!=null && type.equals("edit")) {
			OrderService os =new OrderServiceImpl();
			String orderid =request.getParameter("orderid");
			String userid = request.getParameter("userid");
			String gid =request.getParameter("gid");
			String totalprice =request.getParameter("totalprice");
			Order order=os.getOrderByOrderid(orderid);
			order.setGid(gid);
			order.setOrderid(orderid);
			String orderdate=request.getParameter("orderDate");
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate=null;
			try {
				utilDate = format.parse(orderdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			order.setOrderDate(utilDate);
			order.setTotalprice(Integer.parseInt(totalprice));
			order.setUserid(userid);
			os.updateorder(order);
			response.getWriter().write("1");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
