package org.lanqiao.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;
import org.lanqiao.service.impl.NewsServiceImpl;

import com.google.gson.Gson;


@WebServlet(name = "newsController", urlPatterns = { "/newscontroller.do" })
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type!=null && type.equals("list")) {
		NewsService ns=new NewsServiceImpl();
		List<News> list=ns.newsList();
		//将数据转为json字符串；
		Gson gson=new Gson();
		String json=gson.toJson(list);
		response.getWriter().write(json);
		}else if (type!=null && type.equals("remove")) {
			NewsService ns=new NewsServiceImpl();
			String tid =request.getParameter("tid");
			ns.removenews(tid);
			response.getWriter().write("1");
		}else if (type!=null && type.equals("add")) {
			NewsService ns=new NewsServiceImpl();
			String tid =request.getParameter("tid");
			String title = request.getParameter("title");
			String tcontent =request.getParameter("tcontent");
			String tpubdate=request.getParameter("tpubdate");
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate=null;
			try {
				utilDate = format.parse(tpubdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			News news=new News(tid, title, tcontent, utilDate);
			ns.insertnews(news);
			response.getWriter().write("1");
		}else if (type!=null && type.equals("edit")) {
			NewsService ns=new NewsServiceImpl();
			String tid =request.getParameter("tid");
			String title = request.getParameter("title");
			String tcontent =request.getParameter("tcontent");
			News news=ns.getNewsById(tid);
			news.setTitle(title);
			news.setTcontent(tcontent);
			//news.setTpubdate(tpubdate);

			ns.updatenews(news);
			response.getWriter().write("1");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
