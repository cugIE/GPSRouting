package com.bean;

import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.util.DBHelper;


public class Route {
	private int gener_id;
	//private String gener;
	private double longitude;
	private double latitude;
	private int islogin;
	private String time;
	private int rtr_id;
	public Route(int gener_id, double longitude, double latitude, int islogin,
			String time, int rtr_id) {
		super();
		this.gener_id = gener_id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.islogin = islogin;
		this.time = time;
		this.rtr_id = rtr_id;
	}
	public Route() {
		super();
		this.gener_id = 0;
		this.longitude = 0;
		this.latitude = 0;
		this.islogin = 0;
		this.time = null;
		this.rtr_id = 0;
	}
	public int getGener_id() {
		return gener_id;
	}
	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getIslogin() {
		return islogin;
	}
	public void setIslogin(int islogin) {
		this.islogin = islogin;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getRtr_id() {
		return rtr_id;
	}
	public void setRtr_id(int rtr_id) {
		this.rtr_id = rtr_id;
	}
	
	public static JSONArray GetAllRoute(String gener_id, String start, String end) throws SQLException{
		JSONArray positions= new JSONArray();
		String sql = "select people_name, route_longitude, route_latitude, route_time, route_islogin "
				+ "from route inner join people "
				+ "on people_id = route.gener_id "
				+ "where route.gener_id = " + gener_id +" and "
				+ "route_time < '" + end + "' and route_time > '" + start + "' order by route_time";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			JSONObject pos = new JSONObject();
			pos.put("gener", rs.getString(1));
			pos.put("longitude", rs.getDouble(2));
			pos.put("latitude", rs.getDouble(3));
			pos.put("time", rs.getString(4));
			pos.put("islogin", rs.getString(5));
			positions.add(pos);
		}
		dbh.DBClose(rs);
		return positions;
	}
	public static JSONArray GetAllRoute(String region_id) throws SQLException{
		JSONArray positions= new JSONArray();
		//Map<String, String> position = new HashMap<String, String>();
		String sql = "select people_name, route_longitude, route_latitude, route_time, region_name "
				+ "from (route inner join people "
				+ "on people_id = route.gener_id) "
				+ "inner join region "
				+ "on region.region_id = route.region_id "
				+ "where route.region_id = " + region_id ;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			JSONObject pos = new JSONObject();
			pos.put("gener", rs.getString(1));
			pos.put("longitude", rs.getString(2));
			pos.put("latitude", rs.getString(3));
			pos.put("time", rs.getString(4));
			pos.put("region", rs.getString(5));
			positions.add(pos);
		}
		dbh.DBClose(rs);
		return positions;
	}
	public static int addOneRoute(Route rt) throws SQLException{
		int result = 0;
		if (rt == null){
			result = -1;
		}
		else{
			String sql = "insert into route"
					+ "(gener_id, route_longitude, route_latitude, route_islogin, rtr_id)"
					+ " values('"+rt.getGener_id()+ "','" 
					+ rt.getLongitude() + "','" 
					+ rt.getLatitude() + "','" 
					+ rt.getIslogin() + "','"
					+ rt.getRtr_id()  + "')"; 
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;	
	}
	
}
