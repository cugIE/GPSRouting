package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Question;
import com.bean.Sheet;
import com.util.OutputHelper;

public class AddSheetServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddSheetServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String intro = request.getParameter("intro");
		String branch_id = request.getParameter("branch_id");
		String gener_id = (String) request.getSession().getAttribute("SesId");
		Sheet sht = new Sheet();
		sht.setName(name);
		sht.setIntro(intro);
		if(gener_id==null || branch_id==null){
			OutputHelper.StringOutPut("error", response);
			return;
		}
		else{
			sht.setGener_id(Integer.parseInt(gener_id));
			sht.setBranch_id(Integer.parseInt(branch_id));
		}
		try {
			int result = Sheet.addOneSheet(sht);
			if(result==-1){
				OutputHelper.StringOutPut("error", response);
				return;
			}
			OutputHelper.StringOutPut(String.format("%04d", result), response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			OutputHelper.StringOutPut("error", response);
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
