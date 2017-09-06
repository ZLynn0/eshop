package org.lanqiao.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CodeServlet
 */
@WebServlet(name = "codeServlet", urlPatterns = { "/codeServlet.do" })
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证码：动态生成内存中的一张验证图片（图片中显示的是验证字符）
		String chars ="AQZWSXEDCRFVTGBYHNUJMIKOLPqazwsxedcrfvtgbyhnujmiklop0987654321";
		//创建一个随机对象
		String codes="";
		StringBuilder builder = new StringBuilder();
		Random rand=new Random();
		for (int i = 0; i < 4; i++) {
			int index=rand.nextInt(61);
			builder.append(chars.charAt(index));
			builder.append(" ");
		}
		codes=builder.toString();
		//2、生成一个内存中的图片，在图片中写入验证字符
		//A、创建一个内存中的图片
		BufferedImage bufferedImage=new BufferedImage(95, 25, BufferedImage.TYPE_INT_RGB);
		//B、绘制图片
		//拿到一个画笔-->绘制图片及图片内容；
		Graphics g=bufferedImage.getGraphics();
		//画边框；
		g.setColor(Color.red);
		g.drawRect(1, 1, 95, 25);
		//填充颜色
		g.setColor(Color.white);//修改画笔颜色为白色；
		g.fillRect(1, 1, 93, 23);//填充白色背景
		g.setColor(Color.red);//画笔的颜色为红色；
		g.setFont(new Font("宋体", Font.BOLD, 20));
		//画验证字符
		g.drawString(codes, 10, 20);
		//画干扰线；
		g.setColor(Color.green);
		for (int i = 0; i < 5; i++) {
			g.drawLine(rand.nextInt(90),rand.nextInt(30),rand.nextInt(90),rand.nextInt(30));
		}
		
		//将验证字符存到session；
		request.getSession().setAttribute("code", codes);
		
		//3、输出图片
		//指定输出的为图片数据格式；
		response.setContentType("image/jpeg");
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		response.getOutputStream().flush();
		//response.getWriter().write(builder.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
