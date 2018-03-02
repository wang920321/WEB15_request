package com.itheima.register;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.itheima.utils.DataSourceUtils;



public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置req的编码  -----只适合post方式提交
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		System.out.println(username);
		//get 方式的乱码解决
		//先用iso-8859-1编码，再用utf-8解码
		//username=new String(username.getBytes("iso-8859-1"),"UTF-8");
		
		//1获取数据
		Map<String, String[]> properties = req.getParameterMap();
		
		//2将散装数据封装到javabean
	    //BeanUtils工作原理：将map中的数据根据key与实体的属性的对应关系封装
		//只要key的名字与实体的属性 的名字一样就自动封装到实体中
		User user=new User();
		try {
			BeanUtils.populate(user, properties);
			//现在这个位置user对象已经封装好
			//手动封装没有的数据uid----uuid随机的不重复的字符串32位---Java代码生成后为36位
			
			user.setUid(UUID.randomUUID().toString());
		
			//3将参数传递给一个业务操作方法
			
			register(user);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//4认为注册成功跳转到登录页面
		res.sendRedirect(req.getContextPath()+"/login.jsp");
		
	}
	//注册的方法
		private void register(User user) throws SQLException{
			QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
			
			runner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
					user.getEmail(),null,user.getBirthday(),user.getSex(),null,null);
		}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}