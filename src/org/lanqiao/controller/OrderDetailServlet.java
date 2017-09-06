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


@WebServlet(name = "orderdetailServlet", urlPatterns = { "/orderdetail.do" })
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid=request.getParameter("orderid");
		OrderDetailService ods=new OrderDetailServiceImpl();
		List<OrderDetail> orderdetaillist=ods.orderdetaillist(orderid);
		request.setAttribute("orderdetaillist", orderdetaillist);
		request.getRequestDispatcher("/WEB-INF/orderinfo.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
