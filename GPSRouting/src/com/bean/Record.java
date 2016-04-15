package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.util.DBHelper;

public class Record {
	private String id;
	private String gps;
	private String asws;
	private String error;
	private String picture;
	private Timestamp start;
	private Timestamp end;
	private Timestamp submit;
	private int ptr_id;
	private String period_shift;
	private Time period_time;
	private String region;
	private String type;
	private int gener_id;
	private String gener;
	private String status;
	private int checker_id;
	private String checker;
	private String note;
	private Timestamp check;
	private String branch;
	private int branch_id;
	
	public Record() {
		super();
		this.id = null;
		this.gps = null;
		this.asws = null;
		this.error = null;
		this.picture = null;
		this.start = null;
		this.end = null;
		this.submit = null;
		this.ptr_id = 0;
		this.branch_id = 0;
		this.period_shift = null;
		this.period_time = null;
		this.region = null;
		this.gener_id = 1;
		this.gener = null;
		this.status = null;
		this.checker_id = 1;
		this.checker = null;
		this.note = null;
		this.check = null;
		this.type = null;
		this.branch = null;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getAsws() {
		return asws;
	}

	public void setAsws(String asws) {
		this.asws = asws;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Timestamp getSubmit() {
		return submit;
	}

	public void setSubmit(Timestamp submit) {
		this.submit = submit;
	}

	public int getPtr_id() {
		return ptr_id;
	}

	public void setPtr_id(int ptr_id) {
		this.ptr_id = ptr_id;
	}

	public String getPeriod_shift() {
		return period_shift;
	}

	public void setPeriod_shift(String period_shift) {
		this.period_shift = period_shift;
	}

	public Time getPeriod_time() {
		return period_time;
	}

	public void setPeriod_time(Time period_time) {
		this.period_time = period_time;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getGener_id() {
		return gener_id;
	}

	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}

	public String getGener() {
		return gener;
	}

	public void setGener(String gener) {
		this.gener = gener;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getChecker_id() {
		return checker_id;
	}

	public void setChecker_id(int checker_id) {
		this.checker_id = checker_id;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String not) {
		this.note = not;
	}

	public Timestamp getCheck() {
		return check;
	}

	public void setCheck(Timestamp check) {
		this.check = check;
	}
	//SELECT record_id, record_gps, record_asws, record_error, record_picture, record_start, record_end, record_submit, record.ptr_id, region.region_name, period.period_shift, period.period_time, record.gener_id, people_name, checker_name, record_note, record_check_time from (((gastube_inspection.record inner join people on record.gener_id = people.people_id) inner join periodtoregion on periodtoregion.ptr_id = record.ptr_id) inner join period on period.period_id = periodtoregion.period_id) inner join region on region.region_id = periodtoregion.region_id;
	public static List<Record> getAllRecord(String start, String end) throws SQLException{
		List<Record> rcrds = new ArrayList<Record>();
		String sql = "SELECT record_id, record_error, record_start, record_end, record_submit, record.ptr_id, region.region_name, period.period_shift, period.period_time, record.gener_id, people_name, record_status, checker_name, record_check_time, region.region_type, branch.branch_name "
				+ "from ((((gastube_inspection.record "
				+ "inner join people "
				+ "on record.gener_id = people.people_id) "
				+ "inner join periodtoregion "
				+ "on periodtoregion.ptr_id = record.ptr_id) "
				+ "inner join period "
				+ "on period.period_id = periodtoregion.period_id) "
				+ "inner join region "
				+ "on region.region_id = periodtoregion.region_id) "
				+ "inner join branch "
				+ "on branch.branch_id = region.branch_id "
				+ "where record_submit > '" + start + "' "
				+ "and record_submit < '" + end + "';";
		
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Record rcrd = new Record();
			rcrd.setId(rs.getString(1));
			rcrd.setError(rs.getString(2));
			rcrd.setStart(rs.getTimestamp(3));
			rcrd.setEnd(rs.getTimestamp(4));
			rcrd.setSubmit(rs.getTimestamp(5));
			rcrd.setPtr_id(rs.getInt(6));
			rcrd.setRegion(rs.getString(7));
			rcrd.setPeriod_shift(rs.getString(8));
			rcrd.setPeriod_time(rs.getTime(9));
			rcrd.setGener_id(rs.getInt(10));
			rcrd.setGener(rs.getString(11));
			rcrd.setStatus(rs.getString(12));
			rcrd.setChecker(rs.getString(13));
			rcrd.setCheck(rs.getTimestamp(14));
			rcrd.setType(rs.getString(15));
			rcrd.setBranch(rs.getString(16));
			rcrds.add(rcrd);
		}
		dbh.DBClose(rs);
		
		return rcrds;
	}
	
	public static List<Record> getAllRecord(String gid, String start, String end) throws SQLException{
		List<Record> rcrds = new ArrayList<Record>();
		String sql = "SELECT record_id, record_error, record_start, record_end, record_submit, record.ptr_id, region.region_name, period.period_shift, period.period_time, record.gener_id, people_name, record_status, checker_name, record_check_time, region.region_type, branch.branch_name "
				+ "from ((((gastube_inspection.record "
				+ "inner join people "
				+ "on record.gener_id = people.people_id) "
				+ "inner join periodtoregion "
				+ "on periodtoregion.ptr_id = record.ptr_id) "
				+ "inner join period "
				+ "on period.period_id = periodtoregion.period_id) "
				+ "inner join region "
				+ "on region.region_id = periodtoregion.region_id) "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id "
				+ "where record.gener_id = " + gid + " and record_submit > '" + start + "' "
						+ "and record_submit < '" + end + "';";
		
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Record rcrd = new Record();
			rcrd.setId(rs.getString(1));
			rcrd.setError(rs.getString(2));
			rcrd.setStart(rs.getTimestamp(3));
			rcrd.setEnd(rs.getTimestamp(4));
			rcrd.setSubmit(rs.getTimestamp(5));
			rcrd.setPtr_id(rs.getInt(6));
			rcrd.setRegion(rs.getString(7));
			rcrd.setPeriod_shift(rs.getString(8));
			rcrd.setPeriod_time(rs.getTime(9));
			rcrd.setGener_id(rs.getInt(10));
			rcrd.setGener(rs.getString(11));
			rcrd.setStatus(rs.getString(12));
			rcrd.setChecker(rs.getString(13));
			rcrd.setCheck(rs.getTimestamp(14));
			rcrd.setType(rs.getString(15));
			rcrd.setBranch(rs.getString(16));
			rcrds.add(rcrd);
		}
		dbh.DBClose(rs);
		
		return rcrds;
	}
	public static List<Record> getAllRecord(int branchid, String start, String end) throws SQLException{
		List<Record> rcrds = new ArrayList<Record>();
		String sql = "SELECT record_id, record_error, record_start, record_end, record_submit, record.ptr_id, region.region_name, period.period_shift, period.period_time, record.gener_id, people_name, record_status, checker_name, record_check_time, region.region_type, branch.branch_name "
				+ "from ((((gastube_inspection.record "
				+ "inner join people "
				+ "on record.gener_id = people.people_id) "
				+ "inner join periodtoregion "
				+ "on periodtoregion.ptr_id = record.ptr_id) "
				+ "inner join period "
				+ "on period.period_id = periodtoregion.period_id) "
				+ "inner join region "
				+ "on region.region_id = periodtoregion.region_id) "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id "
				+ "where branch.branch_id = " + branchid + " and record_submit > '" + start + "' "
						+ "and record_submit < '" + end + "';";
		
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Record rcrd = new Record();
			rcrd.setId(rs.getString(1));
			rcrd.setError(rs.getString(2));
			rcrd.setStart(rs.getTimestamp(3));
			rcrd.setEnd(rs.getTimestamp(4));
			rcrd.setSubmit(rs.getTimestamp(5));
			rcrd.setPtr_id(rs.getInt(6));
			rcrd.setRegion(rs.getString(7));
			rcrd.setPeriod_shift(rs.getString(8));
			rcrd.setPeriod_time(rs.getTime(9));
			rcrd.setGener_id(rs.getInt(10));
			rcrd.setGener(rs.getString(11));
			rcrd.setStatus(rs.getString(12));
			rcrd.setChecker(rs.getString(13));
			rcrd.setCheck(rs.getTimestamp(14));
			rcrd.setType(rs.getString(15));
			rcrd.setBranch(rs.getString(16));
			rcrds.add(rcrd);
		}
		dbh.DBClose(rs);
		
		return rcrds;
	}
	public static List<Record> getAllRecord(String gid, int branchid, String start, String end) throws SQLException{
		List<Record> rcrds = new ArrayList<Record>();
		String sql = "SELECT record_id, record_error, record_start, record_end, record_submit, record.ptr_id, region.region_name, period.period_shift, period.period_time, record.gener_id, people_name, record_status, checker_name, record_check_time, region.region_type, branch.branch_name "
				+ "from ((((gastube_inspection.record "
				+ "inner join people "
				+ "on record.gener_id = people.people_id) "
				+ "inner join periodtoregion "
				+ "on periodtoregion.ptr_id = record.ptr_id) "
				+ "inner join period "
				+ "on period.period_id = periodtoregion.period_id) "
				+ "inner join region "
				+ "on region.region_id = periodtoregion.region_id) "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id "
				+ "where record.gener_id = " + gid + " and branch.branch_id = " + branchid + " and record_submit > '" + start + "' "
						+ "and record_submit < '" + end + "';";
		
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Record rcrd = new Record();
			rcrd.setId(rs.getString(1));
			rcrd.setError(rs.getString(2));
			rcrd.setStart(rs.getTimestamp(3));
			rcrd.setEnd(rs.getTimestamp(4));
			rcrd.setSubmit(rs.getTimestamp(5));
			rcrd.setPtr_id(rs.getInt(6));
			rcrd.setRegion(rs.getString(7));
			rcrd.setPeriod_shift(rs.getString(8));
			rcrd.setPeriod_time(rs.getTime(9));
			rcrd.setGener_id(rs.getInt(10));
			rcrd.setGener(rs.getString(11));
			rcrd.setStatus(rs.getString(12));
			rcrd.setChecker(rs.getString(13));
			rcrd.setCheck(rs.getTimestamp(14));
			rcrd.setType(rs.getString(15));
			rcrd.setBranch(rs.getString(16));
			rcrds.add(rcrd);
		}
		dbh.DBClose(rs);
		
