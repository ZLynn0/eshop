package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.lanqiao.entity.Cart;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.News;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.Publisher;
import org.lanqiao.entity.User;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.util.CartUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "dispacher", urlPatterns = { "/dispacher.do" })
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if(type!=null&&type.equals("news")&&id!=null){
			org.lanqiao.service.NewsService ns=new org.lanqiao.service.impl.NewsServiceImpl();
			News news=ns.getNewsById(id);
			request.setAttribute("news", news);
			request.getRequestDispatcher("WEB-INF/title.jsp").forward(request, response);
		}else if (type!=null&&type.equals("goods")&&id!=null) {
			org.lanqiao.service.GoodService gs=new org.lanqiao.service.impl.GoodsServiceImpl();
			org.lanqiao.service.CategoryService cs = new org.lanqiao.service.impl.CategoryServiceImpl();
			org.lanqiao.service.PublisherService ps = new org.lanqiao.service.impl.PublisherServiceImpl();
			Goods goods=gs.getGoodsBygid(id);
			String cid=goods.getCid();
			String pid=goods.getPid();
			Publisher pub=ps.getPublisherById(pid);
			Category cate = cs.getCategoryById(cid);
			request.setAttribute("goods", goods);
			request.setAttribute("cate", cate);
			request.setAttribute("pub", pub);
			request.getRequestDispatcher("WEB-INF/detail.jsp").forward(request, response);
			
		}else if(type!=null && type.equals("loginsuccess") ){
			//判断是否有cookie;
			Cookie[] cookies = request.getCookies();
			Cookie userCookie = null;
			if (cookies!=null) {
				for(Cookie c:cookies){
					if(c.getName().equals("uloginid")){
						userCookie = c;
						break;
					}
				}
			}
			
			if(userCookie!=null){
				String uloginid = userCookie.getValue();
				//根据帐号找此用户;
				org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
				PasswordAnswerService  pas =new PasswordAnswerServiceImpl();
				User user =us.getUserByLoginId(uloginid);		
				PasswordAnswer passwordAnswer=pas.getPwdById(user.getUserid());
				request.getSession().setAttribute ("passwordAnswer", passwordAnswer);
				request.getSession().setAttribute ("user", user);
			}
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}else if(type!=null && type.equals("final") ){
			request.getRequestDispatcher("/WEB-INF/myaccount.jsp").forward(request, response);
			
		}
		else if (type!=null&&type.equals("register")) {
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}else if (type!=null&&type.equals("cart")) {
			request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
		}else if (type!=null&&type.equals("order")) {
			request.getRequestDispatcher("WEB-INF/order.jsp").forward(request, response);
		}else if (type!=null&&type.equals("orderfinal")) {
			request.getRequestDispatcher("WEB-INF/orderfinal.jsp").forward(request, response);
		}else if (type!=null&&type.equals("ordersuccess")) {
			//下订单；
			List<CookieItem> list = getItems(request);
			List<Cart> buygoods=CartUtil.convertCookieItemListToCartList(list);
			String orderid = UUID.randomUUID().toString();
			String userid = ((User)request.getSession().getAttribute("user")).getUserid();
			double totalprice=0;
			for (int i = 0; i < buygoods.size(); i++) {
				Cart cart=buygoods.get(i);
				totalprice+=cart.getAmount()*cart.getGsaleprice();
			}
			java.util.Date orderDate=new java.util.Date();
			Order order = new Order(orderid, null, userid, totalprice, orderDate);
			org.lanqiao.service.OrderService os=new org.lanqiao.service.impl.OrderServiceImpl();
			os.insertOrder(order);
			//订单详情；
			org.lanqiao.service.OrderDetailService ods=new org.lanqiao.service.impl.OrderDetailServiceImpl();
			for (Cart c:buygoods) {
				String orderdetailid=UUID.randomUUID().toString();
				String gtitle = c.getGtitle();
				double gsaleprice=c.getGsaleprice();
				int gnumber=c.getAmount();
				OrderDetail detail=new OrderDetail(orderdetailid, gtitle, gsaleprice, gnumber, orderid);
				ods.insertOrderDetail(detail);
			}
			
			
			//清空购物车；
			Cookie[] cookies=request.getCookies();
			Cookie currentcart =null;
			if (cookies!=null) {
				for (Cookie c:cookies) {
					if (c.getName().equals("cart")) {
						currentcart=c;
						break;
					}
				}
				if (currentcart!=null) {
					currentcart.setMaxAge(0);
					response.addCookie(currentcart);
				}
			}
			request.getRequestDispatcher("WEB-INF/ordersuccess.jsp").forward(request, response);
		}else if (type!=null&&type.equals("myaccount")) {
			request.getRequestDispatcher("WEB-INF/myaccount.jsp").forward(request, response);
		}else if (type!=null&&type.equals("modify")) {
			request.getRequestDispatcher("WEB-INF/modifyuserinfo.jsp").forward(request, response);
		}else if (type!=null&&type.equals("modifysuccess")) {
			request.getRequestDispatcher("WEB-INF/modifysuccess.jsp").forward(request, response);
		}else if (type!=null&&type.equals("orderlist")) {
			org.lanqiao.service.OrderService os=new org.lanqiao.service.impl.OrderServiceImpl();
			org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
			String userid = ((User)request.getSession().getAttribute("user")).getUserid();
		
			
			request.getRequestDispatcher("WEB-INF/orderlist.jsp").forward(request, response);
		}else if(type!=null&&type.equals("removesuccess")) {
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
		}else if(type!=null&&type.equals("orderinfo")) {
			request.getRequestDispatcher("/WEB-INF/orderinfo.jsp").forward(request, response);
		}
	}

	private List<CookieItem> getItems(HttpServletRequest request) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
