package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lanqiao.entity.UserRole;
import com.google.gson.Gson;

@WebServlet(name = "uroleController", urlPatterns = { "/urolecontroller.do" })
public class UroleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("list")) {
			org.lanqiao.service.UserRoleService urs = new org.lanqiao.service.impl.UserRoleServiceImpl();
			List<UserRole> list=urs.userRoleList();
			//将数据转为json字符串；
			Gson gson=new Gson();
			String json=gson.toJson(list);
			response.getWriter().write(json);
		}else if(type!=null && type.equals("remove")){
			org.lanqiao.service.UserRoleService urs = new org.lanqiao.service.impl.UserRoleServiceImpl();
			String uroleid=request.getParameter("uroleid");
			urs.removeUserRole(uroleid);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("add")){
			String uroleid=request.getParameter("uroleid");
			String urolename=request.getParameter("urolename");		
			UserRole userRole =new UserRole(uroleid, urolename);
			org.lanqiao.service.UserRoleService urs = new org.lanqiao.service.impl.UserRoleServiceImpl();
			urs.insertUserRole(userRole);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("edit")){
			String uroleid=request.getParameter("uroleid");
			String urolename=request.getParameter("urolename");
			org.lanqiao.service.UserRoleService urs = new org.lanqiao.service.impl.UserRoleServiceImpl();
			UserRole userRole=urs.getUserRoleByUroleid(uroleid);
			userRole.setUroleid(uroleid);
			userRole.setUrolename(urolename);
			urs.updateUserRole(userRole);
			response.getWriter().write("1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
