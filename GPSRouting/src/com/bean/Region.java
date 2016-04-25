package com.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DB;
import com.util.DBHelper;
/**
 * Region class to assemble all components
 * @author XinLi
 *
 */
public class Region {
	/**
	 * The variables of Regions
	 */
	private String id;
	private String name;
	private String branch;
	private int branch_id;
	private String intro;
	private String gps;
	private String qrcode;
	private String gener;
	private int gener_id;
	private String type;
	private int range;

	/**
	 * Constructor
	 * @param id of Region
	 * @param branch
	 * @param intro
	 * @param gps
	 * @param qrcode
	 * @param sort
	 * @param gener
	 * @param type
	 * @param range
	 */
	public Region(String id, String name, String branch, int branch_id, String intro,
			String gps, String qrcode, String gener, int gener_id, String type,
			int range) {
		super();
		this.id = id;
		this.name = name;
		this.branch = branch;
		this.branch_id = branch_id;
		this.intro = intro;
		this.gps = gps;
		this.qrcode = qrcode;
		this.gener = gener;
		this.gener_id = gener_id;
		this.type = type;
		this.range = range;
	}

	/**
	 * Constructor of Region
	 */
	 public Region(){
		this.id = null;
		this.name = null;
		this.branch = null;
		this.intro = null;
		this.gps = "";
		this.qrcode = "http";
		this.gener = null;
		this.type = null;
		this.range = 0;
		this.branch_id = 1;
		this.gener_id = 1;
	}
	
	/**
	 * getter and setter
	 * @return
	 */
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

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getGener_id() {
		return gener_id;
	}

	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public String getGener() {
		return gener;
	}
	public void setGener(String gener) {
		this.gener = gener;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
	/**
	 * Get all data of table 'region'.
	 * @return
	 * @throws SQLException 
	 */
	public static List<Region> getAllRegion() throws SQLException{
		List<Region> rgs = new ArrayList<Region>();
		String sql = "SELECT region_id, region_name, region.branch_id ,branch_name, region_intro, region_gps, region_qrcode, region.gener_id ,people_name, region_type, region_range "
				+ "from "
				+ "("
				+ "region "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id"
				+ ") "
				+ "inner join people "
				+ "on region.gener_id = people.people_id";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Region rg = new Region();
			rg.setId(rs.getString(1));
			rg.setName(rs.getString(2));
			rg.setBranch_id(rs.getInt(3));
			rg.setBranch(rs.getString(4));
			rg.setIntro(rs.getString(5));
			rg.setGps(rs.getString(6));
			rg.setQrcode(rs.getString(7));
			rg.setGener_id(rs.getInt(8));
			rg.setGener(rs.getString(9));
			rg.setType(rs.getString(10));
			rg.setRange(rs.getInt(11));
			rgs.add(rg);
		}
		dbh.DBClose(rs);
		return rgs;
	}
	
