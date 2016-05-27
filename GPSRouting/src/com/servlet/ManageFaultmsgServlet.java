package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Faultmsg;
import com.bean.People;
import com.service.MsgService;

public class ManageFaultmsgServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void start(HttpServletRequest request,HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		MsgService service = new MsgService();
		String action =  request.getParameter("action");
		String id = request.getParameter("id");
		
		if (action.equals("list")) {
			try {
				List<Faultmsg> faultlist = service.fill();
				request.setAttribute("faultlist", faultlist);
				request.getRequestDispatcher("fault-msg.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(id != null && action.equals("list2")){
			try {
				Faultmsg faultmsg = service.fill(id);
				request.setAttribute("faultmsg", faultmsg);
				request.getRequestDispatcher("fault-msg-detail.jsp").forward(request, response);
				MsgService ms = new MsgService();
				int count=ms.faultcount();
				System.out.println("统计信息:"+count);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				
		}else if (action.equals("updatestatus")) {
			String fault_id = request.getParameter("faultid");
			MsgService msgService = new MsgService();
			try {
				msgService.updatestatus(fault_id);
				PrintWriter out = response.getWriter();
				out.print("<script>" + "alert('更新成功');"+ "document.location.href='fault-msg.jsp';"+ "</script>");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (action.equals("newfault")) {
			String fault_title = request.getParameter("title");
			String fault_word = request.getParameter("word");
			String fault_time = request.getParameter("time");
			String picture_url =  request.getParameter("pic_url");
			String gener_id = request.getParameter("worker_id");
			Faultmsg faultmsg = new Faultmsg();
			faultmsg.setFaultTitle(fault_title);
			faultmsg.setFaultWord(fault_word);
			faultmsg.setFaultTime(fault_time);
			faultmsg.setFaultUrL(picture_url);
			faultmsg.setGenerId(gener_id);
			PrintWriter out = response.getWriter();
			try {
				service.add(faultmsg);
				out.print("1");
			} catch (Exception e) {
				// TODO: handle exception
				out.print("0");
			}
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
			start(request, response);
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
			start(request, response);
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
