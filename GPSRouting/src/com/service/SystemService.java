package com.service;

import java.sql.SQLException;
import java.util.List;

import com.bean.LogInfo;
import com.bean.Site;
import com.dao.LoginfoDao;
import com.dao.SiteDao;
import com.sun.org.apache.regexp.internal.recompile;

public class SystemService {

	 /**
	  * 查询所有日志信息服务
	 * @return
	 * @throws SQLException
	 */
	public  List<LogInfo>  fill() throws SQLException{
	        LoginfoDao dao=new LoginfoDao();    
	        return dao.fill();
	    }
	public List<LogInfo> fill(int page,int rows)throws SQLException {
		LoginfoDao dao =new LoginfoDao();
		return dao.fill(page, rows);
	}
	/**
	 * 查询网站参数信息
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public Site fillsite(String Id) throws SQLException{
		SiteDao dao = new SiteDao();
		return dao.fillsite(Id);
	}
	
	/**
	 * 更新网站信息
	 * @param site
	 * @return
	 * @throws SQLException
	 */
	public int updatewebsite(Site site)throws SQLException {
		SiteDao dao = new SiteDao();
		return dao.updateSite(site);
	}
	
}
