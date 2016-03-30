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

import com.bean.Sheet;
import com.util.OutputHelper;

public class GetAllSheetServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAllSheetServlet() {
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
		
		if(index.equals("branch")){
			String branch_id = request.getParameter("branch_id");
			if (branch_id==null){
				OutputHelper.StringOutPut("error_branch", response);
				
			}
			else {
				JSONArray JA = new JSONArray();
				try {
					List<Sheet> shts = Sheet.getAllSheet(branch_id);
					if (shts == null){
						OutputHelper.StringOutPut(JA.toString(),response);
					}
					else{
						for(int i = 0; i < shts.size(); i++){
							JSONObject jso = new JSONObject();
							jso.put("id", shts.get(i).getId());
							jso.put("name", shts.get(i).getName());
							jso.put("intro", shts.get(i).getIntro());
							jso.put("gener", shts.get(i).getGener());
							JA.add(jso);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				OutputHelper.StringOutPut(JA.toString(), response);
			}
			
		}
		else if(index.equals("sheet")){
			String sheet_id = request.getParameter("sheet_id");
			if (sheet_id==null){
				OutputHelper.StringOutPut("error_sheet", response);
				
			}
			else {
				Sheet sht;
				JSONObject jso = new JSONObject();
				try {
					sht = Sheet.getOneSheet(sheet_id);
					if (sht == null){
						OutputHelper.StringOutPut(jso.toString(),response);
					}
					else{
						jso.put("id", sht.getId());
						jso.put("name", sht.getName());
						jso.put("intro", sht.getIntro());
						jso.put("gener", sht.getGener());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
				OutputHelper.StringOutPut(jso.toString(),response);
			}
			
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
