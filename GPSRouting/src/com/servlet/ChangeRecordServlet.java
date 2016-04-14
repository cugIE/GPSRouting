package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Record;
import com.util.OutputHelper;

public class ChangeRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeRecordServlet() {
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
		request.setCharacterEncoding("utf-8");
		String record_id = request.getParameter("record_id");
		if (record_id!=null){
			try {
				String asws = request.getParameter("asws");
				String error = request.getParameter("error");
				String picture = request.getParameter("picture");
				String note = request.getParameter("note");
				String submit_time = request.getParameter("submit_time");
				Record rcd = Record.getOneRecord(record_id);
				if (asws!=null){
					rcd.setAsws(asws);
				}
				if (error!=null){
					rcd.setError(error);
				}
				if (note!=null){
					rcd.setNote(note);
				}
				if (picture!=null){
					rcd.setPicture(picture);
				}
				if (submit_time!=null){
					rcd.setSubmit(Timestamp.valueOf(submit_time));
				}
				int result = Record.changeOneRecord(rcd);
				OutputHelper.StringOutPut(""+result, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			OutputHelper.StringOutPut("error_record", response);
			return;
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
