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

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.PtrConnection;
import com.bean.Region;
import com.util.OutputHelper;

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
		String index = request.getParameter("index");
		//System.out.print(index+"1");
		if (index.equals("branch")){
			String branchID = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else {
				try {
					List<Region> regions;
					regions = Region.getAllRegion(branchID);
					if (regions.size()!=0){
						
						for (int i = 0; i < regions.size(); i++ ){
							JSONObject js = new JSONObject();
							Region rg = regions.get(i);
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("branch", rg.getBranch());
							js.put("intro", rg.getIntro());
							js.put("gps", rg.getGps());
							js.put("qrcode", rg.getQrcode());
							js.put("type", rg.getType());
							js.put("gener", rg.getGener());
							JA.add(js);
						}
					}
					else{
						OutputHelper.StringOutPut("no result",response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}
		}
		else if (index.equals("all")){
			JSONArray JA = new JSONArray();
			try {
				List<Region> regions;
				regions = Region.getAllRegion();
				if (regions.size()!=0){
					
					for (int i = 0; i < regions.size(); i++ ){
						JSONObject js = new JSONObject();
						Region rg = regions.get(i);
						js.put("id", rg.getId());
						js.put("name", rg.getName());
						js.put("branch", rg.getBranch());
						js.put("intro", rg.getIntro());
						js.put("gps", rg.getGps());
						js.put("qrcode", rg.getQrcode());
						js.put("type", rg.getType());
						js.put("gener", rg.getGener());
						JA.add(js);
					}
				}
				else{
					OutputHelper.StringOutPut("no result",response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.StringOutPut(JA.toString(), response);
		}
		else if (index.equals("period")){
			String periodID = request.getParameter("period_id");
			JSONArray JA = new JSONArray();
			if (periodID == null) {
				this.StringOutPut("error_period", response);
			}
			else {
				try {
					List<PtrConnection> ptrs;
					ptrs = PtrConnection.getAllRegion(periodID);
					if (ptrs.size()!=0){
						
						for (int i = 0; i < ptrs.size(); i++ ){
							JSONObject js = new JSONObject();
							PtrConnection ptr = ptrs.get(i);
							js.put("id", ptr.getRegion_content().getId());
							js.put("name", ptr.getRegion_content().getName());
							js.put("branch", ptr.getRegion_content().getBranch());
							js.put("intro", ptr.getRegion_content().getIntro());
							js.put("type", ptr.getRegion_content().getType());
							js.put("sort", ptr.getSort());
							js.put("gener", ptr.getGener());
							js.put("gener_id", ptr.getGener_id());
							js.put("ptr_id",Integer.parseInt(ptr.getId()));
							JA.add(js);
						}
					}
					else{
						OutputHelper.StringOutPut("no result",response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}
		}
		else if(index.equals("region")){

			String regionID = request.getParameter("region_id");
			JSONObject js = new JSONObject();
			if (regionID == null) {
				this.StringOutPut("error_region", response);
			}
			else {

				try {
					Region rg = Region.getOneRegion(regionID);
					if (rg == null){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					else{
						js.put("id", rg.getId());
						js.put("name", rg.getName());
						js.put("branch", rg.getBranch());
						js.put("intro", rg.getIntro());
						js.put("gps", rg.getGps());
						js.put("qrcode", rg.getQrcode());
						js.put("type", rg.getType());
						js.put("gener", rg.getGener());
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(js.toString(), response);
			}
		}
		else if(index.equals("rest")){
			
		}
		else {
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
