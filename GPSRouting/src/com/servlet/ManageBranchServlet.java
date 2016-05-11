package com.servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Branch;
import com.service.BranchService;

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
        /**
         * 添加
         */
        if (action.equals("add")) {
            response.setContentType("text/html;charset=utf-8");
            //String branch_id = request.getParameter("branch_id");
            String branch_code = request.getParameter("branch_code");
            String branch_name = request.getParameter("branch_name");
            String branch_type = request.getParameter("branch_type");
            String com_name = request.getParameter("com_name");
            String com_id = request.getParameter("com_id");
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
                out.print("<script>" + "alert('添加成功');"+ "document.location.href='data-newbranch.jsp';"+ "</script>");
            } catch (SQLException e) {
                PrintWriter out = response.getWriter();
             //   out.print("添加失败");
                out.print("<script>" + "alert('添加失败');"+ "document.location.href='data-newbranch.jsp';"+ "</script>");
                e.printStackTrace();
            }
        }
        /**
         * 查詢
         */
        else if (action.equals("list")) {
                    try {
                            List<Branch> branchList = service.fill();
                            request.setAttribute("BranchList", branchList);
                            request.getRequestDispatcher("data-newbranch.jsp").forward(
                                    request, response);
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
                                    request.getRequestDispatcher("branchUpdateN.jsp").forward(
                                            request, response);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        } else if(action.equals("update2")){
                                String branch_id = request.getParameter("branch_id");
                                String branch_code = request.getParameter("branch_code");
                                String branch_name = request.getParameter("branch_name");
                                String branch_type = request.getParameter("branch_type");
                                String com_name = request.getParameter("com_name");
                                String com_id = request.getParameter("com_id");
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
                                        branchService.update(b);
                                        PrintWriter out = response.getWriter();
                                        out.print("<script>" + "alert('修改成功');"+ "document.location.href='data-newbranch.jsp';"+ "</script>");
                                     //  out.print("修改成功");
                                         
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
	
}
