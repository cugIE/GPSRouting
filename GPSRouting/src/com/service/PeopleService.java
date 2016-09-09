package com.service;

import java.sql.SQLException;

import com.bean.People;
import com.dao.PeopleDao;

import java.util.List;

//人员信息管理服务类
public class PeopleService {
	
	//添加人员信息
	public int add(People people)throws SQLException {
		PeopleDao dao = new PeopleDao();
		return dao.add(people);
	}
	
	//检查登录
	public boolean check(People people)throws SQLException {
		PeopleDao dao = new PeopleDao();
		return dao.check(people);
	}
	public  List<People>  fill() throws SQLException{
        PeopleDao dao=new PeopleDao();    
        return dao.fill();
    }
	public List<People> fill(int page, int rows)throws SQLException {
		PeopleDao dao = new PeopleDao();
		return dao.fill(page, rows);
	}
    public  People  fill(String  Id) throws SQLException{
        PeopleDao dao=new PeopleDao();    
        return dao.fill(Id);
    }
    public List<People> findPeo(String branchid) throws SQLException{
		PeopleDao dao = new PeopleDao();
		return dao.findPeo(branchid);
	}
    /**
     * 修改
     * @param people
     * @return
     * @throws SQLException
     */
    public int update(People people) throws SQLException{
        PeopleDao dao=new PeopleDao();
        return dao.update(people);       
    }
    
    /**
     * 更新密码
     * @param peo
     * @param newpwd
     * @return
     * @throws SQLException
     */
    public int update(People peo,String newpwd) throws SQLException {
		PeopleDao dao= new PeopleDao();
		return dao.update(peo, newpwd);
	}
    /**
     *  删除
     * @param Id
     * @throws SQLException
     */
    public void delete(String Id) throws SQLException{
        PeopleDao dao=new PeopleDao();
         dao.delete(Id);;  
    }
     
    public void delete(String[] Id) throws SQLException {
        PeopleDao dao=new PeopleDao();
        dao.delete(Id);
    }
}
