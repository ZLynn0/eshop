package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;


@WebServlet(name = "loginServlet", urlPatterns = { "/loginServlet.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理登录；
		String uloginid=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		
		org.lanqiao.service.UserService us=new org.lanqiao.service.impl.UserServiceImpl();
		org.lanqiao.service.PasswordAnswerService pas=new org.lanqiao.service.impl.PasswordAnswerServiceImpl();
		User user=us.login(uloginid, upwd);
		PasswordAnswer passwordAnswer=pas.getPwdById(user.getUserid());
		if (user!=null) {
			//验证通过；
			request.getSession().setAttribute("user", user);
			//处理记住密码；
			String chk=request.getParameter("chk");
			if (chk!=null) {
				//直接将账号写入cookie；
				Cookie cookie=new Cookie("uloginid", uloginid);
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
			}
		}
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
