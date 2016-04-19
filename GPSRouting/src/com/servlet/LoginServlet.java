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
import com.util.TeamidtoName;

public class LoginServlet extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		//登录信息保存于bean中
		People p = new People();
		p.setUsername(username);
		p.setPassword(password);
		
		//创建session，将登录信息保存于session中
	/*	HttpSession session = request.getSession();
		String sessionId = session.getId();
		if (session.isNew()) {
			response.getWriter().print("session创建成功，session的id是："+sessionId);
		} else {
			response.getWriter().print("服务器已经存在该session了，session的id是："+sessionId);
		}*/
		try {
			PeopleService ps = new PeopleService();
			if (ps.check(p)) {
				System.out.println("登录成功");
				System.out.println("人员姓名："+p.getName());
				System.out.println("人员所属部门："+p.getBranchId());
				System.out.println("人员备注信息："+p.getPeopRemark());
				System.out.println("人员组id："+p.getTeamId());
				
			//	response.sendRedirect(request.getContextPath()+"/index.jsp");
				
			//	out.println("登录成功！");
			//	out.println(username);
			//	out.println(password);	
				TeamidtoName teamidtoName = new TeamidtoName();
				String teamName = "";
				teamName = teamidtoName.idtoname(p.getTeamId());
				System.out.println("组类型："+teamName);
				
				int branchid = Integer.parseInt(p.getBranchId());
				int teamid = Integer.parseInt(p.getTeamId());
				
				request.getSession().setAttribute("SesUser",p.getUsername());
				request.getSession().setAttribute("SesPwd", p.getPassword());
				request.getSession().setAttribute("SesId", p.getId());
				request.getSession().setAttribute("SesName", p.getName());
				request.getSession().setAttribute("SesBranchId", branchid);
				request.getSession().setAttribute("SesTeamId", teamid);
				request.getSession().setAttribute("SesTeamName", teamName);
				out.print("<script>" + "alert('登录成功');"+ "document.location.href='index.jsp';"+ "</script>");
				
				/*session.setAttribute("SesUser", p.getUsername());
				session.setAttribute("SesPwd", p.getPassword());
				session.setAttribute("SesId", p.getId());
				session.setAttribute("SesName", p.getName());
				session.setAttribute("SesBranchId", p.getBranchId());
				session.setAttribute("SesTeamId", p.getTeamId());*/
			} else {
				out.println("登录失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.println("</B></font></center>");
			out.close();
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
		processRequest(request,response);
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
		processRequest(request,response);
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
