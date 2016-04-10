package com.servlet.qrcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import net.sf.json.JSONObject;

import com.bean.Region;
import com.sina.sae.storage.SaeStorage;
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
		//String size = request.getParameter("size");
		if(region_id!=null){
			try {
				Region rg = Region.getOneRegion(region_id);
				if(rg.getQrcode().equals("http")){
					String gener_id = String.format("%04d", rg.getGener_id());
					JSONObject jso = new JSONObject();
					jso.put("region_id", region_id);
					jso.put("gener_id", gener_id);
					jso.put("secureKey", "8080");
					String text = jso.toString();
					QRCode qrc;
					
					qrc = QRCode.from(text).withSize(1000, 1000);
					
					File file = qrc.to(ImageType.JPG).file();
					
					String filename = region_id+"_"+System.currentTimeMillis()+".jpg";
				//	FileOutputStream outputStream = new FileOutputStream("saestor://qrcode/"+filename);
				//	FileOutputStream outputStream = new FileOutputStream("D:\\"+filename);
				//	Files.copy(file.toPath(), outputStream);
					SaeStorage seaStorage = new SaeStorage();
					seaStorage.upload("qrcode", file.getAbsolutePath(), filename);
					file.delete();
					int result = Region.addQRCode(region_id, seaStorage.getUrl("qrcode",filename));
					if(result==-1){
						OutputHelper.StringOutPut("error", response);
						return;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				OutputHelper.StringOutPut("error", response);
				e.printStackTrace();
				return;
			}
			 RequestDispatcher rd = request.getRequestDispatcher("qrcode.jsp");
	         rd.forward(request,response);
			
		}
		else{
			OutputHelper.StringOutPut("error", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
