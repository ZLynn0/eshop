package org.lanqiao.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodService;
import org.lanqiao.service.impl.GoodsServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "goodsController", urlPatterns = { "/goodscontroller.do" })
public class GoodsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("list")) {
		GoodService gs =new GoodsServiceImpl();
		String cid=request.getParameter("cid");
		int pagesize=Integer.parseInt(request.getParameter("rows"));
		int pageindex=Integer.parseInt(request.getParameter("page"))-1;//从0开始；
		PageInfo pageinfo=gs.goodsList(cid, pagesize, pageindex);
		//easyui datagrid 分页的数据格式要求{ total:100,rows:datas}
		Map<String, Object> map=new HashMap<String,Object>();

		map.put("total", pageinfo.getTotalNumber());
		map.put("rows", pageinfo.getDatas());
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson(map));
		}else if (type!=null && type.equals("remove")) {
			GoodService gs=new GoodsServiceImpl();
			String gid=request.getParameter("gid");
			gs.removeGoods(gid);
			response.getWriter().write("1");
		}else if (type!=null && type.equals("add")) {
			GoodService gs=new GoodsServiceImpl();
			String gid =request.getParameter("gid");
			String gtitle =request.getParameter("gtitle");
			String gauthor =request.getParameter("gauthor");
			String gsaleprice =request.getParameter("gsaleprice");
			String ginprice =request.getParameter("ginprice");
			String gdesc =request.getParameter("gdesc");
			String gimg =request.getParameter("gimg");
			String gclicks =request.getParameter("gclicks");
			String cid =request.getParameter("cid");
			String pid =request.getParameter("pid");
			Goods goods=new Goods(gid, gtitle, gauthor, Integer.parseInt(gsaleprice), Integer.parseInt(ginprice), gdesc, gimg, Integer.parseInt(gclicks), cid, pid);
			gs.insertGoods(goods);
			response.getWriter().write("1");
		}else if (type!=null && type.equals("edit")) {
			GoodService gs=new GoodsServiceImpl();
			String gid =request.getParameter("gid");
			String gtitle =request.getParameter("gtitle");
			String gauthor =request.getParameter("gauthor");
			String gsaleprice =request.getParameter("gsaleprice");
			String ginprice =request.getParameter("ginprice");
			String gdesc =request.getParameter("gdesc");
			String gimg =request.getParameter("gimg");
			String gclicks =request.getParameter("gclicks");
			String cid =request.getParameter("cid");
			String pid =request.getParameter("pid");
			Goods goods=gs.getGoodsBygid(gid);
			goods.setCid(cid);
			goods.setGauthor(gauthor);
			goods.setGclicks(Integer.parseInt(gclicks));
			goods.setGdesc(gdesc);
			goods.setGinprice(Integer.parseInt(ginprice));
			goods.setGsaleprice(Integer.parseInt(gsaleprice));
			goods.setGimg(gimg);
			goods.setGtitle(gtitle);
			goods.setPid(pid);
			gs.updateGoods(goods);
			response.getWriter().write("1");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
