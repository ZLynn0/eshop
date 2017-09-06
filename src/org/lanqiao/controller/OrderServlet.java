package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderServiceImpl;

@WebServlet(name = "orderServlet", urlPatterns = { "/order.do" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		OrderService os = new OrderServiceImpl();
		List<Order> orderlist=os.orderlist(user.getUserid());
		request.setAttribute("orderlist", orderlist);
		request.getRequestDispatcher("/WEB-INF/orderlist.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
