package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.bean.Faultmsg;
import com.bean.People;
import com.mysql.fabric.xmlrpc.base.Array;
import com.service.MsgService;
import com.service.PeopleService;
import com.util.OutputHelper;
import com.util.TeamidtoName;

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
		PeopleService ps = new PeopleService();
		
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
		}else if (action.equals("list3")) {
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
						List<Faultmsg> faultList = service.fillgenid(gennerid);
						for (int j = 0; j < faultList.size(); j++) {
							Faultmsg f = faultList.get(j);
							JSONObject js = new JSONObject();
							js.put("id", f.getId());
							js.put("title", f.getFaultTitle());
							js.put("word", f.getFaultWord());
							js.put("photoUrl", f.getFaultUrL());
							js.put("time", f.getFaultTime());
							js.put("state", f.getFaultState());
							js.put("dutyMan", f.getDutyPeople());
							js.put("generid", f.getGenerId());
							js.put("generName", id2Name.peoid2name(f.getGenerId()));
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
				
		}else if (action.equals("updatestatus")) {
			String fault_id = request.getParameter("faultid");
			String fault_title = request.getParameter("faulttitle");
			String fault_word = request.getParameter("faultword");
			String people_num = request.getParameter("pnum");//手机号
			System.out.println("手机号："+people_num);
			System.out.println("故障信息："+"id:"+fault_id+"标题："+fault_title+"content:"+fault_word+"num:"+people_num);
			
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn"); 
			post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
			NameValuePair[] data ={ new NameValuePair("Uid", "gpsrouting"),new NameValuePair("Key", "42d1fea592e834991fab"),new NameValuePair("smsMob",people_num),new NameValuePair("smsText",fault_word)};
			MsgService msgService = new MsgService();
			try {  
				if (msgService.updatestatus(fault_id)==1) {
					HttpSession session = request.getSession();
					String dutyMan = (String) session.getAttribute("SesName");
					System.out.println("故障处理人："+dutyMan);
					int statusresult = msgService.handlefault(fault_id, dutyMan);
					/*PrintWriter out = response.getWriter();
					out.print("<script>" + "alert('更新成功');"+ "document.location.href='fault-msg.jsp';"+ "</script>");*/
					
					OutputHelper.StringOutPut(statusresult+"", response);
					//-------------------------发送短信-------------------------------------
					post.setRequestBody(data);

					client.executeMethod(post);
					Header[] headers = post.getResponseHeaders();
					int statusCode = post.getStatusCode();
					System.out.println("statusCode:"+statusCode);
					for(Header h : headers)
					{
					System.out.println(h.toString());
					}
					String result = new String(post.getResponseBodyAsString().getBytes("utf-8")); 
					System.out.println(result); //打印返回消息状态


					post.releaseConnection();

				}			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				OutputHelper.StringOutPut("error", response);
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
