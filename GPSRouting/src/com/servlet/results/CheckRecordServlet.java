package com.servlet.results;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Record;
import com.util.OutputHelper;

/**
 * Servlet implementation class CheckRecordServlet
 */
@WebServlet("/CheckRecordServlet")
public class CheckRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String index = request.getParameter("index");
		String record_id = request.getParameter("record_id");
		String checker = (String) request.getSession().getAttribute("SesName");
		int checkerTeam = (int) request.getSession().getAttribute("SesTeamId");
		String comment = request.getParameter("comment");
		System.out.println("cherkerTeam:"+checkerTeam);
		if(index!=null){
			try {
				if(checkerTeam > 3){
					OutputHelper.StringOutPut("lowpower", response);
					return;
				}
				if (record_id == null || checker == null) {
					OutputHelper.StringOutPut("error", response);
					return;
				}
				String[] ids = record_id.split(",");
				int result = 0;
				for(int i = 0; i < ids.length; i++) {
					result = result + Record.checkOneRecord(checker, ids[i], comment);
				}
				if (result == 0) {
					OutputHelper.StringOutPut("error", response);
					return;
				} else {
					OutputHelper.StringOutPut("" + result, response);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				if (record_id == null || checker == null) {
					OutputHelper.StringOutPut("error", response);
					return;
				}
				int result = Record.checkOneRecord(checker, record_id, comment);
				if (result == 0) {
					OutputHelper.StringOutPut("error", response);
					return;
				} else {
					OutputHelper.StringOutPut("" + result, response);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
