package com.bean;

import java.util.List;

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
	private String regionId;
	private String gener;
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
		this.regionId = null;
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
			String normalAsw, String region, String gener, String regionId) {
		super();
		this.id = id;
		this.title = title;
		this.possibleAsw = possibleAsw;
		this.normalAsw = normalAsw;
		this.region = region;
		this.gener = gener;
		this.regionId = regionId;
	}

	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
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
	 */
	public static boolean addOneQuestion(Question qs){
		return false;
	}
	/**
	 * Delete one Question
	 * @param qID
	 * @return
	 */
	public static boolean deleteOneQuestion(String qID){
		return false;
	}
	
	/**
	 * Get all questions
	 * @return
	 */
	public static List<Question> getAllQuestion(){
		return null;
	}
	
	/**
	 * Get all questions from one region;
	 * @param rgif
	 * @return
	 */
	public static List<Question> getAllQuestion(String rgid){
		return null;
	}
	
	
}
