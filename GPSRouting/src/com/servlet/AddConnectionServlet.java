package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PtrConnection;
import com.bean.Question;
import com.util.OutputHelper;

public class AddConnectionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddConnectionServlet() {
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
		String period_id = request.getParameter("period_id");
		String gener_id = request.getParameter("gener_id");
		String sort = request.getParameter("sort");
		String region_id = request.getParameter("region_id");
		System.out.println(period_id+":"+gener_id+":"+sort+":"+region_id+":");
		PtrConnection connect = new PtrConnection();
		if(gener_id==null || region_id==null || period_id==null || sort==null){
			OutputHelper.StringOutPut("error", response);
			return;
		}
		else{
			connect.setGener_id(Integer.parseInt(gener_id));
			connect.setRegion_id(region_id);
			connect.setPeriod_id(period_id);
			connect.setSort(Integer.parseInt(sort));
		}
		try {
			int result = PtrConnection.addOneConnection(connect);
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
