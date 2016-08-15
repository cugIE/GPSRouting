package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Record;
import com.util.OutputHelper;

public class DeleteRecordServlet extends HttpServlet {
	
	public DeleteRecordServlet() {
		// TODO Auto-generated constructor stub
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

		String index = request.getParameter("index");
		if(index == null){
			OutputHelper.StringOutPut("error_index", response);
		}
		if (index.equals("period")) {
			int result = 0;
			String periodID = request.getParameter("period_id");
			String date = request.getParameter("date");
			System.out.println("periodId:"+periodID+"date:"+date);
			String start = date + " 00:00";
			String end = date + " 23:59";
			try {
				List<Record> recordList = Record.getAllRecordFromPeriod(periodID, start, end);
				for (int i = 0; i < recordList.size(); i++) {
					Record rcd = recordList.get(i);
					result = Record.deleteOneRecord(rcd.getId());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				OutputHelper.StringOutPut("error", response);
				e.printStackTrace();
			}
			OutputHelper.StringOutPut(""+result, response);
		} else {
			OutputHelper.StringOutPut("error_index", response);
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
