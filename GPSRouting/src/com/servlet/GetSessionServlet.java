package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Branch;
import com.service.BranchService;
import com.util.OutputHelper;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetSessionServlet
 */
@WebServlet("/GetSessionServlet")
public class GetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		HttpSession session  = request.getSession();
		if(session.getAttribute("SesBranchId")!=null){
			jsonObject.put("username",session.getAttribute("SesUser"));
			jsonObject.put("id",session.getAttribute("SesId"));
			jsonObject.put("name",session.getAttribute("SesName"));
			BranchService bs = new BranchService();
			try {
				Branch brch = bs.fill((int)session.getAttribute("SesBranchId")+"");
				String branchType = brch.getBranchType();
				jsonObject.put("branch_type", branchType);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jsonObject.put("branch_id",session.getAttribute("SesBranchId"));
			jsonObject.put("team_id",session.getAttribute("SesTeamId"));
			OutputHelper.StringOutPut(jsonObject.toString(), response);
		}
		else{
			OutputHelper.StringOutPut("nosession", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
