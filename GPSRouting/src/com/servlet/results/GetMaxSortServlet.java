package com.servlet.results;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PtrConnection;
import com.util.OutputHelper;

/**
 * Servlet implementation class GetMaxSortServlet
 */
@WebServlet("/GetMaxSortServlet")
public class GetMaxSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMaxSortServlet() {
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
		String period_id = request.getParameter("period_id");
		if(period_id == null){
			OutputHelper.StringOutPut("error_period_id", response);
		}
		else{
			try {
				int max = PtrConnection.getMaxSort(period_id);
				if (max == -1){
					System.out.println(max);
					OutputHelper.StringOutPut("error", response);
					return;
				}
				else {
					OutputHelper.StringOutPut(""+max, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
