package com.itheima.content;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	/*	//1获得单个表单值
		String username = req.getParameter("username");
		System.out.println(username);
		String password = req.getParameter("password");
		System.out.println(password);
		//2获得复选值
		String[] parameterValues = req.getParameterValues("hobby");
		for (String string : parameterValues) {
			System.out.println(string);
		}
		Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			System.out.println(parameterNames.nextElement());
		}*/
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			for (String str : entry.getValue()) {
				System.out.println(str);
			}
			System.out.println("--------");
		}
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}