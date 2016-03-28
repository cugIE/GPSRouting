package com.bean;

import java.util.List;
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
	private String branch;
	private String intro;
	private String gps;
	private String qrcode;
	private String gener;
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
	Region(String id, String branch, String intro, String gps,
			String qrcode, int sort, String gener, String type, int range) {
		super();
		this.id = id;
		this.branch = branch;
		this.intro = intro;
		this.gps = gps;
		this.qrcode = qrcode;
		this.gener = gener;
		this.type = type;
		this.range = range;
	}
	/**
	 * Constructor of Region
	 */
	 Region(){
		this.id = null;
		this.branch = null;
		this.intro = null;
		this.gps = null;
		this.qrcode = null;
		this.gener = null;
		this.type = null;
		this.range = 0;
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
	 * Get all data of this sheet.
	 * @return
	 */
	public static List<Region> getAllRegion(){
		return null;
	}
	/**
	 * Get all regions from one branch
	 * @param brID
	 * @return
	 */
	public static List<Region> getAllRegion(String brID){
		return null;
	}
	/**
	 * Get one data through searching for its id
	 * @param id
	 * @return
	 */
	public static Region getOneRegion(String id){
		return null;
	}
	
	/**
	 * Delete one date through its id
	 * @param id
	 * @return
	 */
	public static boolean deleteOneRegion(String id){
		return false;
	}
	
	/**
	 * Add one data, rg.id should be null;
	 * @param rg
	 * @return
	 */
	public static boolean addOneRegion(Region rg){
		return false;
	}
	
	/**
	 * Change one data
	 * This method will change all fields.
	 * @param rg: rg.id should indicate where you want to change. 
	 * @return
	 */
	public static boolean changeOneRegion(Region rg){
		return false;
	}
	/**
	 * Change one data
	 *  This method will change only one field.
	 * @param rgid should indicate where you want to change. 
	 * @param fieldname
	 * @param content : What you r gonna chance to
	 * @return
	 */
	public static boolean changeOneRegion(String rgid, String fieldname, String content){
		return false;
	}
	
}
