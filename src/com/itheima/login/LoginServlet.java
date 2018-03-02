package com.itheima.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.register.User;
import com.itheima.utils.DataSourceUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//1获取用户名密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user=null;
		//2调用一个方法用于用户查询
		try {
			user=login(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//3通过user是否为空判断用户名和密码是否正确
		if(user!=null){
			//用户名密码正确
			//跳转到网站首页
			res.sendRedirect(req.getContextPath());
		}else{
			//用户名或者密码错误
			//跳回当前页面login.jsp
			//使用转发，可以在request域中存错误信息    jsp可以看做servlet
			req.setAttribute("loginInfo", "用户名或者密码错误");
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
		
	}
	private User login(String username,String password) throws SQLException{
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=? and password=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username,password);
		return user;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}