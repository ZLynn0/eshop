package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;

import com.google.gson.Gson;

@WebServlet(name = "userController", urlPatterns = { "/usercontroller.do" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("list")) {
			org.lanqiao.service.UserService us = new org.lanqiao.service.impl.UserServiceImpl();
			List<User> list=us.userList();
			//将数据转为json字符串；
			Gson gson=new Gson();
			String json=gson.toJson(list);
			response.getWriter().write(json);
		}else if(type!=null && type.equals("remove")){
			org.lanqiao.service.UserService us = new org.lanqiao.service.impl.UserServiceImpl();
			String userid=request.getParameter("userid");
			us.removeUser(userid);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("add")){
			String uloginid=request.getParameter("uname");
			String uemail=request.getParameter("uemail");
			String upassword=request.getParameter("upassword");
			String usex=request.getParameter("usex");
			String utel=request.getParameter("utel");
			String uaddress=request.getParameter("uaddress");
			String userid=UUID.randomUUID().toString();
			String ustateid="B5868B7A06E54DAEB19658343D3A2B28";
			String uroleid="116F9526C319462780B9CA72F6BB9B41";
			
			User user =new User(userid,uemail,uloginid,upassword,usex,uaddress,utel,ustateid,uroleid);
			org.lanqiao.service.UserService us=new org.lanqiao.service.impl.UserServiceImpl();
			us.insertUser(user);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("edit")){
			String uloginid=request.getParameter("uname");
			String uemail=request.getParameter("uemail");
			String upassword=request.getParameter("upassword");
			String usex=request.getParameter("usex");
			String utel=request.getParameter("utel");
			String uaddress=request.getParameter("uaddress");
			String userid=request.getParameter("userid");
			org.lanqiao.service.UserService us=new org.lanqiao.service.impl.UserServiceImpl();
			User user=us.getUserByUserid(userid);
			user.setUaddress(uaddress);
			user.setUemail(uemail);
			user.setUpassword(upassword);
			user.setUsex(usex);
			user.setUtel(utel);
			user.setUloginid(uloginid);
			us.updateUser(user);
			response.getWriter().write("1");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
