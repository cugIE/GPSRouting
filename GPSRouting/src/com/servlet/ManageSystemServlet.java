package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LogInfo;
import com.bean.Site;
import com.service.SystemService;

public class ManageSystemServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void start(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		SystemService service = new SystemService();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		System.out.println("方法：" + action);
		System.out.println("方法id：" + id);
		
		if (id != null && action.equals("updatesite")) {
			Site site = service.fillsite(id);
			request.setAttribute("site", site);
			request.getRequestDispatcher("data-site.jsp").forward(
					request, response);
		} else if (action.equals("updatesite2")) {
			String siteid = "0001";
			String sitetitle = request.getParameter("site_title");
			String sitead = request.getParameter("site_ad");
			String siteword = request.getParameter("site_word");
			String sitecom = request.getParameter("site_com");
			Site site = new Site();
			site.setId(siteid);
			site.setWebtitle(sitetitle);
			site.setWebad(sitead);
			site.setWebword(siteword);
			site.setComname(sitecom);
			SystemService service2 = new SystemService();
			try {
				service2.updatewebsite(site);
				PrintWriter out = response.getWriter();
				out.print("修改成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}{

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

		request.setCharacterEncoding("utf-8");
		try {
			start(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		request.setCharacterEncoding("utf-8");
		try {
			start(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
