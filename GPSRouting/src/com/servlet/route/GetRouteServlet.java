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
import com.bean.Route;
import com.util.OutputHelper;

/**
 * Servlet implementation class GetRouteServlet
 */
@WebServlet("/GetRouteServlet")
public class GetRouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRouteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String index = request.getParameter("index");
		String gener_id = request.getParameter("gener_id");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(index==null){
			OutputHelper.StringOutPut("error_index", response);
			return;
		}
		else if(index.equals("gener")){
			if (gener_id == null||start==null||end ==null) {				
				OutputHelper.StringOutPut("error_para", response);
				return;
			}
			try {
				OutputHelper.StringOutPut(Route.GetAllRoute(gener_id,start,end).toString(), response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