	public static List<Region> getAllRoute() throws SQLException{
		List<Region> rgs = new ArrayList<Region>();
		String sql = "SELECT region_id, region_name, region.branch_id , region_range, branch_name "
				+ "from region inner join branch on region.branch_id = branch.branch_id where region_type='route'";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Region rg = new Region();
			rg.setId(rs.getString(1));
			rg.setName(rs.getString(2));
			rg.setBranch_id(rs.getInt(3));
			
			rg.setRange(rs.getInt(4));
			rg.setBranch(rs.getString(5));
			rgs.add(rg);
		}
		dbh.DBClose(rs);
		return rgs;
	}
	public static List<Region> getAllRoute(String branch_id) throws SQLException{
		List<Region> rgs = new ArrayList<Region>();
		String sql = "SELECT region_id, region_name, region.branch_id , region_range, branch_name "
				+ "from region inner join branch on region.branch_id = branch.branch_id where region_type='route' and branch_id = "+branch_id;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Region rg = new Region();
			rg.setId(rs.getString(1));
			rg.setName(rs.getString(2));
			rg.setBranch_id(rs.getInt(3));
			
			rg.setRange(rs.getInt(4));
			rg.setBranch(rs.getString(5));
			rgs.add(rg);
		}
		dbh.DBClose(rs);
		return rgs;
	}
	
	/**
	 * Get all regions from one branch
	 * @param brID
	 * @return
	 * @throws SQLException 
	 */
	public static List<Region> getAllRegion(String brID) throws SQLException{
		List<Region> rgs = new ArrayList<Region>();
		String sql = "SELECT region_id, region_name, region.branch_id, branch_name, region_intro, region_gps, region_qrcode, region.gener_id, people_name, region_type, region_range "
				+ "from "
				+ "("
				+ "region "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id"
				+ ") "
				+ "inner join people "
				+ "on region.gener_id = people.people_id where region.branch_id =" + brID;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Region rg = new Region();
			rg.setId(rs.getString(1));
			rg.setName(rs.getString(2));
			rg.setBranch_id(rs.getInt(3));
			rg.setBranch(rs.getString(4));
			rg.setIntro(rs.getString(5));
			rg.setGps(rs.getString(6));
			rg.setQrcode(rs.getString(7));
			rg.setGener_id(rs.getInt(8));
			rg.setGener(rs.getString(9));
			rg.setType(rs.getString(10));
			rg.setRange(rs.getInt(11));
			rgs.add(rg);
		}
		dbh.DBClose(rs);
		return rgs;
	}
	public static List<Region> getAllRegionWithoutTemp(String brID) throws SQLException{
		List<Region> rgs = new ArrayList<Region>();
		String sql = "SELECT region_id, region_name, region.branch_id, branch_name, region_intro, region_gps, region_qrcode, region.gener_id, people_name, region_type, region_range "
				+ "from "
				+ "("
				+ "region "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id"
				+ ") "
				+ "inner join people "
				+ "on region.gener_id = people.people_id where region.branch_id =" + brID + " and region.region_type<>'temp'";
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Region rg = new Region();
			rg.setId(rs.getString(1));
			rg.setName(rs.getString(2));
			rg.setBranch_id(rs.getInt(3));
			rg.setBranch(rs.getString(4));
			rg.setIntro(rs.getString(5));
			rg.setGps(rs.getString(6));
			rg.setQrcode(rs.getString(7));
			rg.setGener_id(rs.getInt(8));
			rg.setGener(rs.getString(9));
			rg.setType(rs.getString(10));
			rg.setRange(rs.getInt(11));
			rgs.add(rg);
		}
		dbh.DBClose(rs);
		return rgs;
	}
	/**
	 * Get one data through searching for its id
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public static Region getOneRegion(String id) throws SQLException{

		String sql = "SELECT region_id, region_name, region.branch_id, branch_name, region_intro, region_gps, region_qrcode, region.gener_id, people_name, region_type, region_range "
				+ "from "
				+ "("
				+ "region "
				+ "inner join branch "
				+ "on region.branch_id = branch.branch_id"
				+ ") "
				+ "inner join people "
				+ "on region.gener_id = people.people_id where region_id =" + id;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		if(rs.next()){
			Region rg = new Region();

			rg.setId(rs.getString(1));
			rg.setName(rs.getString(2));
			rg.setBranch_id(rs.getInt(3));
			rg.setBranch(rs.getString(4));
			rg.setIntro(rs.getString(5));
			rg.setGps(rs.getString(6));
			rg.setQrcode(rs.getString(7));
			rg.setGener_id(rs.getInt(8));
			rg.setGener(rs.getString(9));
			rg.setType(rs.getString(10));
			rg.setRange(rs.getInt(11));
			dbh.DBClose(rs);
			return rg;
		}
		else{
			dbh.DBClose(rs);
			return null;
		}
	}
	
	/**
	 * Delete one date through its id
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public static int deleteOneRegion(String id) throws SQLException{
		if (id == null){
			return -1;
		}
		else{
			Question.deleteQuestionfromRegion(id);
			PtrConnection.deleteConnectionfromRegion(id);
			String sql = "delete from region "
					+ "where region_id=" + id;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}

	}
	
	/**
	 * Add one data, rg.id should be null;
	 * @param rg
	 * @return
	 * @throws SQLException 
	 */
	public static int addOneRegion(Region rg) throws SQLException{
		int result = 0;
		if (rg == null){
			result = -1;
		}
		else{
			String sql = "insert into region"
					+ "(region_name, branch_id, region_intro, region_gps, region_qrcode, gener_id, region_type, region_range)"
					+ "values('"
					+ rg.getName() + "','"
					+ rg.getBranch_id() + "','"
					+ rg.getIntro()  + "','" 
					+ rg.getGps()  + "','" 
					+ rg.getQrcode()  + "','" 
					+ rg.getGener_id()  + "','" 
					+ rg.getType() + "','"
					+ rg.getRange()  + "')";
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
	
	/**
	 * Change one data
	 * This method will change all fields.
	 * @param rg: rg.id should indicate where you want to change. 
	 * @return
	 * @throws SQLException 
	 */
	public static int changeOneRegion(Region rg) throws SQLException{
		if (rg == null){
			return -1;
		}
		else{
			String sql = "update region set "
					+ "region_name = '" + rg.getName() + "', "
					+ "branch_id = '" + rg.getBranch_id() + "', "
					+ "region_intro = '" + rg.getIntro() + "', "
					+ "region_gps = '" + rg.getGps() + "', "
					+ "region_qrcode = '" + rg.getQrcode() + "', " 
					+ "region_type = '" + rg.getType() + "', "
					+ "region_range = '" + rg.getRange() + "' "
					+ "where region_id = " + rg.getId();
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
				
	}
	public static int addQRCode(String region_id, String qrcode) throws SQLException{
		if (region_id == null||qrcode==null){
			return -1;
		}
		else{
			String sql = "update region set "
					+ "region_qrcode = '" + qrcode + "' " 
					+ "where region_id = " + region_id;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}
				
	}
	/**
	 * Change one data
	 *  This method will change only one field.
	 * @param rgid should indicate where you want to change. 
	 * @param fieldname
	 * @param content : What you r gonna chance to
	 * @return
	 */
//	public static boolean changeOneRegion(String rgid, String fieldname, String content){
//		return false;
//	}
	
}
