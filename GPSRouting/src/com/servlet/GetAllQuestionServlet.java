package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PtrConnection;
import com.bean.Question;
import com.bean.Region;
import com.util.OutputHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAllQuestionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAllQuestionServlet() {
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
		if (index.equals("question")){
			String question_id = request.getParameter("question_id");
			if (question_id == null){
				this.StringOutPut("error_question", response);
			}
			else{
				JSONObject jso = new JSONObject();
				
				try {
					Question qst = Question.getOneQuestion(question_id);
					if (qst == null){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					jso.put("id", question_id);
					jso.put("title", qst.getTitle());
					jso.put("possasws", qst.getPossibleAsw());
					jso.put("normalasws", qst.getNormalAsw());
					jso.put("gener", qst.getGener());
					jso.put("gener_id", qst.getGener_id());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(jso.toString(), response);
				
			}
		}
		else if(index.equals("region")){
			String region_id = request.getParameter("region_id");
			if (region_id == null){
				this.StringOutPut("error_region", response);
			}
			
			else{
				JSONArray JA = new JSONArray();
				
				try {
					List<Question> qsts = Question.getAllQuestion(region_id);
					if (qsts.size() == 0){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					for (int i = 0; i < qsts.size(); i++){
						JSONObject jso = new JSONObject();
						
						jso.put("id", qsts.get(i).getId());
						jso.put("title", qsts.get(i).getTitle());
						jso.put("possasws", qsts.get(i).getPossibleAsw());
						jso.put("normalasws", qsts.get(i).getNormalAsw());
						jso.put("gener", qsts.get(i).getGener());
						jso.put("gener_id", qsts.get(i).getGener_id());
						jso.put("region_id", qsts.get(i).getRegion_id());
						
						JA.add(jso);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}

		}
		else if(index.equals("period")){
			String period_id = request.getParameter("period_id");
			if (period_id == null){
				this.StringOutPut("error_period", response);
			}
			else {
				JSONArray JA = new JSONArray();
			
				try {
					List<PtrConnection> ptrs = PtrConnection.getAllRegion(period_id);
					if (ptrs.size()==0){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					for (int i = 0; i < ptrs.size(); i++){
						List<Question> qsts = Question.getAllQuestion(ptrs.get(i).getRegion_content().getId());
						for(int j = 0; j <qsts.size(); j++){
							JSONObject jso = new JSONObject();
							jso.put("region", ptrs.get(i).getRegion_content().getName());
							jso.put("id", qsts.get(j).getId());
							jso.put("title", qsts.get(j).getTitle());
							jso.put("possasws", qsts.get(j).getPossibleAsw());
							jso.put("normalasws", qsts.get(j).getNormalAsw());
							jso.put("gener", qsts.get(j).getGener());
							jso.put("gener_id", qsts.get(j).getGener_id());
							jso.put("region_id", qsts.get(j).getRegion_id());
							
							JA.add(jso);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}
		}
		else{
			this.StringOutPut("error_index", response);
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
	public void StringOutPut (String str, HttpServletResponse response){
		System.out.println(str);
		response.setContentType("charset=utf-8");
		try {
			response.getOutputStream().write(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
