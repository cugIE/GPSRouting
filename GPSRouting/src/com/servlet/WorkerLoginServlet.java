package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bean.People;
import com.service.PeopleService;

public class WorkerLoginServlet extends HttpServlet {
	
	static final long serialVersionUID = 1L;
	private static final int LOGIN_SUCCESS = 1; //  
    private static final int LOGIN_FAIL = 0;
	 
    public WorkerLoginServlet() {
        super();
    }

	protected void login(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		People p = new People();
		p.setUsername(username); 
		p.setPassword(password);

		JSONObject pJsonObject = new JSONObject();
		
		try {
			PeopleService ps = new PeopleService();
			//登录成功，返回状态码和json数据
			if (ps.check(p)) {
				System.out.println("登录成功！");
				pJsonObject.put("people_id",p.getId());
				pJsonObject.put("username", p.getUsername());
				pJsonObject.put("password", p.getPassword());
				pJsonObject.put("name", p.getName());
				pJsonObject.put("branch_id", p.getBranchId());
				pJsonObject.put("team_id", p.getTeamId());
			//	out.print(LOGIN_SUCCESS);
				out.print(pJsonObject.toString());
			} else {
				System.out.println("登录失败！");
				out.print(LOGIN_FAIL);
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
		login(request, response);
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
		login(request, response);	
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
