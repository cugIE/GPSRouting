/**
 * 
 */
package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBHelper;

/**
 * @author XinLi
 *
 */
public class Sheet {
	private String id;
	private String name;
	private String intro;
	private String gener;
	private int gener_id;
	private int branch_id;
	
	public Sheet(){
		this.id = null;
		this.name = null;
		this.intro = null;
		this.gener = null;
		this.gener_id = 1;
		this.branch_id = 1;
	}
	
	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getGener() {
		return gener;
	}
	public void setGener(String gener) {
		this.gener = gener;
	}
	public int getGener_id() {
		return gener_id;
	}
	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}
	public static int addOneSheet(Sheet sht) throws SQLException{
		int result = 0;
		if (sht == null){
			result = -1;
		}
		else{
			String sql = "insert into sheet"
					+ "(sheet_name, sheet_intro, gener_id, branch_id)"
					+ "values('"+ sht.getName() + "','"
					+ sht.getIntro() + "','" 
					+ sht.getGener_id()  + "','"
					+ sht.getBranch_id() + "')";
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;
	}
	public static int deleteOneSheet(String id) throws SQLException{
		if (id == null){
			return -1;
		}
		else{
			String sql = "delete from sheet "
					+ "where sheet_id=" + id;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}	
	}
	public static int changeOneSheet(Sheet sht) throws SQLException{
		if (sht == null){
			return -1;
		}
		else{
			String sql = "update sheet set "
					+ "sheet_name = '" + sht.getName() + "', "
					+ "sheet_intro = '" + sht.getIntro() + "', "
					+ "gener_id = '" + sht.getGener_id()+ "', "
					+ "branch_id = '" + sht.getBranch_id() + "' " 
					+ "where sheet_id = " + sht.getId();
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
				
	}
	public static List<Sheet> getAllSheet(String brid) throws SQLException{
		List<Sheet> sts = new ArrayList<Sheet>();
		String sql = "SELECT sheet_id, sheet_name, sheet_intro, sheet.gener_id, people_name "
				+ "from "
				+ "sheet "
				+ "inner join people "
				+ "on sheet.gener_id = people.people_id "
				+ "where sheet.branch_id = " + brid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Sheet st = new Sheet();
			st.setId(rs.getString(1));
			st.setName(rs.getString(2));
			st.setIntro(rs.getString(3));
			st.setGener_id(rs.getInt(4));
			st.setGener(rs.getString(5));
			st.setBranch_id(Integer.parseInt(brid));
			sts.add(st);
		}
		dbh.DBClose(rs);
		return sts;
	}
	
	public static Sheet getOneSheet(String shid) throws SQLException{
		String sql = "SELECT sheet_id, sheet_name, sheet_intro, sheet.gener_id, people_name, sheet.branch_id "
				+ "from "
				+ "sheet "
				+ "inner join people "
				+ "on sheet.gener_id = people.people_id "
				+ "where sheet.sheet_id =" + shid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		if(rs.next()){
			Sheet st = new Sheet();
			st.setId(rs.getString(1));
			st.setName(rs.getString(2));
			st.setIntro(rs.getString(3));
			st.setGener_id(rs.getInt(4));
			st.setGener(rs.getString(5));
			st.setBranch_id(rs.getInt(6));
			dbh.DBClose(rs);
			return st;
		}
		else{
			dbh.DBClose(rs);
			return null;
		}
	}

}
