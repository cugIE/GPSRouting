package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.bean.People;
import com.bean.People;
import com.service.PeopleService;
import com.service.PeopleService;

public class ManagePeopleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagePeopleServlet() {
		super();
	}

	private void start(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PeopleService service = new PeopleService();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		System.out.println("方法：" + action);
		System.out.println("方法id：" + id);
		
		Logger log = Logger.getLogger(ManageBranchServlet.class);
		/**
		 * 添加
		 */
		if (action.equals("add")) {
			response.setContentType("text/html;charset=utf-8");
			// String People_id = request.getParameter("People_id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String people_code = request.getParameter("peoplecode");
			String branch_id = request.getParameter("branchid");
			String team_id = request.getParameter("teamid");
			System.out.println(username);
			System.out.println(password);
			System.out.println(name);
			System.out.println(people_code);
			System.out.println(branch_id);
			System.out.println(team_id);

			People p = new People();
			p.setUsername(username);
			p.setPassword(password);
			p.setName(name);
			p.setCode(people_code);
			p.setBranchId(branch_id);
			p.setTeamId(team_id);
			
			
			PeopleService peopleService = new PeopleService();
			try {
				peopleService.add(p);
				PrintWriter out = response.getWriter();
				out.print("<script>" + "alert('添加成功');"+ "document.location.href='data-people.jsp';"+ "</script>");
			//	out.print("添加成功");
				log.error("新增人员");
			} catch (SQLException e) {
				PrintWriter out = response.getWriter();
				out.print("<script>" + "alert('添加失败');"+ "document.location.href='data-people.jsp';"+ "</script>");
			//	out.print("添加失败");
				e.printStackTrace();
			}
		}
		/**
		 * 查詢
		 */
		else if (action.equals("list")) {
			try {
				List<People> peopleList = service.fill();
				request.setAttribute("PeopleList", peopleList);
				request.getRequestDispatcher("data-people.jsp").forward(request,
						response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("list2")) {
			String id1 = request.getParameter("id");
			try {
				People people = service.fill(id1);
				request.setAttribute("people", people);
				request.getRequestDispatcher("data-people.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * 修改
		 */
		else if (id != null && action.equals("update")) {
			try {
				People people = service.fill(id);
				request.setAttribute("people", people);
				request.getRequestDispatcher("peopleUpdate.jsp").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("update2")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String people_code = request.getParameter("peoplecode");
			People p = new People();
			p.setUsername(username);
			p.setPassword(password);
			p.setName(name);
			p.setCode(people_code);
			System.out.println("人员编码：" + people_code);
			System.out.println("名称：" + name);

			PeopleService peopleService = new PeopleService();
			try {
				peopleService.update(p);
				PrintWriter out = response.getWriter();
				out.print("修改成功");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 删除
		 */
		if (action.equals("delete")) {
			try {
				List<People> peopleDelete = service.fill();
				request.setAttribute("PeopleDelete", peopleDelete);
				request.getRequestDispatcher("PeopleDelete.jsp").forward(
						request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("delete2")) {
			String[] ids = request.getParameterValues("Id");
			// String id1=request.getParameter("id");
			try {
				// service.delete(id1);
				service.delete(ids);
				response.sendRedirect("ManagePeopleServlet?action=delete");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("delete3")) {
			String id1 = request.getParameter("id");
			try {
				service.delete(id1);
				response.sendRedirect("ManagePeopleServlet?action=list");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	 * Returns information about the servlet, such as author, version, and
	 * copyright.
	 * 
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

}
