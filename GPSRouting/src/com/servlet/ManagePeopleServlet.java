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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.bean.Branch;
import com.bean.People;
import com.service.BranchService;
import com.service.PeopleService;
import com.util.OutputHelper;
import com.util.TeamidtoName;

public class ManagePeopleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagePeopleServlet() {
		super();
	}

	private void start(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PeopleService service = new PeopleService();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		System.out.println("方法：" + action);
		System.out.println("方法id：" + id);
		
		
		Logger log = Logger.getLogger(ManagePeopleServlet.class);
		/**
		 * 添加
		 */
		if (action.equals("add")) {
			response.setContentType("text/html;charset=utf-8");
			// String People_id = request.getParameter("People_id");
			String username = request.getParameter("username");
		//	String password = request.getParameter("password");
			String name = request.getParameter("name");
			String people_code = request.getParameter("peoplecode");
			String branch_id = request.getParameter("branchid");
			String team_id = request.getParameter("teamid");
			String people_remark = request.getParameter("peopRemark");
			System.out.println(username);
	//		System.out.println(password);
			System.out.println(name);
			System.out.println(people_code);
			System.out.println(branch_id);
			System.out.println(team_id);
			System.out.println("备注信息："+people_remark);

			People p = new People();
			p.setUsername(username);
	//		p.setPassword(password);
			p.setName(name);
			p.setCode(people_code);
			p.setBranchId(branch_id);
			p.setTeamId(team_id);
			p.setPeopRemark(people_remark);
			
			
			PeopleService peopleService = new PeopleService();
			try {
				peopleService.add(p);
				PrintWriter out = response.getWriter();
				out.print("<script>" + "alert('添加成功');"+ "document.location.href='data-people.jsp';"+ "</script>");
			//	out.print("添加成功");
				log.error("新增人员信息");
			} catch (SQLException e) {
				PrintWriter out = response.getWriter();
				out.print("<script>" + "alert('添加失败');"+ "document.location.href='data-people.jsp';"+ "</script>");
			//	out.print("添加失败");
				e.printStackTrace();
			}
		}
		/**
		 * 查詢
		 */
		else if (action.equals("list")) {
			String userBranch = request.getParameter("userbranch");
			System.out.println("当前登录用户部门id："+userBranch);
			BranchService bs = new BranchService();
			Branch uBranch = bs.fill(userBranch);
			String uBranchType = uBranch.getBranchType();
			System.out.println("当前登录用户部门类型："+uBranchType);
			
			TeamidtoName teamidtoName = new TeamidtoName();
			
			//==========================================================
			/*JSONArray JA = new JSONArray();
			try {
				List<People> peopleList = service.fill();
				System.out.println("记录条数："+peopleList.size());
				if (peopleList.size()!=0) {
					for (int i = 0; i < peopleList.size(); i++) {
						JSONObject js = new JSONObject();
						People p = peopleList.get(i);
						js.put("id", p.getId());
						js.put("username", p.getUsername());
						js.put("name", p.getName());
						js.put("teamid", p.getTeamId());
						js.put("branchid", p.getBranchId());
						js.put("peoplecode", p.getCode());
						js.put("peopRemark", p.getPeopRemark());		
						JA.add(js);
					}
				}else {
					OutputHelper.StringOutPut("no result",response);
					return;
				}
				this.StringOutPut(JA.toString(), response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//=============================================
			
		

			if (uBranchType.equals("管理")) {
				JSONArray JA = new JSONArray();
				try {
					List<People> peopleList = service.fill();
					System.out.println("记录条数："+peopleList.size());
					if (peopleList.size()!=0) {
						for (int i = 0; i < peopleList.size(); i++) {
							JSONObject js = new JSONObject();
							People p = peopleList.get(i);
							js.put("id", p.getId());
							js.put("username", p.getUsername());
							js.put("name", p.getName());
							js.put("teamid", p.getTeamId());
							js.put("teamname", teamidtoName.idtoname(p.getTeamId()));
							js.put("branchid", p.getBranchId());
							js.put("braname", teamidtoName.braid2name(p.getBranchId()));
							js.put("peoplecode", p.getCode());
							js.put("peopRemark", p.getPeopRemark());		
							JA.add(js);
						}
					}else {
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					this.StringOutPut(JA.toString(), response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (uBranchType.equals("站场")) {
				JSONArray JA2 = new JSONArray();
				try {
					PeopleService ps = new PeopleService();
					List<People> peopleList2 = ps.findPeo(userBranch);
					System.out.println("记录条数："+peopleList2.size());
					if (peopleList2.size()!=0) {
						for (int i = 0; i < peopleList2.size(); i++) {
							JSONObject js2 = new JSONObject();
							People p = peopleList2.get(i);
							js2.put("id", p.getId());
							js2.put("username", p.getUsername());
							js2.put("name", p.getName());
							js2.put("teamid", p.getTeamId());
							js2.put("teamname", teamidtoName.idtoname(p.getTeamId()));
							js2.put("branchid", p.getBranchId());
							js2.put("braname", teamidtoName.braid2name(p.getBranchId()));
							js2.put("peoplecode", p.getCode());
							js2.put("peopRemark", p.getPeopRemark());		
							JA2.add(js2);
						}
					}else {
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					this.StringOutPut(JA2.toString(), response);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} 
								
		} else if (action.equals("list2")) {
			String id1 = request.getParameter("id");
			try {
				People people = service.fill(id1);
				request.setAttribute("people", people);
				request.getRequestDispatcher("data-people.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("branchpeople")) {
			String branchid = request.getParameter("selbraid");
			System.out.println("员工所属部门id:"+branchid);
			try {
				
		//		List<People> listpeo = service.findPeo(branchid);
				request.setAttribute("branchid", branchid);
				request.getRequestDispatcher("data-brapeople.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		/**
		 * 修改
		 */
		else if (id != null && action.equals("update")) {
			try {
				People people = service.fill(id);
				request.setAttribute("people", people);
				request.getRequestDispatcher("peopleUpdate.jsp").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("update2")) {
			String user_id = request.getParameter("id");
			String username = request.getParameter("username");
		//	String password = request.getParameter("password");
			String name = request.getParameter("name");
			String people_code = request.getParameter("peoplecode");
			String branch_id = request.getParameter("branchid");
			String team_id = request.getParameter("teamid");
			String people_remark = request.getParameter("peopRemark");
			People p = new People();
			p.setId(user_id);
			p.setUsername(username);
		//	p.setPassword(password);
			p.setName(name);
			p.setCode(people_code);
			p.setBranchId(branch_id);
			p.setTeamId(team_id);
			p.setPeopRemark(people_remark);
			System.out.println("人员编码：" + people_code);
			System.out.println("名称：" + name);
			System.out.println("部门："+branch_id);
			System.out.println("职位："+team_id);
			System.out.println("备注："+people_remark);

			PeopleService peopleService = new PeopleService();
			try {
				int result = peopleService.update(p);
			//	PrintWriter out = response.getWriter();
				OutputHelper.StringOutPut(""+result, response);
	//			out.print("<script>" + "alert('修改成功！');"+ "document.location.href='data-people.jsp';"+ "</script>");
				log.error("修改人员信息");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 删除
		 */
		if (action.equals("delete")) {
			try {
				List<People> peopleDelete = service.fill();
				request.setAttribute("PeopleDelete", peopleDelete);
				request.getRequestDispatcher("PeopleDelete.jsp").forward(
						request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("delete2")) {
			String[] ids = request.getParameterValues("Id");
			// String id1=request.getParameter("id");
			try {
				// service.delete(id1);
				service.delete(ids);
				response.sendRedirect("ManagePeopleServlet?action=delete");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("delete3")) {
			String id1 = request.getParameter("id");
			try {
				int result = 1 ;
			//	PrintWriter out = response.getWriter();
				service.delete(id1);
			//	response.sendRedirect("deletesuccess");
			//	response.sendRedirect("ManagePeopleServlet?action=list");
				OutputHelper.StringOutPut(result+"", response);
				log.error("删除人员信息");
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	 * Returns information about the servlet, such as author, version, and
	 * copyright.
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
