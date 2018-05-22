package org.mail.servlet.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mail.common.ResponseBody;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResponseBody responseBody = new ResponseBody();
		//清理session
		request.getSession().setAttribute("account", "");
	    request.getSession().removeAttribute("account");
	    request.getSession().setAttribute("password", "");
	    request.getSession().removeAttribute("password");
	    
	    responseBody.setStatus(200);
	    response.getWriter().write(responseBody.toJson());
	   // response.sendRedirect("home/pay.jsp");
	}

}
