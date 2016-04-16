package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.util.DBHelper;

public class Position {
	private int gener_id;
	//private String gener;
	private float longitude;
	private float latitude;
	private int iscurrent;
	private String time;
	public Position(int gener_id, float longitude, float latitude, int iscurrent,
			String time) {
		super();
		this.gener_id = gener_id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.iscurrent = iscurrent;
		this.time = time;
	}
	public Position() {
		super();
		this.gener_id = 0;
		this.longitude = 0;
		this.latitude = 0;
		this.iscurrent = 1;
		this.time = null;
	}
	public int getGener_id() {
		return gener_id;
	}
	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public int getIscurrent() {
		return iscurrent;
	}
	public void setIscurrent(int iscurrent) {
		this.iscurrent = iscurrent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	public static JSONArray GetAllPosition(String gener_id, Timestamp start, Timestamp end) throws SQLException{
		JSONArray positions= new JSONArray();
		String sql = "select people_name, pos_longitude, pos_latitude, pos_time, pos_iscurrent "
				+ "from position inner join people "
				+ "on people_id = position.gener_id "
				+ "where position.gener_id = " + gener_id +" and "
				+ "pos_time < " + end + " and pos_time > " + start + " order by pos_time";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			JSONObject pos = new JSONObject();
			pos.put("gener", rs.getString(1));
			pos.put("longitude", rs.getString(2));
			pos.put("latitude", rs.getString(3));
			pos.put("time", rs.getString(4));
			pos.put("iscurrent", rs.getString(5));
			positions.add(pos);
		}
		dbh.DBClose(rs);
		return positions;
	}
	public static JSONArray GetAllPosition() throws SQLException{
		JSONArray positions= new JSONArray();
		//Map<String, String> position = new HashMap<String, String>();
		String sql = "select people_name, pos_longitude, pos_latitude, pos_time "
				+ "from position inner join people "
				+ "on people_id = position.gener_id "
				+ "where position.iscurrent = 1";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			JSONObject pos = new JSONObject();
			pos.put("gener", rs.getString(1));
			pos.put("longitude", rs.getString(2));
			pos.put("latitude", rs.getString(3));
			pos.put("time", rs.getString(4));
			positions.add(pos);
		}
		dbh.DBClose(rs);
		return positions;
	}
	public static JSONArray GetAllPosition(String gener_id) throws SQLException{
		JSONArray positions= new JSONArray();
		//Map<String, String> position = new HashMap<String, String>();
		String sql = "select people_name, pos_longitude, pos_latitude, pos_time "
				+ "from position inner join people "
				+ "on people_id = position.gener_id "
				+ "where position.iscurrent = 1 and "
				+ "position.gener_id = " + gener_id;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			JSONObject pos = new JSONObject();
			pos.put("gener", rs.getString(1));
			pos.put("longitude", rs.getString(2));
			pos.put("latitude", rs.getString(3));
			pos.put("time", rs.getString(4));
			positions.add(pos);
		}
		dbh.DBClose(rs);
		return positions;
	}
	public static int addOnePosition(Position pst) throws SQLException{
		int result = 0;
		if (pst == null){
			result = -1;
		}
		else{
			String sql = "insert into position"
					+ "(gener_id, pos_longitude, pos_latitude)"
					+ " values('"+pst.getGener_id()+ "','" 
					+ pst.getLongitude() + "','" 
					+ pst.getLatitude() +  "')"; 
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;	
	}
	public static int logoutPosition(String gener_id) throws SQLException{
		int result = 0;
		if (gener_id == null){
			result = -1;
		}
		else{
			String sql = "update position set "
					+ "pos_iscurrent = 0 "
					+ "where gener_id = " + gener_id + " and pos_iscurrent = 1";
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;	
	}
}
