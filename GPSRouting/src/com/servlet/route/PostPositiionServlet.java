package com.servlet.route;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Position;
import com.util.OutputHelper;

/**
 * Servlet implementation class PostPositiionServlet
 */
@WebServlet("/PostPositiionServlet")
public class PostPositiionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostPositiionServlet() {
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
		String gener_id = request.getParameter("gener_id");
		String longitude = request.getParameter("longitude");
		String latitude  = request.getParameter("latitude");
		Position tempP = new Position();
		int result = 0;
		if (gener_id==null||longitude ==null || latitude ==null){
			OutputHelper.StringOutPut("error_index", response);
			return;
		}
		tempP.setGener_id(Integer.parseInt(gener_id));
		tempP.setLongitude(Float.parseFloat(longitude));
		tempP.setLatitude(Float.parseFloat(latitude));
		
		try {
			result = Position.addOnePosition(tempP);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputHelper.StringOutPut(""+result, response);
	}

}
