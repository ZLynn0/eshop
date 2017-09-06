package org.lanqiao.controller;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.util.MailUtils;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "registerServlet", urlPatterns = { "/register.do" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//验证验证码是否正确；
		//从session中取验证与用户输入的验证码进行比对；
		String code =request.getSession().getAttribute("code").toString().toLowerCase().replaceAll(" ","");
		String ucheckcode=request.getParameter("ucheckcode").toLowerCase();
		if (!code.equals(ucheckcode)) {
			request.getRequestDispatcher("/WEB-INF/register.jsp?msg=code eorror").forward(request, response);
			return;
		}
		//处理注册
		String uloginid=request.getParameter("uname");
		String uemail=request.getParameter("uemail");
		String upassword=request.getParameter("upassword");
		//String upasswordagain=request.getParameter("upasswordagain");
		String usex=request.getParameter("usex");
		String utel=request.getParameter("utel");
		String uaddress=request.getParameter("uaddress");
		String userid=UUID.randomUUID().toString();
		String ustateid="B5868B7A06E54DAEB19658343D3A2B28";
		String uroleid="116F9526C319462780B9CA72F6BB9B41";
		
		User user =new User(userid,uemail,uloginid,upassword,usex,uaddress,utel,ustateid,uroleid);
		org.lanqiao.service.UserService us=new org.lanqiao.service.impl.UserServiceImpl();
		us.insertUser(user);
		
		//处理密码保护信息
		String squestion=request.getParameter("squestion");
		String sanswer = request.getParameter("sanswer");
		String ubackupemail=request.getParameter("ubackupemail");
		String answerid = UUID.randomUUID().toString();

		org.lanqiao.service.PasswordAnswerService pas = new org.lanqiao.service.impl.PasswordAnswerServiceImpl();
		org.lanqiao.entity.PasswordAnswer passwordAnswer=new PasswordAnswer(answerid, squestion, sanswer, ubackupemail, userid);
		pas.insertPasswordAnswer(passwordAnswer);
		
	//	发一封激活邮件；
		try {
			MailUtils.sendMail(uemail, "你正在eshop网站注册账号，请点击确认！");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//MailUtil.sendMail(uemail, "注册", "http://localhost:8080/web_project/enableUser.do?userid="+userid);
		request.getRequestDispatcher("/WEB-INF/registersuccess.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
