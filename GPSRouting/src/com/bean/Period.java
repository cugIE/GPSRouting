/**
 * 
 */
package com.bean;

import java.sql.Time;
import java.util.List;

/**
 * @author XinLi
 *
 */
public class Period {
	private String id;
	private String shift;
	private Time time;
	private String gener;
	public Period(String id, String shift, Time time, String gener) {
		super();
		this.id = id;
		this.shift = shift;
		this.time = time;
		this.gener = gener;
	}
	public Period(){
		this.id = null;
		this.shift = null;
		this.time = null;
		this.gener = null;
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
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getGener() {
		return gener;
	}
	public void setGener(String gener) {
		this.gener = gener;
	}
	public static boolean addOnePeriod(Question pr){
		return false;
	}
	public static boolean deleteOnePeriod(String prid){
		return false;
	}
	public static boolean changeOnePeriod(Period pr){
		return false;
	}
	public static boolean changeOnePeriod(String prid, String fieldname, String content){
		return false;
	}
	public static List<Period> getAllPeriod(String sheetid){
		return null;
	}
}
