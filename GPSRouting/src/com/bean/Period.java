/**
 * 
 */
package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.util.DBHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author XinLi
 *
 */
public class Period {
	private String id;
	private String shift;
	private String time;
	private String gener;
	private int gener_id;
	private int sheet_id;
	
	public int getSheet_id() {
		return sheet_id;
	}
	public void setSheet_id(int sheet_id) {
		this.sheet_id = sheet_id;
	}
	public int getGener_id() {
		return gener_id;
	}
	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}
	public Period(String id, String shift, String time, String gener, int gener_id, int sheet_id) {
		super();
		this.id = id;
		this.shift = shift;
		this.time = time;
		this.gener = gener;
		this.gener_id = gener_id;
		this.sheet_id = sheet_id;
	}
	public Period(){
		this.id = null;
		this.shift = null;
		this.time = null;
		this.gener = null;
		this.gener_id = 1;
		this.sheet_id = 1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGener() {
		return gener;
	}
	public void setGener(String gener) {
		this.gener = gener;
	}
	public static int addOnePeriod(Period pr) throws SQLException{
		int result = 0;
		if (pr == null){
			result = -1;
		}
		else{
			String sql = "insert into period"
					+ "(sheet_id, period_shift, period_time, gener_id)"
					+ " values('" + pr.getSheet_id() + "','" 
					+ pr.getShift() + "','" 
					+ pr.getTime()  + "','" 
					+ pr.getGener_id()  + "')"; 
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;	
	}
	public static boolean isShift(String shift, String sheet_id){
		String sql = "select period_id from period where period_shift = '"+shift+"' and sheet_id = "+sheet_id+" limit 1;";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		try {
			if(rs.next()){
				dbh.DBClose(rs);
				return true;
			}
			else{
				dbh.DBClose(rs);
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
			return false;

		}
	}
	public static int deleteOnePeriod(String prid) throws SQLException{
		if (prid == null){
			return -1;
		}
		else{
			PtrConnection.deleteConnectionfromPeriod(prid);
			String sql = "delete from period "
					+ "where period_id =" + prid;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}	
	}
	public static int deletePeriodFromSheet(String sheetid) throws SQLException{
		if (sheetid == null){
			return -1;
		}
		else{
			String sql = "SELECT period_id "
					+ "from period "
					+ "where period.sheet_id = " + sheetid;
			DBHelper dbh = new DBHelper();
			DBHelper dbh2 = new DBHelper();
			ResultSet rs = dbh.getResultSet(sql);
			int result =0;
			while(rs.next()) {
				PtrConnection.deleteConnectionfromPeriod(rs.getString(1));
				String sql2 = "delete from period "
						+ "where period_id =" + rs.getString(1);
				result += dbh2.updateDatabase(sql2);
			}
			dbh.DBClose();
			dbh2.DBClose();

			return result;
		}
	}
	public static int deleteOneShift(String shid, String shift) throws SQLException{
		if (shid == null||shift == null){
			return -1;
		}
		else{
			String sql = "select period_id from period "
					+ "where sheet_id =" + shid + " "
					+ "and period_shift = '"+shift+"'";
			DBHelper dbh = new DBHelper();
			ResultSet rs = dbh.getResultSet(sql);
			int result = 0;
			while(rs.next()){
				deleteOnePeriod(rs.getString(1));
			}
			dbh.DBClose();
			return result;
		}	
	}
//	public static int changeOnePeriod(Period pr){
//		return 1;
//	}
	public static List<Period> getAllPeriod(String sheetid) throws SQLException{
		List<Period> prs = new ArrayList<Period>();
		String sql = "SELECT period_id, period_shift, period_time, period.gener_id, people_name "
				+ "from period "
				+ "inner join people "
				+ "on period.gener_id = people.people_id "
				+ "where period.sheet_id = " + sheetid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Period pr = new Period();
			pr.setId(rs.getString(1));
			pr.setShift(rs.getString(2));
			pr.setTime(rs.getString(3));
			pr.setGener_id(rs.getInt(4));
			pr.setGener(rs.getString(5));
			
			prs.add(pr);
		}
		dbh.DBClose(rs);
		return prs;
	}
	public static List<Period> getAllPeriod(String sheetid,String shift) throws SQLException{
		List<Period> prs = new ArrayList<Period>();
		String sql = "SELECT period_id, period_shift, period_time, period.gener_id, people_name "
				+ "from period "
				+ "inner join people "
				+ "on period.gener_id = people.people_id "
				+ "where period.sheet_id = " + sheetid
				+ " and period_shift= '" + shift + "'";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Period pr = new Period();
			pr.setId(rs.getString(1));
			pr.setShift(rs.getString(2));
			pr.setTime(rs.getString(3));
			pr.setGener_id(rs.getInt(4));
			pr.setGener(rs.getString(5));

			prs.add(pr);
		}
		dbh.DBClose(rs);
		return prs;
	}
	public static Period getOnePeriod(String periodid) throws SQLException{
		String sql = "SELECT period_id, period_shift, period_time, period.gener_id, people_name "
				+ "from period "
				+ "inner join people "
				+ "on period.gener_id = people.people_id "
				+ "where period.period_id = " + periodid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		Period pr = null;
		if(rs.next()){
			pr = new Period();
			pr.setId(rs.getString(1));
			pr.setShift(rs.getString(2));
			pr.setTime(rs.getString(3));
			pr.setGener_id(rs.getInt(4));
			pr.setGener(rs.getString(5));
			
		}
		dbh.DBClose(rs);
		return pr;
	}
	public static String getShift(String shid) throws SQLException{
		String sql = "select distinct period_shift from period "
				+ "where sheet_id =" + shid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		JSONArray JA = new JSONArray();
		int i = 0;
		while(rs.next()){
			JSONObject jo = new JSONObject();
			jo.put("shift",rs.getString(1));
			jo.put("text",rs.getString(1));
			if(i == 0){
				jo.put("selected",true);
			}
			i++;
			JA.add(jo);
		}
		dbh.DBClose(rs);
		return JA.toString();
		
	}
	public static List<Period> getPeriodfromShift(String shid, String shift) throws SQLException{
		String sql = "select period_id, period_time from period "
				+ "where sheet_id =" + shid
				+ " and period_shift= '" + shift + "'";
		List<Period> prs = new ArrayList<Period>();
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Period pr = new Period();
			pr.setId(rs.getString(1));
			pr.setTime(rs.getString(2));
			prs.add(pr);
		}
		dbh.DBClose(rs);
		return prs;
		
	}
	public static String getRestPeriod(String shid, String shift) throws SQLException{
		String alltime = "00:00,02:00,04:00,06:00,08:00,10:00,12:00,14:00,16:00,18:00,20:00,22:00";
		String sql = "select period_id, period_time from period "
				+ "where sheet_id =" + shid
				+ " and period_shift= '" + shift + "'";
		List<Period> prs = new ArrayList<Period>();
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Period pr = new Period();
			pr.setTime(rs.getString(2));
			alltime.replace(rs.getString(2),"");
			prs.add(pr);
		}
		System.out.println(alltime);
		String[] temp = alltime.split(",");
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<temp.length;i++){
			JSONObject jsonObject = new JSONObject();
			if(!temp[i].equals("")) {
				jsonObject.put("time", temp[i]);
				jsonObject.put("text", temp[i]);
				jsonArray.add(jsonObject);
			}
		}

		dbh.DBClose(rs);
		System.out.println(jsonArray.toString());
		return jsonArray.toString();

	}
	public static int changeOnePeriod(Period prd) throws SQLException{
		if (prd == null){
			return -1;
		}
		else{
			String sql = "update period set "
					+ "period_shift = '" + prd.getShift() + "', "
					+ "period_time= '" + prd.getTime() + "' "
					+ "where period_id = " + prd.getId();
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
				
	}
	
	/**
	 * 表id为sheet_id的时间点数量
	 * @param sheetId
	 * @return
	 * @throws SQLException
	 */
	public static int fillPeriodTimes(String sheetId)throws SQLException {
		if (sheetId == null) {
			return -1;
		} else {
			String sql = "select count(sheet_id) from period where sheet_id = '"+ sheetId +"'";
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
	}
}
