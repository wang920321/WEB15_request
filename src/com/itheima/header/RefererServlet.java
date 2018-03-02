package com.itheima.header;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefererServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//对改新闻的来源进行判断
		String header = req.getHeader("referer");
		if(header!=null&&header.startsWith("http://localhost")){
			//是从我自己的网站跳转过去的可以看新闻
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().write("你很牛逼");
		}else{
			res.getWriter().write("你是sb");
		}
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
