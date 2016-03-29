package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBHelper;

/**
 * Question class to assemble all components
 * @author XinLi
 *
 */
public class Question {
	private String id;
	private String title;
	private String possibleAsw;
	private String normalAsw;
	private String region;
	private int region_id;
	private String gener;
	private int gener_id;
	/**
	 * Constructor
	 */
	public Question(){
		this.id = null;
		this.title = null;
		this.possibleAsw = null;
		this.normalAsw = null;
		this.region = null;
		this.gener = null;
		this.region_id = 0;
		this.gener_id = 0;
	}
	/**
	 * Constructor with its variables
	 * @param id
	 * @param title
	 * @param possibleAsw
	 * @param normalAsw
	 * @param region
	 * @param gener
	 */
	public Question(String id, String title, String possibleAsw,
			String normalAsw, String region, String gener, int region_id, int gener_id) {
		super();
		this.id = id;
		this.title = title;
		this.possibleAsw = possibleAsw;
		this.normalAsw = normalAsw;
		this.region = region;
		this.gener = gener;
		this.region_id = region_id;
		this.gener_id = gener_id;
	}

	
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public int getGener_id() {
		return gener_id;
	}
	public void setGener_id(int gener_id) {
		this.gener_id = gener_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPossibleAsw() {
		return possibleAsw;
	}
	public void setPossibleAsw(String possibleAsw) {
		this.possibleAsw = possibleAsw;
	}
	public String getNormalAsw() {
		return normalAsw;
	}
	public void setNormalAsw(String normalAsw) {
		this.normalAsw = normalAsw;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getGener() {
		return gener;
	}
	public void setGener(String gener) {
		this.gener = gener;
	}
	
	/**
	 * Add one Question
	 * @param qs
	 * @return
	 * @throws SQLException 
	 */
	public static int addOneQuestion(Question qs) throws SQLException{
		int result = 0;
		if (qs == null){
			result = -1;
		}
		else{
			String sql = "inset into question "
					+ "(question_title, question_asws, question_r_asws, gener_id, åregion_id)"
					+ "values('"+ qs.getTitle() + "','"
					+ qs.getPossibleAsw() + "','" 
					+ qs.getNormalAsw()  + "','" 
					+ qs.getGener_id()  + "','" 
					+ qs.getRegion_id()  + "')"; 
			DBHelper dbh = new DBHelper();
			result = dbh.updateDatabase(sql);
			dbh.DBClose();
		}
		return result;
	}
	/**
	 * Delete one Question
	 * @param qID
	 * @return
	 * @throws SQLException 
	 */
	public static int deleteOneQuestion(String id) throws SQLException{
		if (id == null){
			return -1;
		}
		else{
			String sql = "delete from question "
					+ "where question_id=" + id;
			DBHelper dbh = new DBHelper();
			int result = dbh.updateDatabase(sql);
			dbh.DBClose();
			return result;
		}	}
	
	/**
	 * Get all questions
	 * @return
	 */
//	public static List<Question> getAllQuestion(){
//
//	}
	
	/**
	 * Get all questions from one region;
	 * @param rgif
	 * @return
	 * @throws SQLException 
	 */
	public static List<Question> getAllQuestion(String rgid) throws SQLException{
		List<Question> qts = new ArrayList<Question>();
		String sql = "SELECT question_id, question_title, question_asws, question_r_asws, question.gener_id, people_name"
				+ "from "
				+ "question "
				+ "inner join people "
				+ "on question.gener_id = people.people_id"
				+ "where question.region_id =" + rgid;
		DBHelper dbh = new DBHelper();
		ResultSet rs = dbh.getResultSet(sql);
		while(rs.next()){
			Question qt = new Question();
			qt.setId(rs.getString(1));
			qt.setTitle(rs.getString(2));
			qt.setPossibleAsw(rs.getString(3));
			qt.setNormalAsw(rs.getString(4));
			qt.setGener_id(rs.getInt(5));
			qt.setGener(rs.getString(6));
			qt.setRegion_id(Integer.parseInt(rgid));
			qts.add(qt);
		}
		dbh.DBClose(rs);
		return qts;
	}
	
	
}
