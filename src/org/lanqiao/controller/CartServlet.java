package org.lanqiao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.CookieItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@WebServlet(name = "cartServlet", urlPatterns = { "/cart.do" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type =request.getParameter("type");
		if (type==null) {
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("buy")) {
			//1、将商品添加到购物车(cookie)|更新购物车的商品//{gid:1,amount:1}
			String gid=request.getParameter("gid");
			CookieItem item=new CookieItem(gid, 1);
			addItem(item, request, response);
			//2、获取购物车中所有商品；
			//List<CookieItem> cart = getItems(request);
			//3、存到request域对象，转到cart.jsp显示车中的商品
			//request.setAttribute("cart", cart);
			//removeItem(gid, request, response);
			request.getRequestDispatcher("/WEB-INF/addsuccess.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("remove")) {
			//1、将商品添加到购物车(cookie)|更新购物车的商品//{gid:1,amount:1}
			String gid=request.getParameter("gid");
			removeItem(gid, request, response);
			request.getRequestDispatcher("/WEB-INF/removesuccess.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("removesuccess")) {
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//从购物车中取出所有的商品
	private List<CookieItem> getItems(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		if(cookies==null) return null;
		Cookie cart=null;
		for (Cookie c:cookies) {
			if (c.getName().equals("cart")) {
				cart=c;
			}
		}
		if (cart==null) {
			return null;
		}
		String json =cart.getValue();//json格式商品数据；
		Gson gson=new Gson();
		TypeToken<List<CookieItem>> listType=new TypeToken<List<CookieItem>>(){//指定集合元素类型；
			
		};
		List<CookieItem> list =gson.fromJson(json, listType.getType());
		return list;
	}
	//将商品添加到购物车
	private void addItem(CookieItem item,HttpServletRequest request, HttpServletResponse response){
		List<CookieItem> list=getItems(request);
		//第一次向购物车添加商品；
		if(list==null){
			list=new ArrayList<CookieItem>();
			list.add(item);
		}else {//表示购物车不为空（有商品）
		
			CookieItem currItem=null;
			for (CookieItem goods:list) {
				if (goods.getGid().equals(item.getGid())) {//存在此商品；
					currItem=goods;
					break;
					
				}
				
			}
			if (currItem==null) {//说明购物车中没有此商品
				list.add(item);			
			}else {
				currItem.setAmount(currItem.getAmount()+1);//更新数量
			}
		}
		//重新将数据写入到cookie；
		Gson gson=new Gson();
		String json = gson.toJson(list);
		Cookie cookie=new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}
	//从购物车中删除商品
	private void removeItem(String gid,HttpServletRequest request, HttpServletResponse response){
		List<CookieItem> list=getItems(request);
		if (list==null) {
			return;
		}
		CookieItem currentItem =null;
		for(CookieItem goods:list){
			if (goods.getGid().equals(gid)) {//存在此商品;
				currentItem=goods;
				break;
			}
		}
		if (currentItem==null) {//说明购物车中没有此商品;
			return;
		}else {
			list.remove(currentItem);
		}
		//重新写入cookie;
		Gson gson=new Gson();
		String json=gson.toJson(list);
		Cookie cookie =new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}
	
}
