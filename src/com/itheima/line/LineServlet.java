package com.itheima.line;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1获取请求方式
		String method = req.getMethod();
		System.out.println(method);
		//2获得请求的资源相关的内容】
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		StringBuffer requestURL = req.getRequestURL();
		System.out.println(requestURL);
		//3获得web应用的名称
		String contextPath = req.getContextPath();
		System.out.println("web应用"+contextPath);
		//地址后的参数的字符串
		System.out.println(req.getQueryString());
		//获得客户机的信息   获得访问者ip地址
		System.out.println(req.getRemoteAddr());
		
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}