package cn.etc.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�û�ע��
		request.getSession().removeAttribute("user");
		//�ӿͻ���ɾ���Զ���¼��cookie
		Cookie cookie = new Cookie("autologin", "msg");
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
	}

}
