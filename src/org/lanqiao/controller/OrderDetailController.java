package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import com.google.gson.Gson;

@WebServlet(name = "orderDetailController", urlPatterns = { "/orderdetailcontroller.do" })
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("orderdetail")) {
		OrderDetailService ods =new OrderDetailServiceImpl();
		String orderid=request.getParameter("orderid");
		List<OrderDetail> list=ods.orderdetaillist(orderid);
		//将数据转为json字符串；
		Gson gson=new Gson();
		String json=gson.toJson(list);
		response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
