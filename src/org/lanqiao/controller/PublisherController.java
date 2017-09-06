package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lanqiao.entity.Publisher;
import com.google.gson.Gson;


@WebServlet(name = "publisherController", urlPatterns = { "/publishercontroller.do" })
public class PublisherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("list")) {
			org.lanqiao.service.PublisherService ps = new org.lanqiao.service.impl.PublisherServiceImpl();
			List<Publisher> list=ps.publisherList();
			//将数据转为json字符串；
			Gson gson=new Gson();
			String json=gson.toJson(list);
			response.getWriter().write(json);
		}else if(type!=null && type.equals("remove")){
			org.lanqiao.service.PublisherService ps = new org.lanqiao.service.impl.PublisherServiceImpl();
			String pid=request.getParameter("pid");
			ps.removePublisher(pid);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("add")){
			String pid=request.getParameter("pid");
			String pname=request.getParameter("pname");		
			Publisher publisher=new Publisher(pid, pname);
			org.lanqiao.service.PublisherService ps = new org.lanqiao.service.impl.PublisherServiceImpl();
			ps.insertPublisher(publisher);
			response.getWriter().write("1");
		}else if(type!=null && type.equals("edit")){
			String pid=request.getParameter("pid");
			String pname=request.getParameter("pname");
			org.lanqiao.service.PublisherService ps = new org.lanqiao.service.impl.PublisherServiceImpl();
			Publisher publisher=ps.getPublisherById(pid);
			publisher.setPid(pid);
			publisher.setPname(pname);
			ps.updatePublisher(publisher);
			response.getWriter().write("1");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
