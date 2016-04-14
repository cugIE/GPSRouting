package com.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateHelper {

	public static String[] getInitDate(){
		Date endDate = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
		Date startDate = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(endDate);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -1); //设置为前3月
		startDate = calendar.getTime(); //得到前3月的时间
		
	
		String end = dateFormat.format(endDate); 
		String start = dateFormat.format(startDate); 
		System.out.println(end+";"+start); 
		String result[] = {start,end};
		return result;
	}
}
