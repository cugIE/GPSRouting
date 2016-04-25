package com.servlet.route;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Position;
import com.bean.Route;
import com.util.OutputHelper;

/**
 * Servlet implementation class PostRouteServlet
 */
@WebServlet("/PostRouteServlet")
public class PostRouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostRouteServlet() {
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
		String islogin  = request.getParameter("islogin");
		String region_id = request.getParameter("region_id");
		Route tempR = new Route();
		int result = 0;
		if (gener_id==null||longitude ==null || latitude ==null){
			OutputHelper.StringOutPut("error_index", response);
			return;
		}
		tempR.setGener_id(Integer.parseInt(gener_id));
		tempR.setLongitude(Double.parseDouble(longitude));
		tempR.setLatitude(Double.parseDouble(latitude));
		
		
		
		
		
		
		Position tempP = new Position();
		tempP.setGener_id(Integer.parseInt(gener_id));
		tempP.setLongitude(Double.parseDouble(longitude));
		tempP.setLatitude(Double.parseDouble(latitude));
		
		try {
			if(Position.isPosition(gener_id)){
				result = Position.changePosition(tempP);
			}
			else{
				result = Position.addOnePosition(tempP);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			OutputHelper.StringOutPut(""+result, response);
			e.printStackTrace();
			return;
		}
		
		
		
		
		
		if(islogin!=null){ 
			tempR.setIslogin(Integer.parseInt(islogin));
		}
		if(region_id!=null){ 
			tempR.setRegion_id(Integer.parseInt(region_id));
		}
		
		try {
			result = Route.addOneRoute(tempR);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			OutputHelper.StringOutPut(""+result, response);
			e.printStackTrace();
			return;
		}
		
		
		
		OutputHelper.StringOutPut(""+result, response);
	}

}
