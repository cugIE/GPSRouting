package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AlarmMsg;
import com.service.MsgService;

public class PushAlarmServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int SUCCESS = 1; //  
    private static final int FAIL = 0;
    
    protected void pAlarm(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException,IOException{
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String alarmAddress = request.getParameter("gps");
		String alarmTime = request.getParameter("time");
		String gener_id = request.getParameter("worker_id");
		
		AlarmMsg alarmMsg = new AlarmMsg();
		alarmMsg.setAlarmAddress(alarmAddress);
		alarmMsg.setAlarmTime(alarmTime);
		alarmMsg.setGener_id(gener_id);
		
		MsgService service = new MsgService();
		try {
			service.addAlarm(alarmMsg);
			out.print("添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			out.print("添加失败");
		}
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

		request.setCharacterEncoding("UTF-8");
		try {
			pAlarm(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

		request.setCharacterEncoding("UTF-8");
		try {
			pAlarm(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

}