		return rcrds;
	}
	public static int addOneRecord(Record rcd) throws SQLException{
		int result = 0;
		if (rcd == null){
			result = -1;
		}
		else{
			String sql = "insert into record"
					+ "(record_gps, record_asws, record_error, record_picture, "
					+ "record_start, record_end, ptr_id, "
					+ "gener_id, record_note)"
					+ "values('"+ rcd.getGps() + "','"
					+ rcd.getAsws() + "','" 
					+ rcd.getError()  + "','" 
					+ rcd.getPicture()  + "','" 
					+ rcd.getStart().toString()  + "','" 
					+ rcd.getEnd().toString()  + "','" 
					+ rcd.getPtr_id()  + "','" 
					+ rcd.getGener_id()  + "','" 
					+ rcd.getNote()  + "')"; 
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
	public static int changeOneRecord(Record rcd) throws SQLException{
		if (rcd == null){
			return -1;
		}
		else{
			String sql = "update record set "
					+ "record_asws = '" + rcd.getAsws() + "', "
					+ "record_error = '" + rcd.getError() + "', "
					+ "record_picture = '" + rcd.getPicture() + "', "
					+ "record_submit = '" + rcd.getSubmit().toString() + "', "
					+ "record_note = '" + rcd.getNote() +  "' "
					+ "where record_id = " + rcd.getId();
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
	}
	public static int checkOneRecord(String checker, String id) throws SQLException{
		if (id == null){
			return -1;
		}
		else{
			String sql = "update record set "
					+ "checker_name = '" + checker + "', "
					+ "record_status = '1', "
					+ "record_check_time = '" +new Timestamp(System.currentTimeMillis()).toString()+ "' "
					+ "where record_id = " + id;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
	}
	public static int deleteOneRecord(String rcdid) throws SQLException{
		if (rcdid == null){
			return -1;
		}
		else{
			String sql = "delete from record "
					+ "where record_id =" + rcdid;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
	}
	public static Record getOneRecord(String rcdid) throws SQLException{
		String sql = "SELECT record_id, record_gps, record_asws, record_error, record_picture, record_start, record_end, record_submit, record.ptr_id, region.region_name, period.period_shift, period.period_time, record.gener_id, people_name, record_status, checker_name, record_note, record_check_time, region.region_type, branch.branch_name "
				+ "FROM ( ( ( (`record`  "
				+ "inner join people "
				+ "on record.gener_id = people.people_id ) "
				+ "inner join periodtoregion "
				+ "on periodtoregion.ptr_id = record.ptr_id) "
				+ "inner join period "
				+ "on period.period_id = periodtoregion.period_id) "
				+ "inner join region "
				+ "on region.region_id = periodtoregion.region_id) "
				+ "inner join branch "
				+ "on branch.branch_id = region.branch_id "
				+ "WHERE record_id="+rcdid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		Record rcrd = new Record();
		if(rs.next()){
			rcrd.setId(rs.getString(1));
			rcrd.setGps(rs.getString(2));
			rcrd.setAsws(rs.getString(3));
			rcrd.setError(rs.getString(4));
			rcrd.setPicture(rs.getString(5));
			rcrd.setStart(rs.getTimestamp(6));
			rcrd.setEnd(rs.getTimestamp(7));
			rcrd.setSubmit(rs.getTimestamp(8));
			rcrd.setPtr_id(rs.getInt(9));
			rcrd.setRegion(rs.getString(10));
			rcrd.setPeriod_shift(rs.getString(11));
			rcrd.setPeriod_time(rs.getTime(12));
			rcrd.setGener_id(rs.getInt(13));
			rcrd.setGener(rs.getString(14));
			rcrd.setStatus(rs.getString(15));
			rcrd.setChecker(rs.getString(16));
			rcrd.setNote(rs.getString(17));
			rcrd.setCheck(rs.getTimestamp(18));
			rcrd.setType(rs.getString(19));
			rcrd.setBranch(rs.getString(20));
			dbh.DBClose();
			return rcrd;
		}
		else{
			return null;
		}
	}
	
	//[{"title":"是否漏油","possasws":"是,否","normalasws":"否","choosedasws":"否","error":"0"},{"title":"是否数值正常","possasws":"是,否","normalasws":"是","choosedasws":"否","error":"1"},{"title":"表的读书在什么数字附近","possasws":"60,70,80,90","normalasws":"60","choosedasws":"70","error":"1"}]
	
}
