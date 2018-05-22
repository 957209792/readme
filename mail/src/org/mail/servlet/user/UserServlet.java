package org.mail.servlet.user;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mail.cache.CacheContext;
import org.mail.common.ResponseBody;
import org.mail.entity.Email;
import org.mail.service.other.EmailService;
import org.mail.service.user.UsersService;
import org.mail.util.StringUtil;
import org.mail.util.VerificationCodeUtil;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * 用户Servlet
 * @author VIC
 *
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		// 验证账号唯一性
		ResponseBody responseBody = new ResponseBody();
		UsersService usersService = new UsersService();
		boolean exists = false;
		if(StringUtil.isNotEmpty(email)){
			exists = usersService.checkedEmail(email);	
		}else{
			exists = usersService.checkedPhone(phone);
		}
		
		if(exists){
			responseBody.setStatus(-1);
			responseBody.setMessage("邮箱或手机已经被使用了.");
			resp.getWriter().write(responseBody.toJson());
			return;
		}
	}

	/**
	 * 注册用户
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 获取注册数据
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String validateCode = req.getParameter("validateCode");
		
		// 验证注册数据
		ResponseBody responseBody = new ResponseBody();
		if(StringUtil.isEmpty(password) || StringUtil.isEmpty(validateCode) || (StringUtil.isEmpty(phone) && StringUtil.isEmpty(email))){
			responseBody.setStatus(-1);
			responseBody.setMessage("注册参数不能为空.");
			resp.getWriter().write(responseBody.toJson());
			return;
		}
		
		// 验证验证码
		String key = VerificationCodeUtil.VERIFICATION_CODE_PREFIX + validateCode;
		String code = CacheContext.getCache(key.toUpperCase(), String.class);
		if(StringUtil.isEmpty(code) || !code.equalsIgnoreCase(validateCode)){
			responseBody.setStatus(-1);
			responseBody.setMessage("验证码过期或验证码错误");
			resp.getWriter().write(responseBody.toJson());
			return;
		}else{
			//  移除验证码
			CacheContext.removeCache(key);
		}
		
		// 验证账号唯一性
		UsersService usersService = new UsersService();
		boolean exists = false;
		if(StringUtil.isNotEmpty(email)){
			exists = usersService.checkedEmail(email);	
		}else{
			exists = usersService.checkedPhone(phone);
		}
		
		if(exists){
			responseBody.setStatus(-1);
			responseBody.setMessage("邮箱或手机已经被使用了.");
			resp.getWriter().write(responseBody.toJson());
			return;
		}
		
		// 调用业务类进行注册
		int modify = 0;
		if(StringUtil.isNotEmpty(email)){
			modify = usersService.registerEmail(email,password);	
		}else{
			modify = usersService.registerPhone(phone,password);
		}
		
		if(modify>0){
			// 注册用户成功后生成邮件通知内容
			EmailService emailService = new EmailService();
			emailService.addEmail(email,Email.REGISTER);
			responseBody.setMessage("恭喜您,注册成功");
		}else{
			responseBody.setMessage("对不起!,注册失败");
		}
		// 返回注册结果
		resp.getWriter().write(responseBody.toJson());
	}

	/**
	 * 登录
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPut(req, resp);
	}
	
	

}
