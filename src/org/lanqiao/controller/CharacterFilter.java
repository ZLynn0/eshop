package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter {
	private  FilterConfig  config=null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//获取初始化参数
		String charset =config.getInitParameter("charset");
		// 1、设置服务器处理字节编码
		request.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		//System.out.println("我被拦截了。。。。");
		chain.doFilter(request, response);
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
		
	}

}
