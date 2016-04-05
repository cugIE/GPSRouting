package com.servlet.qrcode;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import net.sf.json.JSONObject;

import com.bean.Region;
import com.util.OutputHelper;

/**
 * Servlet implementation class GenerateQRCode
 */
@WebServlet("/GenerateQRCode")
public class GenerateQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateQRCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String region_id = request.getParameter("region_id");
		String size = request.getParameter("size");
		if(region_id!=null){
			try {
				Region rg = Region.getOneRegion(region_id);
				String gener_id = String.format("%04d", rg.getGener_id());
				JSONObject jso = new JSONObject();
				jso.put("region_id", region_id);
				jso.put("gener_id", gener_id);
				jso.put("secureKey", "8080");
				String text = jso.toString();
				QRCode qrc;
				if (size == null){
					qrc = QRCode.from(text);
				}
				else{
					qrc = QRCode.from(text).withSize(Integer.parseInt(size), Integer.parseInt(size));
				}
				response.getOutputStream().write("123".getBytes());
				response.flushBuffer();
				response.getOutputStream().write(qrc.to(ImageType.JPG).stream().toByteArray());
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
