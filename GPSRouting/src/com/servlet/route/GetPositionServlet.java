package com.servlet.route;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.Branch;
import com.bean.Position;
import com.service.BranchService;
import com.util.OutputHelper;

/**
 * Servlet implementation class GetPositionServlet
 */
@WebServlet("/GetPositionServlet")
public class GetPositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPositionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String index = request.getParameter("index");
		String branch_id_string = request.getParameter("branch_id");
		HttpSession session = request.getSession();
		int branch_id = (int)session.getAttribute("SesBranchId");
		BranchService bs = new BranchService();
		String branchType = "";
		try {
			Branch brch = bs.fill(branch_id+"");
			branchType = brch.getBranchType();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(branchType.equals("管理")){
			if(index==null){
				index="all";
			}
		}
		else{
			index = "branch";
			branch_id_string = ""+branch_id;
		}
		if(index.equals("all")){
			try {
				OutputHelper.StringOutPut(Position.GetAllPosition().toString(), response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (index.equals("gener")){
			String gener_id = request.getParameter("gener_id");
			if (gener_id == null) {				
				OutputHelper.StringOutPut("error_gener", response);
				return;
			}
			
			try {
				JSONObject jsonObject = Position.GetAllPosition(gener_id);
				if (jsonObject.getString("gener")==null) {
					OutputHelper.StringOutPut("no result", response);
				}
				else{
					OutputHelper.StringOutPut(Position.GetAllPosition(gener_id).toString(), response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (index.equals("branch")){
			
			if (branch_id_string == null) {				
				OutputHelper.StringOutPut("error_branch", response);
				return;
			}
			
			try {
				JSONArray ja = Position.GetAllPositionFromBranch(branch_id_string);
				if (ja.size()==0) {
					OutputHelper.StringOutPut("no result", response);
				}
				else{
					OutputHelper.StringOutPut(ja.toString(), response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			OutputHelper.StringOutPut("error_index", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
