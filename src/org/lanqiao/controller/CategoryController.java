package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;
import org.lanqiao.service.impl.CategoryServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "categoryController", urlPatterns = { "/categorycontroller.do" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");	
		if (type!=null && type.equals("list")) {
			CategoryService cs=new CategoryServiceImpl();
			List<Category> list=cs.categoryList();
			//将数据转为json字符串；
			Gson gson=new Gson();
			String json=gson.toJson(list);
			response.getWriter().write(json);
		}else if(type!=null && type.equals("remove")){
			CategoryService cs=new CategoryServiceImpl();
			String cid=request.getParameter("cid");
			cs.removeCategory(cid);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("add")){
			String cid=request.getParameter("cid");
			String cname=request.getParameter("cname");		
			Category category=new Category(cid, cname);
			CategoryService cs=new CategoryServiceImpl();
			cs.insertCategory(category);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("edit")){
			String cid=request.getParameter("cid");
			String cname=request.getParameter("cname");
			CategoryService cs=new CategoryServiceImpl();
			Category category=cs.getCategoryById(cid);
			category.setCid(cid);
			category.setCname(cname);
			cs.updateCategory(category);
			response.getWriter().write("1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
