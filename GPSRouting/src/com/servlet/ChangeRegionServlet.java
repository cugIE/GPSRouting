package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Region;
import com.util.OutputHelper;

public class ChangeRegionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeRegionServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String intro = request.getParameter("intro");
		String gps = request.getParameter("gps");
		String type = request.getParameter("type");
		String sheet_id = request.getParameter("sheet_id");
		String qrcode = request.getParameter("qrcode");
		String range = request.getParameter("range");
		
		System.out.println(id+";"+name+";"+intro+";"+gps+";"+type+range);
		System.out.println("表id："+sheet_id);
		try {
			Region rg = Region.getOneRegion(id);
			if(gps!=null){
				rg.setGps(gps);
			}
			if(name!=null){
				rg.setName(name);
			}
			if(qrcode!=null){
				rg.setQrcode(qrcode);
			}
			if(type!=null){
				if(type.equals("巡站点")){
					type="site";
				}
				else if(type.equals("巡线点")){
					type="route";
				}

				rg.setType(type);
			}
			if (sheet_id!=null) {
				rg.setSheet_id(Integer.parseInt(sheet_id));
			}
			if(range!=null){
				rg.setRange(Integer.parseInt(range));
			}
			if(intro!=null){
				rg.setIntro(intro);
			}
			int result = Region.changeOneRegion(rg);
			OutputHelper.StringOutPut(""+result, response);
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
