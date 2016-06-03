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

import net.sf.json.JSONObject;

public class AddRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddRecordServlet() {
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
		String RecordStr = request.getParameter("record");
		JSONObject jso = JSONObject.fromObject(RecordStr);
		JSONObject temp  = new JSONObject();
		Record rcd = new Record();
		try {
			if(jso.get("picture")!=null){
				temp.put("picture",jso.get("picture"));
			}
			else{
				temp.put("picture","");
			}
			if(jso.get("vedio")!=null){
				temp.put("vedio",jso.get("vedio"));
			}
			else{
				temp.put("vedio","");
			}
//			String sql = "insert into record"
//					+ "(record_gps, record_asws, record_error, record_picture, "
//					+ "record_start, record_end, ptr_id, "
//					+ "gener_id, record_note)"
//					+ "values('"+ rcd.getGps() + "','"
//					+ rcd.getAsws() + "','" 
//					+ rcd.getError()  + "','" 
//					+ rcd.getPicture()  + "','" 
//					+ rcd.getStart().toString()  + "','" 
//					+ rcd.getEnd().toString()  + "','" 
//					+ rcd.getPtr_id()  + "','" 
//					+ rcd.getGener_id()  + "','" 
//					+ rcd.getNote()  + "')"; 
			rcd.setGps(jso.getString("gps"));
			rcd.setAsws(jso.getString("asws"));
			rcd.setError(jso.getString("error"));
			rcd.setPicture(temp.toString());
			rcd.setStart(Timestamp.valueOf(jso.getString("start")));
			rcd.setEnd(Timestamp.valueOf(jso.getString("end")));
			rcd.setPtr_id(jso.getInt("ptr_id"));
			rcd.setGener_id(jso.getInt("gener_id"));
			rcd.setNote(jso.getString("note"));
			int result = Record.addOneRecord(rcd);
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
