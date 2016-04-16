package com.servlet.route;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Position;
import com.util.OutputHelper;

/**
 * Servlet implementation class GetPostionServlet
 */
@WebServlet("/GetPostionServlet")
public class GetPostionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostionServlet() {
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
				
				OutputHelper.StringOutPut(Position.GetAllPosition(gener_id).toString(), response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (index.equals("history")) {
			String gener_id = request.getParameter("gener_id");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			if (gener_id == null||start==null||end ==null) {				
				OutputHelper.StringOutPut("error_history", response);
				return;
			}
			try {
				OutputHelper.StringOutPut(Position.GetAllPosition(gener_id,Timestamp.valueOf(start),Timestamp.valueOf(end)).toString(), response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			OutputHelper.StringOutPut("error_index", response);
		}
	}

}
