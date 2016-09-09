package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.interfaces.DSAKey;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Period;
import com.bean.PtrConnection;
import com.bean.Record;
import com.bean.Region;
import com.util.OutputHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MonthDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	private void start(HttpServletRequest request,HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String sheet_id = request.getParameter("sheet_id");
//		String nowDate =  request.getParameter("nowDate");
		String start_date = request.getParameter("start");
		String end_date = request.getParameter("end");
		Long start_Time = Long.parseLong(start_date);
		Long end_Time = Long.parseLong(end_date);
		Date date = new Date(start_Time);
		Date date2 = new Date(end_Time);
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd"); 
		
		
		System.out.println("表id："+sheet_id);
		System.out.println("开始日期："+start_date);
		System.out.println("结束日期："+end_date);
		/*Date date = new Date(nowDate);
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd");
        nowDate=dateformat1.format(date);*/
//		System.out.println("表id："+sheet_id+"nowdate:"+nowDate);
		
		int timesPlan = 0;//计划巡检次数
		int timesActual = 0;//实际巡检次数
		int dotsMiss = 0;//当天漏检个数
		int dotsUnusual = 0;//发现异常个数
		
		
		
		
		/*List<Period> prds = Period.getAllPeriod(sheet_id);
		timesActual = prds.size();*/
		/*String action = request.getParameter("action");
		JSONObject js = new JSONObject();
		js.put("id", 1);
		js.put("title", "标题");
		js.put("allDay", true);
		js.put("start", "2016-07-04");
		this.StringOutPut(js.toString(), response);*/
		
		Calendar start = Calendar.getInstance();
		start.set(2016, 6, 8);
		Long startTIme = start.getTimeInMillis();

		Calendar end = Calendar.getInstance();
//		end.set(2016, 7, 11);
		Long endTime = end.getTimeInMillis();
		System.out.println("当前系统时间："+endTime);

		Long oneDay = 1000 * 60 * 60 * 24l;
		  
		Long time = start_Time; 
		Long Etime = end_Time;
		JSONArray JA = new JSONArray();
		    while (time <= Etime) {  
		    	JSONObject js = new JSONObject();	
		    	JSONObject js2 = new JSONObject();//巡检记录用一个JSON存储
		        Date d = new Date(time);  
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		      //  timesActual = recordTimes(df.format(d), sheet_id);
		        js2 = recordTimes(df.format(d), sheet_id);
		        timesPlan = js2.getInt("prds");
		        timesActual = js2.getInt("records");
//		        dotsMiss = js2.getInt("missRegions");
		        if(timesActual == 0){
		        	dotsUnusual = 0;
		        	dotsMiss = 0;
		        }else {
		        	dotsMiss = MissRegions(df.format(d), sheet_id);
		        	dotsUnusual = unusualDots(df.format(d),sheet_id);
				}
		        System.out.println(df.format(d));  
		        js.put("title","计划巡检："+timesPlan+"\n"+"实际巡检："+timesActual+"\n"+"当天漏检："+dotsMiss+"\n"+"发现异常："+dotsUnusual);
		        js.put("start", df.format(d));
		        JA.add(js);
		        time += oneDay;  
		    }  
		
	//	String strvalue = "[{\"id\":111,\"title\":\"Event1\",\"start\":\"2016-07-10\",\"url\":\"http:\\/\\/yahoo.com\\/\"},{\"id\":222,\"title\":\"Event2\",\"start\":\"2016-07-20\",\"end\":\"2016-07-22\",\"url\":\"http:\\/\\/yahoo.com\\/\"}]";  
	      
	     //  System.out.println("strvalue="+strvalue);  
	       
	         //  response.getWriter().print(strvalue);  
	    	   this.StringOutPut(JA.toString(), response);
	       
	     
		
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public int MissRegions(String date,String sheetId)throws Exception {
		int missRgns = 0;
		int totalRgns = 0;
		if (sheetId == null||date == null) {
			return 0;
		} else {
			try {
				String start = date + " 00:00";
				String end = date + " 23:59";
				List<Period> prds = Period.getAllPeriod(sheetId);
				
//				System.out.println("表id为"+sheetId+"的在时间为"+date+"时间点有"+prds.size()+"个");
				for (int i = 0; i < prds.size(); i++) {
					Period prd = prds.get(i);
					List<Record> recordList = Record.getAllRecordFromPeriod(prd.getId(), start, end);//当前循环时间点已巡检区域列表
//					System.out.println("表id为"+sheetId+"的巡检记录在"+date+"共有"+recordList.size()+"条");
					List<PtrConnection> ptrConnectionList = PtrConnection.getAllRegion(prd.getId());//当前循环时间点所有区域
				
					totalRgns = ptrConnectionList.size();
					missRgns = missRgns + (totalRgns-recordList.size());	
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return missRgns;
	}
	
	public JSONObject recordTimes(String date,String sheetId)throws Exception {
		JSONObject resultJSON = new JSONObject();
		int periods = 0;
		int rcds = 0;

		if (sheetId == null||date== null){
			return null;
		}else {
			try {
				String start = date + " 00:00";
				String end = date + " 23:59";
				List<Period> prds = Period.getAllPeriod(sheetId);
				periods = prds.size();

				for (int i = 0; i < prds.size(); i++) {
					Period prd = prds.get(i);
					List<Record> recordList = Record.getAllRecordFromPeriod(prd.getId(), start, end);//当前循环时间点已巡检区域列表	
					if (recordList.size() != 0) {
						rcds++;
					}
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			resultJSON.put("prds", periods);
			resultJSON.put("records", rcds);

			return resultJSON;
		}
	}
	
	/*public JSONObject recordTimes(String date,String sheetId)throws Exception {
		JSONObject resultJSON = new JSONObject();
		int periods = 0;
		int rcds = 0;
		int missRgns = 0;
		int totalRgns = 0;
		if (sheetId == null||date== null){
			return null;
		}else {
			JSONArray JA = new JSONArray();
			try {
				String start = date + " 00:00";
				String end = date + " 23:59";
				List<Period> prds = Period.getAllPeriod(sheetId);
				periods = prds.size();
//				System.out.println("表id为"+sheetId+"的在时间为"+date+"时间点有"+prds.size()+"个");
				for (int i = 0; i < prds.size(); i++) {
					Period prd = prds.get(i);
					List<Record> recordList = Record.getAllRecordFromPeriod(prd.getId(), start, end);//当前循环时间点已巡检区域列表
//					System.out.println("表id为"+sheetId+"的巡检记录在"+date+"共有"+recordList.size()+"条");
					List<PtrConnection> ptrConnectionList = PtrConnection.getAllRegion(prd.getId());//当前循环时间点所有区域
				
					totalRgns = ptrConnectionList.size();
					missRgns = missRgns + (totalRgns-recordList.size());	
					if (recordList.size() != 0) {
						rcds++;
					}
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			resultJSON.put("prds", periods);
			resultJSON.put("records", rcds);
			resultJSON.put("missRegions", missRgns);
			return resultJSON;
		}
	}*/

	private int unusualDots(String date,String sheetId)throws Exception {
		int resultDots = 0;
		
		if (date == null || sheetId == null) {
			return 0;
		} else {
			String end = date + " 23:59";
		    String start = date + " 00:00";		    
		    try {
		    	List<Period> prds = Period.getAllPeriod(sheetId);
		    	for (int i = 0; i < prds.size(); i++) {
		    		Period prd = prds.get(i);
					List<Record> recordList = Record.getAllRecordFromPeriod(prd.getId(), start, end);
					
						for (int j = 0; j < recordList.size(); j++) {
							Record rcd = recordList.get(i);
							JSONArray jalist = JSONArray.fromObject(rcd.getAsws());
							for (int k = 0; k < jalist.size(); k++) {
								
								JSONObject jsrcd = jalist.getJSONObject(k);
								if (jsrcd.getString("error").equals("1")) {
									resultDots++;
								}
							
							}
						}
									
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	    
		
		return resultDots;
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
