package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.AlarmMsg;
import com.bean.Faultmsg;
import com.bean.People;
import com.service.MsgService;
import com.service.PeopleService;
import com.util.OutputHelper;
import com.util.TeamidtoName;

public class ManageAlarmServlet extends HttpServlet {

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
	
	public void start(HttpServletRequest request,HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		MsgService service = new MsgService();
		String action =  request.getParameter("action");
		String id = request.getParameter("id");
		PeopleService ps = new PeopleService();
		
		String handleState = "";
		
		
		if (action.equals("alarmList")) {
			String userBranchid = request.getParameter("userBranchid");//当前站场编号
			List<People> peopleList = ps.findPeo(userBranchid);
			List<String> peopleidList = new ArrayList<String>();//当前站场所有人员id
			String peopleid = "";
			TeamidtoName id2Name = new TeamidtoName();
			for (int i = 0; i < peopleList.size(); i++) {	
				People p = peopleList.get(i);
				peopleid = p.getId();
				peopleidList.add(peopleid);
			}
			JSONArray JA = new JSONArray();
			try {
				if (peopleidList.size()!=0) {
					for (int i = 0; i < peopleidList.size(); i++) {
						String gennerid = peopleidList.get(i);
						List<AlarmMsg> alarmList = service.fillpeoAlarm(gennerid);
						for (int j = 0; j < alarmList.size(); j++) {
							AlarmMsg a = alarmList.get(j);
							if (a.getState().equals("0")) {
								handleState = "未查看";
							}else {
								handleState = "已查看";
							}
							JSONObject js = new JSONObject();
							js.put("id", a.getId());
							js.put("title", "重要警报");
							js.put("time", a.getAlarmTime());
							js.put("address", a.getAlarmAddress());							
							js.put("state", a.getState());
							js.put("stateH", handleState);
							js.put("generid", a.getGener_id());
							js.put("generName", id2Name.peoid2name(a.getGener_id()));
							JA.add(js);
						}
					}
				} else {
					OutputHelper.StringOutPut("no result",response);
					return;
				}
				this.StringOutPut(JA.toString(), response);
			} catch (Exception e) {
				// TODO: handle exception
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
