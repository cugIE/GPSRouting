package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Period;
import com.util.OutputHelper;

public class AddPeriodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddPeriodServlet() {
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
		String index = request.getParameter("index");
	//	System.out.println(index);
		if (index == null){
			OutputHelper.StringOutPut("error_index", response);
			
			return;
		}
		if (index.equals("several")){
			String shift = request.getParameter("shift");
			String periods = request.getParameter("periods");
			String gener_id = request.getParameter("gener_id");
			String sheet_id = request.getParameter("sheet_id");
			String[] periodStrs = periods.split(",");
			Period pr = new Period();
			if(Period.isShift(shift,sheet_id)){
				OutputHelper.StringOutPut("error_existed", response);
				return;
			}
			else{
				pr.setGener_id(Integer.parseInt(gener_id));
				pr.setShift(shift);
				pr.setSheet_id(Integer.parseInt(sheet_id));
				for (int i = 0; i<periodStrs.length;i++){
					
					pr.setTime(periodStrs[i]);
					
					try {
						Period.addOnePeriod(pr);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						OutputHelper.StringOutPut("error", response);
						e.printStackTrace();
					}
				}
				OutputHelper.StringOutPut("1", response);
			}
		}
		else if(index.equals("one")){
			String shift = request.getParameter("shift");
			String time = request.getParameter("time");
			String gener_id = request.getParameter("gener_id");
			String sheet_id = request.getParameter("sheet_id");
			Period pr = new Period();
		//	System.out.println(shift+","+time+","+gener_id+","+sheet_id);
			pr.setGener_id(Integer.parseInt(gener_id));
			pr.setShift(shift);
			pr.setSheet_id(Integer.parseInt(sheet_id));
				
			pr.setTime(time);
			int result = 0;	
			try {
				result = Period.addOnePeriod(pr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				OutputHelper.StringOutPut("error", response);
				e.printStackTrace();
			}
			OutputHelper.StringOutPut(""+result, response);
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
