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
 * Servlet implementation class SwitchSortServlet
 */
@WebServlet("/SwitchSortServlet")
public class SwitchSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchSortServlet() {
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
		String ptr_id_bef = request.getParameter("ptr_id_bef");
		String ptr_id_aft = request.getParameter("ptr_id_aft");
		try {
			if(PtrConnection.switchSort(ptr_id_bef, ptr_id_aft)){
				OutputHelper.StringOutPut("success", response);
			}
			else{
				OutputHelper.StringOutPut("fail", response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
