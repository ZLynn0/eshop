package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

import com.google.gson.Gson;

@WebServlet(name = "adminGoodsServlet", urlPatterns = { "/admingoods.do" })
public class AdminGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=request.getParameter("cid");
		String pageindex=request.getParameter("pageIndex");
		if (pageindex==null ) {//如果类别没数，加载默认类别；
			//	int endindex=pagesize*pageindex+pagesize;在goodsdaoimpl改
		//	int startindex=pageindex*pagesize;
			pageindex="0";
		}
		if (cid==null) {//默认拿此类别的第一页数据
			cid="1";
		}
		String pagesize=request.getParameter("pageSize");
		if (pagesize==null) {
			pagesize="10";
		}
		org.lanqiao.service.GoodService gs=new org.lanqiao.service.impl.GoodsServiceImpl();
		PageInfo<Goods> pageinfo =gs.goodsList(cid, Integer.parseInt(pagesize), Integer.parseInt(pageindex));
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson(pageinfo));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
