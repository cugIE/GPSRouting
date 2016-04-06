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
		return result;	}
	public static int deleteOnePeriod(String prid) throws SQLException{
		if (prid == null){
			return -1;
		}
		else{
			String sql = "delete from period "
					+ "where period_id =" + prid;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
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
	public static String getShift(String shid) throws SQLException{
		String value ="";
		String sql = "select distinct period_shift from period "
				+ "where sheet_id =" + shid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			value += rs.getString(1) +",";
		}
		dbh.DBClose(rs);
		return value;
		
	}
	public static List<Period> getPeriod(String shid, String shift) throws SQLException{
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
}
