package cn.etc.fliter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.management.RuntimeErrorException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharacterFliter
 */
@WebFilter("/*")
public class CharacterFliter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}



	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //�������е����� ���ȫվ��������
        //ָ��request �� response �ı���
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//��request ���а�װ
        CharacterRequest characterRequest = new CharacterRequest(request);
		chain.doFilter(request, response);
	}
	
	class CharacterRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		public CharacterRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			// TODO Auto-generated constructor stub
		}
		//����̳и���һ���ḲдһЩ�������˴�������дgetParameter��������
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if (value == null) {
				return null;
			}
				String method = super.getMethod();
				if ("get".equalsIgnoreCase(method)) {
					try {
						value = new String(value.getBytes("iso-8859-1") , "utf-8");
						
								
					}catch (UnsupportedEncodingException e) {
						// TODO: handle exception
						throw new RuntimeException(e);
					}
				}
				
			return value;
		}	
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}


}
