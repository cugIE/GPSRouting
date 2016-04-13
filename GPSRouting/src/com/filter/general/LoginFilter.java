package com.filter.general;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req=(HttpServletRequest) request;

		HttpServletResponse res=(HttpServletResponse) response;

		//获得请求的URL

		String url=req.getRequestURL().toString();



		//获得session中的对象

		HttpSession session= req.getSession();

		//使用endsWith()判断url结尾的字符串


		if( url.endsWith(".css") || url.endsWith(".js")|| url.endsWith(".gif")|| 
				url.endsWith(".png")|| url.endsWith(".jpg")|| url.endsWith("#")){
		
		

		//满足条件就继续执行

		chain.doFilter(request, response);

		}else if (session.getAttribute("SesId")!=null){
			if(url.endsWith("login.jsp")){
				ServletContext sc = request.getServletContext();
		        RequestDispatcher rd = null;
		        rd = sc.getRequestDispatcher("/index.jsp");
		        rd.forward(request, response);
			}
			else{
				chain.doFilter(request, response);
			}
		}else{

		//不满足条件就跳转到其他页面

		PrintWriter out=res.getWriter();

		out.print("<script language>alert('请登录！…………');top.location.href='login.jsp'</script>");
		//
		res.sendRedirect(req.getContextPath() + "/login.jsp"); 



		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
