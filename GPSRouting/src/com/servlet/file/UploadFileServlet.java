package com.servlet.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sina.sae.storage.SaeStorage;
import com.sina.sae.util.SaeUserInfo;

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
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
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
//			String finalPath = "saestor://recordpic";
			String realPath = SaeUserInfo.getSaeTmpPath();
			System.out.println(realPath);
			File dir = new File(realPath);
			if(!dir.exists())
				dir.mkdirs();
			FileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("UTF-8");
			try {
				List<FileItem> items = upload.parseRequest(request);
				for(FileItem item: items){
					if(item.isFormField()){
						String fname = item.getFieldName();
						String value = item.getString("UTF-8");
						System.out.println(fname+"-"+value);
					}else{
						String filename = System.currentTimeMillis()
								+item.getName().substring(item.getName().lastIndexOf("."));
						item.write(new File(dir,filename));
						SaeStorage saeStorage = new SaeStorage();
						saeStorage.upload("recordpic", realPath+"/"+filename, filename);
						response.getOutputStream().write(saeStorage.getUrl("recordpic",filename).getBytes());
						
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
