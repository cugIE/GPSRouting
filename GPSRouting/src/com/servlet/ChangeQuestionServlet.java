package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Question;
import com.bean.Record;
import com.util.OutputHelper;

public class ChangeQuestionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeQuestionServlet() {
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

		request.setCharacterEncoding("utf-8");
		String question_id = request.getParameter("question_id");
		System.out.println(question_id);
		if (question_id!=null){
			try {
				String title = request.getParameter("title");
				String possasws = request.getParameter("possasws");
				String normalasws = request.getParameter("normalasws");
				System.out.println(question_id+";"+title+";"+possasws+";"+normalasws);
				Question qst = Question.getOneQuestion(question_id);
				if (title!=null){
					qst.setTitle(title);
				}
				if (possasws!=null){
					qst.setPossibleAsw(possasws);
				}
				if (normalasws!=null){
					qst.setNormalAsw(normalasws);
				}
				int result = Question.changeOneQuestion(qst);
				OutputHelper.StringOutPut(""+result, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				OutputHelper.StringOutPut("error", response);
				e.printStackTrace();
			}
		}
		else {
			OutputHelper.StringOutPut("error", response);
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
