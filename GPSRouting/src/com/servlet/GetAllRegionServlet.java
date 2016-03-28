package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.Region;

public class GetAllRegionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAllRegionServlet() {
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
		JSONArray JA = new JSONArray();
		try {
			List<Region> regions = Region.getAllRegion();
			for (int i = 0; i < regions.size(); i++ ){
				JSONObject js = new JSONObject();
				Region rg = regions.get(i);
				js.put("id", rg.getId());
				js.put("branch", rg.getBranch());
				js.put("intro", rg.getIntro());
				js.put("gps", rg.getGps());
				js.put("qrcode", rg.getType());
				js.put("type", rg.getType());
				js.put("gener", rg.getGener());
				JA.add(js);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(JA.toString());
		response.setContentType("text/html;charset=utf-8");
		response.getOutputStream().write(JA.toString().getBytes("utf-8"));

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
