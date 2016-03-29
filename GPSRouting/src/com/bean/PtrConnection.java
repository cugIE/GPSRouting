package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.util.DBHelper;

/**
 * 
 * @author XinLi
 *
 */
public class PtrConnection {
	private String id;
	private String period_id;
	private String region_id;
	private int sort;
	private String gener;
	private int gener_id;
	
	public PtrConnection(){
		this.id = null;
		this.period_id = null;
		this.region_id = null;
		this.sort = 0;
		this.gener = null;
		this.gener_id = 1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeriod_id() {
		return period_id;
	}
	public void setPeriod_id(String periodId) {
		this.period_id = periodId;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String regionId) {
		this.region_id = regionId;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public static int addOneConnection(PtrConnection pc) throws SQLException{
		int result = 0;
		if (pc == null){
			result = -1;
		}
		else{
			String sql = "inset into periodtoregion "
					+ "(period_id, region_id, sort, gener_id)"
					+ "values('"+ pc.getPeriod_id() + "','"
					+ pc.getRegion_id() + "','" 
					+ pc.getSort()  + "','" 
					+ pc.getGener_id() + "')";
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;
	}
	public static int deleteOneConnection(String ptrid) throws SQLException{
		if (ptrid == null){
			return -1;
		}
		else{
			String sql = "delete from periodtoregion "
					+ "where ptr_id=" + ptrid;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}	
	}
	private static List<Region> getAllRegion(String prid){
		String sql = "SELECT region_id, region.branch_id, branch_name, region_intro, region_gps, region_qrcode, region.gener_id, people_name, region_type, region_range "
				+ "from "
				+ "(("
				+ "periodtoregion "
				+ "inner join period "
				+ "on region.branch_id = branch.branch_id"
				+ ") inner) "
				+ "inner join people "
				+ "on region.gener_id = people.people_id where period_id =" + prid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		if(rs.next()){
			Region rg = new Region();
			rg.setId(rs.getString(1));
			rg.setBranch_id(rs.getInt(2));
			rg.setBranch(rs.getString(3));
			rg.setIntro(rs.getString(4));
			rg.setGps(rs.getString(5));
			rg.setQrcode(rs.getString(6));
			rg.setGener_id(rs.getInt(7));
			rg.setGener(rs.getString(8));
			rg.setType(rs.getString(9));
			rg.setRange(rs.getInt(10));
			dbh.DBClose(rs);
			return rg;
		}
		else{
			return null;
		}
		return null;
	}
	private static List<Period> getAllPeriod(String rgid){
		return null;
	}
	
}
