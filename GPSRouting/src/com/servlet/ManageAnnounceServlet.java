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

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.Announcement;
import com.bean.People;
import com.service.MsgService;
import com.service.PeopleService;
import com.util.OutputHelper;
import com.util.TeamidtoName;

public class ManageAnnounceServlet extends HttpServlet {

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
		
		Logger log = Logger.getLogger(ManageAnnounceServlet.class);
		
		String handleState = "";
		
		
		if (action.equals("announceList")) {
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
						List<Announcement> announceList = service.fillgenidAnnounce(gennerid);
						for (int j = 0; j < announceList.size(); j++) {
							Announcement a = announceList.get(j);
							
							JSONObject js = new JSONObject();
							js.put("id", a.getId());
							js.put("title", a.getTitle());
							js.put("content", a.getContent());
							js.put("time", a.getTime());
							js.put("generid", a.getGenerId());
							js.put("generName", id2Name.peoid2name(a.getGenerId()));
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
		}else if (action.equals("add")) {
			String annTitle = request.getParameter("title");
			String annContent = request.getParameter("content");
			String annTime = request.getParameter("time");
			String annGenerid = request.getParameter("generId");
			
			System.out.println("故障标题："+annTitle);
			System.out.println("发布时间："+annTime);
			System.out.println("发布人id："+annGenerid);
			System.out.println("故障内容："+annContent);
			
			Announcement ann = new Announcement();
			ann.setTitle(annTitle);
			ann.setContent(annContent);
			ann.setTime(annTime);
			ann.setGenerId(annGenerid);

			MsgService msgService = new MsgService();
			try {
				
				int result = msgService.addAnn(ann);
				OutputHelper.StringOutPut(result+"", response);
				log.error("添加公告信息");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}		
			
		}else if (action.equals("update")) {
			String annId = request.getParameter("id");
			String annTitle = request.getParameter("title");
			String annContent = request.getParameter("content");
			String annTime = request.getParameter("time");
			String annGenerid = request.getParameter("generId");
			
			System.out.println("故障id："+annId);
			System.out.println("故障标题："+annTitle);
			System.out.println("发布时间："+annTime);
			System.out.println("发布人id："+annGenerid);
			System.out.println("故障内容："+annContent);
			
			Announcement ann = new Announcement();
			ann.setId(annId);
			ann.setTitle(annTitle);
			ann.setContent(annContent);
			ann.setTime(annTime);
			ann.setGenerId(annGenerid);

			MsgService msgService = new MsgService();
			try {
				
				int result = msgService.updateAnn(ann);
				OutputHelper.StringOutPut(result+"", response);
				log.error("更新公告信息");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				OutputHelper.StringOutPut("error", response);
			}		
		}else if (action.equals("delete")) {
			String annId = request.getParameter("id");
			try {
				
			
				int result = service.deleteAnn(annId);
	
				OutputHelper.StringOutPut(result+"", response);
				log.error("删除人员信息");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				OutputHelper.StringOutPut("error", response);
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
