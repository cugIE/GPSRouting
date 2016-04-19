package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.PeopleService;

public class ExitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public ExitServlet(){
		super();
	}

	private void loginout(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session1 = request.getSession(false);
		if(session1 == null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		//	session1.removeAttribute("user");
			session1.removeAttribute("SesUser");
			session1.removeAttribute("SesPwd");
			session1.removeAttribute("SesId");
			session1.removeAttribute("SesName");
			session1.removeAttribute("SesBranchId");
			session1.removeAttribute("SesTeamId");
		//	session1.invalidate();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		try {
			loginout(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		try {
			loginout(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

}
