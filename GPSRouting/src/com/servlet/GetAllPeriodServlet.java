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

import com.bean.Record;
import com.bean.Region;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.Period;
import com.bean.PtrConnection;
import com.util.OutputHelper;

public class GetAllPeriodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAllPeriodServlet() {
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
		String web = request.getParameter("web");
		if (index.equals("sheet")){
			String sheet_id = request.getParameter("sheet_id");
			if (sheet_id == null){
				this.StringOutPut("error_sheet", response);
			}
			
			else{
				JSONArray JA = new JSONArray();
				
				try {
					List<Period> prds = Period.getAllPeriod(sheet_id);
					if (prds.size() == 0){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					else{
						for (int i = 0; i < prds.size(); i++){
							JSONObject jso = new JSONObject();
							
							jso.put("id", prds.get(i).getId());
							if(web!=null){
								jso.put("time", prds.get(i).getShift()+"-"+prds.get(i).getTime());
							}
							else {
								jso.put("shift", prds.get(i).getShift());
								jso.put("time", prds.get(i).getTime());
							}
							jso.put("gener", prds.get(i).getGener());
							jso.put("gener_id", prds.get(i).getGener_id());
							
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
		else if(index.equals("region")){
			String region_id = request.getParameter("region_id");
			if (region_id == null){
				this.StringOutPut("error_region", response);
			}
			
			else{
				JSONArray JA = new JSONArray();
				
				try {
					List<Period> prds = PtrConnection.getAllPeriod(region_id);
					if (prds.size() == 0){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					else{
						for (int i = 0; i < prds.size(); i++){
							JSONObject jso = new JSONObject();
							jso.put("shift", prds.get(i).getShift());
							jso.put("time", prds.get(i).getTime());
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
		else if(index.equals("result")) {
			String sheet_id = request.getParameter("sheet_id");
			String date = request.getParameter("date");
			System.out.println("选择的时间为："+date);
			if (sheet_id == null||date== null){
				this.StringOutPut("error_sheet", response);
			}else {
				JSONArray JA = new JSONArray();
				try {
					String start = date + " 00:00";
					String end = date + " 23:59";
					List<Period> prds = Period.getAllPeriod(sheet_id);
					for (int i = 0; i < prds.size(); i++) {
						JSONObject jsonObject = new JSONObject();

						Period prd = prds.get(i);

						List<Record> recordList = Record.getAllRecordFromPeriod(prd.getId(), start, end);
						List<PtrConnection> ptrConnectionList = PtrConnection.getAllRegion(prd.getId());
						if (recordList.size() == 0 || ptrConnectionList.size() == 0) {

							continue;
						} else {
							jsonObject.put("id",prd.getId());
							jsonObject.put("shift",prd.getShift());
							jsonObject.put("time",prd.getTime());
							jsonObject.put("date",date);
							String done = "";
							String all = "";
							for(int j=0; j< recordList.size(); j++){
								Record rcd = recordList.get(j);
								done = done+rcd.getRegion()+",";
							}
							jsonObject.put("done",done);
							for(int j=0; j< ptrConnectionList.size(); j++){
								Region rg = ptrConnectionList.get(j).getRegion_content();
								all = all+rg.getName()+",";
							}
							jsonObject.put("all",all);
							JA.add(jsonObject);

						}
					}
					JSONObject jo = new JSONObject();
					jo.put("total", 20);
					jo.put("rows", JA);
					OutputHelper.StringOutPut(jo.toString(), response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		else {
			this.StringOutPut("error_index".toString(), response);
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
		String index = request.getParameter("index");
		String sheet_id = request.getParameter("sheet_id");
		String shift = request.getParameter("shift");
		if(index==null) {
			if (sheet_id == null) {
				this.StringOutPut("error_sheet", response);
			} else {
				JSONArray JA = new JSONArray();

				try {
					List<Period> prds = Period.getAllPeriod(sheet_id);

						for (int i = 0; i < prds.size(); i++) {
							JSONObject jso = new JSONObject();

							jso.put("id", prds.get(i).getId());
							jso.put("shift", prds.get(i).getShift());
							jso.put("time", prds.get(i).getTime());
							jso.put("gener", prds.get(i).getGener());
							jso.put("gener_id", prds.get(i).getGener_id());

							JA.add(jso);
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("total", 20);
				jsonObject.put("rows", JA);
				OutputHelper.StringOutPut(jsonObject.toString(), response);
			}
		}else{
			if(index.equals("shift")){
				if (sheet_id == null) {
					this.StringOutPut("error_sheet", response);
				}
				else{
					try {
						OutputHelper.StringOutPut(Period.getShift(sheet_id), response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			else if(index.equals("rest")){
				if (sheet_id == null||shift == null) {
					this.StringOutPut("error_sheet", response);
				}
				else{
					try {
						OutputHelper.StringOutPut(Period.getRestPeriod(sheet_id,shift), response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}else if(index.equals("fromshift")){
				if (sheet_id == null||shift == null) {
					this.StringOutPut("error_sheet", response);
				}
				else{
					JSONArray JA = new JSONArray();

					try {
						List<Period> prds = Period.getAllPeriod(sheet_id,shift);

						for (int i = 0; i < prds.size(); i++) {
							JSONObject jso = new JSONObject();

							jso.put("id", prds.get(i).getId());
							jso.put("shift", prds.get(i).getShift());
							jso.put("time", prds.get(i).getTime());
							jso.put("gener", prds.get(i).getGener());
							jso.put("gener_id", prds.get(i).getGener_id());

							JA.add(jso);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("total", 20);
					jsonObject.put("rows", JA);
					OutputHelper.StringOutPut(jsonObject.toString(), response);

				}
			}
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
