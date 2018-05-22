package org.mail.servlet.other;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mail.cache.CacheContext;
import org.mail.common.ResponseBody;
import org.mail.util.ImageUtil;
import org.mail.util.StringUtil;
import org.mail.util.VerificationCode;
import org.mail.util.VerificationCodeUtil;

/**
 * 验证码Servlet
 */
@WebServlet("/validateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 检查验证码
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResponseBody responseBody = new ResponseBody();
		String validateCode = req.getParameter("validateCode");
		if(StringUtil.isEmpty(validateCode)){
			responseBody.setStatus(-1);
			responseBody.setMessage("验证码为空");
			resp.getWriter().write(responseBody.toJson());
			return;
		}
		
		// 验证验证码
		String key = VerificationCodeUtil.VERIFICATION_CODE_PREFIX + validateCode;
		String code = CacheContext.getCache(key.toUpperCase(), String.class);
		if(StringUtil.isNotEmpty(code) && code.equalsIgnoreCase(validateCode)){
			responseBody.setMessage("验证码正确");
			resp.getWriter().write(responseBody.toJson());
			return;
		}else{
			responseBody.setStatus(-1);
			responseBody.setMessage("验证码过期或不存在");
			resp.getWriter().write(responseBody.toJson());
		}
	}

	/**
	 * 获取验证码
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		// 创建响应对象
		ResponseBody responseBody = new ResponseBody();
		// 生成验证码对象
		VerificationCode verificationCode = VerificationCodeUtil.generateVerificationCode(200, 50);
		// 将验证码保存到缓存中
		String key = VerificationCodeUtil.VERIFICATION_CODE_PREFIX + verificationCode.getCode();
		CacheContext.setCache(key.toUpperCase(), verificationCode.getCode());
		// 将验证码图片转换成base64位编码并放入响应对象
		responseBody.setData(ImageUtil.encodeToBase64(verificationCode.getImage()));
		responseBody.setMessage("获取验证码成功.");
		response.getWriter().write(responseBody.toJson());
	}

}
