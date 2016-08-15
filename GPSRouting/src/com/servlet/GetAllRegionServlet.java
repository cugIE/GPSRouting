package com.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bean.PtrConnection;
import com.bean.Region;
import com.util.OutputHelper;
import com.util.TeamidtoName;

public class GetAllRegionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetAllRegionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index = request.getParameter("index");
		//System.out.print(index+"1");
		if (index.equals("branch")){
			String branchID = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else {
				try {
					List<Region> regions;
					regions = Region.getAllRegion(branchID);
					if (regions.size()!=0){

						for (int i = 0; i < regions.size(); i++ ){
							JSONObject js = new JSONObject();
							Region rg = regions.get(i);
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("branch", rg.getBranch());
							js.put("intro", rg.getIntro());
							js.put("gps", rg.getGps());
							js.put("qrcode", rg.getQrcode());
							js.put("type", rg.getType());
							js.put("gener", rg.getGener());
							JA.add(js);
						}
					}
					else{
						OutputHelper.StringOutPut("no result",response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}
		}
		else if (index.equals("all")){
			JSONArray JA = new JSONArray();
			try {
				List<Region> regions;
				regions = Region.getAllRegion();
				if (regions.size()!=0){
					
					for (int i = 0; i < regions.size(); i++ ){
						JSONObject js = new JSONObject();
						Region rg = regions.get(i);
						js.put("id", rg.getId());
						js.put("name", rg.getName());
						js.put("branch", rg.getBranch());
						js.put("intro", rg.getIntro());
						js.put("gps", rg.getGps());
						js.put("qrcode", rg.getQrcode());
						js.put("type", rg.getType());
						js.put("gener", rg.getGener());
						JA.add(js);
					}
				}
				else{
					OutputHelper.StringOutPut("no result",response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.StringOutPut(JA.toString(), response);
		}
		else if (index.equals("period")){
			String periodID = request.getParameter("period_id");
			JSONArray JA = new JSONArray();
			if (periodID == null) {
				this.StringOutPut("error_period", response);
			}
			else {
				try {
					List<PtrConnection> ptrs;
					ptrs = PtrConnection.getAllRegion(periodID);
					if (ptrs.size()!=0){
						
						for (int i = 0; i < ptrs.size(); i++ ){
							JSONObject js = new JSONObject();
							PtrConnection ptr = ptrs.get(i);
							js.put("id", ptr.getRegion_content().getId());
							js.put("name", ptr.getRegion_content().getName());
							js.put("branch", ptr.getRegion_content().getBranch());
							js.put("intro", ptr.getRegion_content().getIntro());
							js.put("type", ptr.getRegion_content().getType());
							js.put("sort", ptr.getSort());
							js.put("gener", ptr.getGener());
							js.put("gener_id", ptr.getGener_id());
							js.put("ptr_id",Integer.parseInt(ptr.getId()));
							JA.add(js);
						}
					}
					else{
						OutputHelper.StringOutPut(JA.toString(),response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}
		}
		else if(index.equals("region")){

			String regionID = request.getParameter("region_id");
			JSONObject js = new JSONObject();
			if (regionID == null) {
				this.StringOutPut("error_region", response);
			}
			else {

				try {
					Region rg = Region.getOneRegion(regionID);
					if (rg == null){
						OutputHelper.StringOutPut("no result",response);
						return;
					}
					else{
						js.put("id", rg.getId());
						js.put("name", rg.getName());
						js.put("branch", rg.getBranch());
						js.put("intro", rg.getIntro());
						js.put("gps", rg.getGps());
						js.put("qrcode", rg.getQrcode());
						js.put("type", rg.getType());
						js.put("gener", rg.getGener());
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(js.toString(), response);
			}
		}
		else if(index.equals("rest")){
			String branchID = request.getParameter("branch_id");
			String periodID = request.getParameter("period_id");
			String sheetID = request.getParameter("sheet_id");
			System.out.println("部门id："+branchID+",时间点id："+periodID+",表id："+sheetID);
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else{
				
				try {
//					List<Region> AllRegion = Region.getAllRegionWithoutTemp(branchID);
					List<Region> AllRegion = Region.getAllRegionFromSheet(branchID, sheetID);
					List<PtrConnection> SelectedRegion = PtrConnection.getAllRegion(periodID);
					if(AllRegion.size()==SelectedRegion.size()){
						OutputHelper.StringOutPut(JA.toString(),response);
						return;
					}
					else if (SelectedRegion.size()==0){							
						for (int i = 0; i < AllRegion.size(); i++ ){
							JSONObject js = new JSONObject();
							Region rg = AllRegion.get(i);
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("type", rg.getType());
							JA.add(js);
						}
				
						OutputHelper.StringOutPut(JA.toString(),response);
						return;
					}
					else{
						int number = AllRegion.size()-SelectedRegion.size();
						for (int i = 0; i < SelectedRegion.size(); i ++){
							
							for (int j = 0; j < AllRegion.size(); j ++){
								if(SelectedRegion.get(i).getRegion_content().getId().equals(AllRegion.get(j).getId())){
									AllRegion.remove(j);
									break;
								}
							}
							if(AllRegion.size()==number){
								break;
							}
						}
						for (int i = 0; i < AllRegion.size(); i++ ){
							JSONObject js = new JSONObject();
							Region rg = AllRegion.get(i);
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("type", rg.getType());
							JA.add(js);
						}
				
						OutputHelper.StringOutPut(JA.toString(),response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if (index.equals("route")){
			String branch_id = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branch_id == null) {
				try {
					List<Region> rgsList = Region.getAllRoute();

					for (int i = 0; i < rgsList.size(); i++ ){
						JSONObject js = new JSONObject();
						Region tempRegion = rgsList.get(i);
						js.put("id", tempRegion.getId());
						js.put("name", tempRegion.getName());
						js.put("branch", tempRegion.getBranch());
						JA.add(js);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
				
			}
			else {
				try {
					List<Region> rgsList = Region.getAllRoute(branch_id);

					for (int i = 0; i < rgsList.size(); i++ ){
						JSONObject js = new JSONObject();
						Region tempRegion = rgsList.get(i);
						js.put("id", tempRegion.getId());
						js.put("name", tempRegion.getName());
						js.put("branch", tempRegion.getBranch());
						JA.add(js);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.StringOutPut(JA.toString(), response);
			}
		}
		else {
			this.StringOutPut("error_index", response);
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("index");
		String regionType = request.getParameter("regionType");
		TeamidtoName id2Name = new TeamidtoName();
		//System.out.print(index+"1");
		if (index.equals("branch") && regionType.equals("all")){
			String branchID = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else {
				try {
					List<Region> regions;
					regions = Region.getAllRegion(branchID);

					for (int i = 0; i < regions.size(); i++ ){
						JSONObject js = new JSONObject();
						Region rg = regions.get(i);
						js.put("id", rg.getId());
						js.put("name", rg.getName());
						js.put("branch", rg.getBranch());
						js.put("sheet_name", id2Name.sheid2name(""+rg.getSheet_id()));
						js.put("intro", rg.getIntro());
						js.put("gps", rg.getGps());
						js.put("qrcode", rg.getQrcode());
						if(rg.getType().equals("site")){
							js.put("type", "巡站点");
						}
						else if(rg.getType().equals("route")){
							js.put("type", "巡线点");
						}
						else{
							js.put("type", "临时");
						}
						js.put("gener", rg.getGener());
						JA.add(js);
					}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("total", 20);
				jsonObject.put("rows", JA);
				OutputHelper.StringOutPut(jsonObject.toString(), response);
			}
		}else if(index.equals("branch") && regionType.equals("site")) {
			String branchID = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else {
				try {
					List<Region> regions;
					regions = Region.getAllRegion(branchID);

					for (int i = 0; i < regions.size(); i++ ){
						JSONObject js = new JSONObject();
						Region rg = regions.get(i);
						if (rg.getType().equals(regionType)) {
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("branch", rg.getBranch());
							js.put("sheet_name", id2Name.sheid2name(""+rg.getSheet_id()));
							js.put("intro", rg.getIntro());
							js.put("gps", rg.getGps());
							js.put("qrcode", rg.getQrcode());
							if(regionType.equals("site")){
								js.put("type", "巡站点");
							}
							else if(regionType.equals("route")){
								js.put("type", "巡线点");
							}
							else{
								js.put("type", "临时");
							}
							js.put("gener", rg.getGener());
							JA.add(js);
						}			
					}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("total", 20);
				jsonObject.put("rows", JA);
				OutputHelper.StringOutPut(jsonObject.toString(), response);
			}
		}else if (index.equals("branch") && regionType.equals("route")) {
			String branchID = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else {
				try {
					List<Region> regions;
					regions = Region.getAllRegion(branchID);

					for (int i = 0; i < regions.size(); i++ ){
						JSONObject js = new JSONObject();
						Region rg = regions.get(i);
						if (rg.getType().equals(regionType)) {
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("branch", rg.getBranch());
							js.put("sheet_name", id2Name.sheid2name(""+rg.getSheet_id()));
							js.put("intro", rg.getIntro());
							js.put("gps", rg.getGps());
							js.put("qrcode", rg.getQrcode());
							if(regionType.equals("site")){
								js.put("type", "巡站点");
							}
							else if(regionType.equals("route")){
								js.put("type", "巡线点");
							}
							else{
								js.put("type", "临时");
							}
							js.put("gener", rg.getGener());
							JA.add(js);
						}			
					}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("total", 20);
				jsonObject.put("rows", JA);
				OutputHelper.StringOutPut(jsonObject.toString(), response);
			}
		}else if (index.equals("branch") && regionType.equals("巡站点")) {
			String branchID = request.getParameter("branch_id");
			JSONArray JA = new JSONArray();
			if (branchID == null) {
				this.StringOutPut("error_branch", response);
			}
			else {
				try {
					List<Region> regions;
					regions = Region.getAllRegion(branchID);

					for (int i = 0; i < regions.size(); i++ ){
						JSONObject js = new JSONObject();
						Region rg = regions.get(i);
						if (rg.getType().equals(regionType)) {
							js.put("id", rg.getId());
							js.put("name", rg.getName());
							js.put("branch", rg.getBranch());
							js.put("sheet_name", id2Name.sheid2name(""+rg.getSheet_id()));
							js.put("intro", rg.getIntro());
							js.put("gps", rg.getGps());
							js.put("qrcode", rg.getQrcode());
							if(regionType.equals("site")){
								js.put("type", "巡站点");
							}
							else if(regionType.equals("route")){
								js.put("type", "巡线点");
							}
							else{
								js.put("type", "临时");
							}
							js.put("gener", rg.getGener());
							JA.add(js);
						}			
					}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("total", 20);
				jsonObject.put("rows", JA);
				OutputHelper.StringOutPut(jsonObject.toString(), response);
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public void StringOutPut (String str, HttpServletResponse response){
		System.out.println(str);
		response.setContentType("charset=utf-8");
		try {
			response.getOutputStream().write(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
