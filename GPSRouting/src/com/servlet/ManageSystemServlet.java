package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.Branch;
import com.bean.LogInfo;
import com.bean.Site;
import com.service.SystemService;
import com.util.OutputHelper;

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
			String userTeam = request.getParameter("userteamid");
			System.out.println("用户类型："+ userTeam);
			if (!userTeam.equals("1")) {
				PrintWriter out = response.getWriter();
				out.print("修改失败，非管理人员不得修改此参数！");
			} else {
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
			}
		}else if (action.equals("loglist")) {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			// System.out.print("当前页码："+page+"当前行数："+rows);
			int logpage = Integer.parseInt(page);
			int logrows = Integer.parseInt(rows);
			System.out.print("当前页码：" + logpage + "当前行数：" + logrows);
			JSONArray JA = new JSONArray();
			JSONObject resultJson = new JSONObject();
			try {

				List<LogInfo> loginfoList = service.fill(logpage, logrows);
				
				if (loginfoList.size() != 0) {
					for (int i = 0; i < loginfoList.size(); i++) {
						JSONObject js = new JSONObject();
						LogInfo l = loginfoList.get(i);
						js.put("id", l.getId());
						js.put("msg", l.getMsg());
						js.put("time", l.getCreattime());
						js.put("user", l.getUsername());
						JA.add(js);
					}
				} else {
					OutputHelper.StringOutPut("no result", response);
					return;
				}
				Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
				jsonMap.put("total", service.fill().size());// total键
															// 存放总记录数，必须的
				jsonMap.put("rows", JA);// rows键 存放每页记录 list
				resultJson = JSONObject.fromObject(jsonMap);
				this.StringOutPut(resultJson.toString(), response);
				// this.StringOutPut(JA.toString(), response);
				/*
				 * request.setAttribute("BranchList", branchList);
				 * request.getRequestDispatcher("data-newbranch.jsp").forward(
				 * request, response);
				 */
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
