package cn.etc.fliter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.etc.entity.User;

/**
 * Servlet Filter implementation class AutoLoginFliter
 */
@WebFilter("/AutoLoginFliter")
public class AutoLoginFliter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {		
		HttpServletRequest request = (HttpServletRequest) req;
		//���һ����Ϊ autoLogin ��cookie
		Cookie[] cookies = request.getCookies();
		String autologin = null;
		for(int i = 0; cookies != null && i< cookies.length; i++) {
			if ("autologin".equals(cookies[i].getName())) {
				//�ҵ���ָ����cookie
				autologin = cookies[i].getValue();
				break;
			}
		}
		if (autologin != null) {
			//���Զ���¼
			String[] parts = autologin.split("-");
			String username = parts[0];
			String password = parts[1];
			//����û���������
			if ("itcast".equals(username) && ("123456").equals(password)) {
				//��¼�ɹ������û�״̬user�������session��
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				request.getSession().setAttribute("user", user);
			}
		}
		//����
		chain.doFilter(request, response);
	}


	public void destroy() {
		// TODO Auto-generated method stub
	}

}
