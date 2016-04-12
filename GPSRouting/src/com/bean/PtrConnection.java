package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private Region region_content;
	private Period period_content;
	
	public Region getRegion_content() {
		return region_content;
	}
	public void setRegion_content(Region region_content) {
		this.region_content = region_content;
	}
	public Period getPeriod_content() {
		return period_content;
	}
	public void setPeriod_content(Period period_content) {
		this.period_content = period_content;
	}
	public PtrConnection(){
		this.id = null;
		this.period_id = null;
		this.region_id = null;
		this.sort = 0;
		this.gener = null;
		this.gener_id = 1;
		this.region_content = new Region();
		this.period_content = new Period();
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
			String sql = "insert into periodtoregion"
					+ "(period_id, region_id, sort, gener_id)"
					+ "values('"+ pc.getPeriod_id() + "','"
					+ pc.getRegion_id() + "','" 
					+ pc.getSort()  + "','" 
					+ pc.getGener_id() + "')";
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			ResultSet rs = dbh.getResultSet("SELECT LAST_INSERT_ID()");
			if (rs.next()) {  
		        result = rs.getInt(1);  
		    }  else {  
		        // throw an exception from here  
		    	result=-1;
		    } 
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
	public static int deleteConnectionfromPeriod(String prid) throws SQLException{
		if (prid == null){
			return -1;
		}
		else{
			String sql = "delete from periodtoregion "
					+ "where period_id=" + prid;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}	
	}
	public static int deleteConnectionfromRegion(String rgid) throws SQLException{
		if (rgid == null){
			return -1;
		}
		else{
			String sql = "delete from periodtoregion "
					+ "where region_id=" + rgid;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}	
	}
	public static List<PtrConnection> getAllRegion(String prid) throws SQLException{
		List<PtrConnection> ptrs = new ArrayList<PtrConnection>();
		String sql = "SELECT region.region_id, region_name, region_intro, region_type, sort, periodtoregion.gener_id, people_name,ptr_id "
				+ "from ((periodtoregion "
				+ "inner join period "
				+ "on periodtoregion.period_id = period.period_id) "
				+ "inner join region "
				+ "on periodtoregion.region_id = region.region_id) "
				+ "inner join people on periodtoregion.gener_id = people.people_id "
				+ "where periodtoregion.period_id = "+prid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while (rs.next()){
			PtrConnection ptr = new PtrConnection();
			ptr.getRegion_content().setId(rs.getString(1));
			ptr.getRegion_content().setName(rs.getString(2));
			ptr.getRegion_content().setIntro(rs.getString(3));
			ptr.getRegion_content().setType(rs.getString(4));
			ptr.setSort(rs.getInt(5));
			ptr.setGener_id(rs.getInt(6));
			ptr.setGener(rs.getString(7));
			ptr.setId(rs.getString(8));
			ptrs.add(ptr);
			
		}
		dbh.DBClose(rs);
		return ptrs;
	}
	public static int getMaxSort(String prid) throws SQLException {
		if(prid==null){
			return -1;
		}
		int result = -1;
		String sql = "select max(sort) from periodtoregion where period_id="+prid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while (rs.next()){
			result = rs.getInt(1);
		}
		dbh.DBClose(rs);
		return result;
	}
	public static boolean switchSort(String ptr_id_bef, String ptr_id_aft) throws SQLException {
		DBHelper dbh = new DBHelper();
		String sql_fst = "select sort from periodtoregion where ptr_id = "+ptr_id_bef + ";";
		ResultSet rs_fst = dbh.getResultSet(sql_fst);
		int sort_fst =-1;
		while (rs_fst.next()){
			sort_fst = rs_fst.getInt(1);
		}
		rs_fst.close();
		String sql_snd = "select sort from periodtoregion where ptr_id = "+ptr_id_aft + ";";
		ResultSet rs_snd = dbh.getResultSet(sql_snd);
		int sort_snd =-1;

		while (rs_snd.next()){
			sort_snd = rs_snd.getInt(1);
		}

		if(sort_fst==-1||sort_snd==-1){
			return false;
		}
		String update_fst = "update periodtoregion set sort = "+sort_snd +" where ptr_id = "+ptr_id_bef+ ";";
		String update_snd = "update periodtoregion set sort = "+sort_fst +" where ptr_id = "+ptr_id_aft+ ";";
		int result_fst=-1;
		try {
			result_fst = dbh.updateDatabase(update_fst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result_snd=-1;
		try {
			result_snd = dbh.updateDatabase(update_snd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result_fst==1&&result_snd==1){
			return true;
		}
		else{
			return false;
		}
	}
	public static List<Period> getAllPeriod(String rgid) throws SQLException{
		List<Period> prds = new ArrayList<Period>();
		String sql = "select period_shift, period_time "
				+ "from periodtoregion "
				+ "inner join period "
				+ "on periodtoregion.period_id = period.period_id "
				+ "where periodtoregion.region_id = " + rgid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while (rs.next()){
			Period prd = new Period();
			prd.setShift(rs.getString(1));
			prd.setTime(rs.getString(2));
			prds.add(prd);
			
		}
		dbh.DBClose(rs);
		return prds;
	}
	
}
