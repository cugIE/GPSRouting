package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.People;
import com.service.PeopleService;

public class ModPwdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModPwdServlet(){
		super();
	}
	
	private void start(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session= request.getSession();
		
		PrintWriter out = response.getWriter();
		
		String username = (String) session.getAttribute("SesUser");
		System.out.println(username);
		String oldpwd = request.getParameter("old_pwd");
		String newpwd = request.getParameter("new_pwd");
		
		People people = new People();
		people.setUsername(username);
		people.setPassword(oldpwd);
		
		
		try {
			PeopleService ps = new PeopleService();
			if (ps.check(people)) {
				try {
					ps.update(people, newpwd);
					out.print("<script>" + "alert('修改成功，请重新登录！');"+ "document.location.href='ExitServlet';"+ "</script>");
					//System.out.println("修改成功！");
				} catch (Exception e) {
					// TODO: handle exception
					out.print("修改密码失败");
				}		
			}else {
				out.print("密码不正确");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
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
			start(request, response);
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
			start(request, response);
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
