package com.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bean.Branch;
import com.bean.People;
import com.service.BranchService;
import com.util.OutputHelper;

public class ManageBranchServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	static final long serialVersionUID = 1L;
	 
    public ManageBranchServlet() {
        super();
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

	private void start(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
 
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        BranchService service = new BranchService();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        System.out.println("方法："+action);
        System.out.println("方法id："+id);
        
        Logger log = Logger.getLogger(ManageBranchServlet.class);
        /**
         * 添加
         */
        if (action.equals("add")) {
            response.setContentType("text/html;charset=utf-8");
            //String branch_id = request.getParameter("branch_id");
            String branch_code = request.getParameter("branchCode");
            String branch_name = request.getParameter("branchName");
            String branch_type = request.getParameter("branchType");
            String com_name = request.getParameter("comName");
            String com_id = request.getParameter("comId");
            System.out.println(branch_name);
            System.out.println(branch_code);
            System.out.println(com_name);
    		System.out.println(com_id);
            
            Branch b = new Branch();
            b.setBranchCode(branch_code);
            b.setBranchName(branch_name);
            b.setBranchType(branch_type);
            b.setComName(com_name);
            b.setComId(com_id);
            BranchService branchService = new BranchService();
            try {
                branchService.add(b);
                PrintWriter out = response.getWriter();
                //out.print("添加成功");
                out.print("<script>" + "alert('添加成功');"+ "document.location.href='branch-data.jsp';"+ "</script>");
                log.error("新增部门信息");
            } catch (SQLException e) {
                PrintWriter out = response.getWriter();
             //   out.print("添加失败");
                out.print("<script>" + "alert('添加失败');"+ "document.location.href='branch-data.jsp';"+ "</script>");
                e.printStackTrace();
            }
        }
        /**
         * 查詢
         */
        else if (action.equals("list")) {
        	String page = request.getParameter("page");
			String rows = request.getParameter("rows");
//			System.out.print("当前页码："+page+"当前行数："+rows);
			int brapage = Integer.parseInt(page);
			int brarows = Integer.parseInt(rows);
			System.out.print("当前页码："+brapage+"当前行数："+brarows);
        	JSONArray JA = new JSONArray();
        	JSONObject resultJson = new JSONObject();
                    try {
                          /*  List<Branch> branchList = service.fill();
                            if (branchList.size()!=0) {
								for (int i = 0; i < branchList.size(); i++) {
									JSONObject js =  new JSONObject();
									Branch b = branchList.get(i);
									js.put("id", b.getId());
									js.put("branchName", b.getBranchName());
									js.put("branchType", b.getBranchType());
									js.put("branchCode", b.getBranchCode());
									js.put("comName", b.getComName());
									js.put("comId", b.getComId());
									JA.add(js);
								}
							} else {
								OutputHelper.StringOutPut("no result",response);
								return;
							}*/
                    	List<Branch> branchList = service.fill(brapage, brarows);
                    	if (branchList.size() != 0) {
                    		for (int i = 0; i < branchList.size(); i++) {
								JSONObject js =  new JSONObject();
								Branch b = branchList.get(i);
								js.put("id", b.getId());
								js.put("branchName", b.getBranchName());
								js.put("branchType", b.getBranchType());
								js.put("branchCode", b.getBranchCode());
								js.put("comName", b.getComName());
								js.put("comId", b.getComId());
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
//                            this.StringOutPut(JA.toString(), response);
                            /*request.setAttribute("BranchList", branchList);
                            request.getRequestDispatcher("data-newbranch.jsp").forward(
                                    request, response);*/
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                } else if (action.equals("list2")) {
                    String id1 = request.getParameter("id");
                    try {
                            Branch branch = service.fill(id1);
                            request.setAttribute("branch", branch);
                            request.getRequestDispatcher("data-newbranch.jsp").forward(
                                    request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
        /**
         * 修改
         */               
                else if (id != null&&action.equals("update")) {
                            try {
                                    Branch branch = service.fill(id);
                                    request.setAttribute("branch", branch);
                                    request.getRequestDispatcher("branchUpdate.jsp").forward(
                                            request, response);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        } else if(action.equals("update2")){
                                String branch_id = request.getParameter("id");
                                String branch_code = request.getParameter("branchCode");
                                String branch_name = request.getParameter("branchName");
                                String branch_type = request.getParameter("branchType");
                                String com_name = request.getParameter("comName");
                                String com_id = request.getParameter("comId");
                                Branch b = new Branch();
                                b.setId(branch_id);
                                b.setBranchCode(branch_code);
                                b.setBranchName(branch_name);
                                b.setBranchType(branch_type);
                                b.setComName(com_name);
                                b.setComId(com_id);
                                System.out.println("部门编码："+branch_code);
                                System.out.println("名称："+branch_name);

                                BranchService branchService = new BranchService();
                                try {
                                        int result = branchService.update(b);
                                     //  PrintWriter out = response.getWriter();
                                      // out.print("<script>" + "alert('修改成功');"+ "document.location.href='branch-data.jsp';"+ "</script>");
                                     //  out.print("修改成功");
                                        OutputHelper.StringOutPut(""+result, response);
                                        log.error("修改部门信息");
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }               
                        }
        /**
         * 删除
         */
                     if(action.equals("delete")) {
                         try {                            
                            List<Branch> branchDelete = service.fill();
                            request.setAttribute("branchDelete", branchDelete);
                            request.getRequestDispatcher("branchDelete.jsp").forward(
                                    request, response);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                      }
                      else if(action.equals("delete2")) {
                          String[] ids=request.getParameterValues("Id");
                         // String id1=request.getParameter("id");
                          try {
                            //service.delete(id1);
                            service.delete(ids);
                              response.sendRedirect("ManageBranchServlet?action=delete");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                      }
                      else if(action.equals("delete3")) {                         
                          String id1=request.getParameter("id");
                          try {                          
                            service.delete(id1);
                              response.sendRedirect("ManageBranchServlet?action=list");
                              log.error("删除部门信息");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
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
