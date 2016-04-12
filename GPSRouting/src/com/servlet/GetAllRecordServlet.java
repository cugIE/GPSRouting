package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.Record;
import com.util.OutputHelper;

public class GetAllRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAllRecordServlet() {
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

		String index = request.getParameter("index");
		if (index.equals("record")){
			String record_id = request.getParameter("record_id");
			if (record_id == null){
				OutputHelper.StringOutPut("error_record", response);
				return;
			}
			JSONObject jso = new JSONObject();
			try {
				Record rcd = Record.getOneRecord(record_id);
				if (rcd == null){
					OutputHelper.StringOutPut("no result", response);
					return;
				}
				jso.put("gps", rcd.getGps());
				jso.put("asws", rcd.getAsws());
				jso.put("error", rcd.getError());
				jso.put("picture", rcd.getPicture());
				jso.put("start", rcd.getStart().toString());
				jso.put("end", rcd.getEnd().toString());
				jso.put("submit", rcd.getSubmit().toString());
				jso.put("region", rcd.getRegion());
				jso.put("period_shift", rcd.getPeriod_shift());
				jso.put("period_time", rcd.getPeriod_time().toString());
				jso.put("gener", rcd.getGener());
				jso.put("status", rcd.getStatus());
				jso.put("checker", rcd.getChecker());
				jso.put("note", rcd.getNote());
				jso.put("type", rcd.getType());
				jso.put("branch", rcd.getBranch());
				if(rcd.getCheck() == null){
					jso.put("check_time", "");
				}
				else{
					jso.put("check_time", rcd.getCheck().toString());
				}
				OutputHelper.StringOutPut(jso.toString(), response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(index.equals("gener")){
			String gener_id = request.getParameter("gener_id");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			if (gener_id == null||start == null||end == null){
				OutputHelper.StringOutPut("error_record", response);
				return;
			}
			JSONArray JA = new JSONArray();
			List<Record> rcds;
			try {
				rcds = Record.getAllRecord(gener_id,start,end);
				if (rcds.size() == 0){
					OutputHelper.StringOutPut("no result", response);
					return;
				}
			
				for(int i = 0; i < rcds.size(); i++){
					Record rcd = rcds.get(i);
					JSONObject jso = new JSONObject();
					jso.put("id", rcd.getId());
					jso.put("error", rcd.getError());
					jso.put("start", rcd.getStart().toString());
					jso.put("end", rcd.getEnd().toString());
					jso.put("submit", rcd.getSubmit().toString());
					jso.put("region", rcd.getRegion());
					jso.put("period_shift", rcd.getPeriod_shift());
					jso.put("period_time", rcd.getPeriod_time().toString());
					jso.put("gener", rcd.getGener());
					jso.put("status", rcd.getStatus());
					jso.put("checker", rcd.getChecker());
					jso.put("type", rcd.getType());
					jso.put("branch", rcd.getType());
					if(rcd.getCheck()!=null)
					{
						jso.put("check_time", rcd.getCheck().toString());
					}
					else{
						jso.put("check_time", "");
					}
					JA.add(jso);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputHelper.StringOutPut(JA.toString(), response);
		}
		else if(index.equals("timestamp")){
			
		}
		else {
			OutputHelper.StringOutPut("error_index", response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
