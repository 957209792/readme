package org.mail.servlet.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mail.common.ResponseBody;
import org.mail.entity.Users;
import org.mail.service.user.UsersService;
import org.mail.util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置默认为false
		boolean exists = false;
		System.out.println("进来了");
		// 获取登录数据
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		// 获取cookie
		Cookie[] cookies = request.getCookies();
		
		//创建状态码对象
		ResponseBody responseBody = new ResponseBody();

		// 验证是否为空
		if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password)) {
			response.getWriter().write("<script>alert(\"账号密码不能为空！\");</script>");
			return;
		}

		// 创建业务类对象
		UsersService usersService = new UsersService();

		// 获取数据库中List集合里的元素users,调用业务类
		Users users = usersService.searchUserByAccount(account);
		// 验证users不为空
		if (null != users) {
			// 验证account
			if (account.equals(users.getUACCOUNT())) {
				// 验证password
				if (password.equals(users.getUPASSWORD())) {
					System.out.println("登陆成功！");
					// session对象： 表示会话,负责跟踪管理维护客户端会话信息(会话ID,会话状态,自定义会话数据)
					// 获取会话并存储
					// session.setAttribute("account", account);
					request.getSession().setAttribute("account", account);
					request.getSession().setAttribute("password", password);
					//给responseBody响应类的Message字段设置了200状态码
					responseBody.setMessage("200");
					
					//把响应类responseBody封装成对象放入Json中并返回给login.js
					response.getWriter().write(responseBody.toJson());
				}
			}
		} else {
			responseBody.setMessage("1");
			response.getWriter().write(responseBody.toJson());
		}
	}
}
