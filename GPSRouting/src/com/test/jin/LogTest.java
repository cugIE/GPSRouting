package com.test.jin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //如果直接 className.class 日志输出到全局的 即rootLogger 指定的文件中
	  Logger logger = Logger.getLogger(LogTest.class.getName());
	   //如果指定logger名字，则是把日志，输出到pay-log 指定的日志文件中去
	//  Logger logger = Logger.getLogger(“pay-log”);
	//  MyLog4j.getSomething();
	  System.out.println("================97987==============");
	  logger.info("日志信息开始!");
	  logger.info("日志信息结束!");
	  try {
	   Integer.parseInt("a");
	  } catch (NumberFormatException e) {
	   logger.error("解析数字出现异常",e);
	   e.printStackTrace();
	  }
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

}
