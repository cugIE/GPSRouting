package com.service;

import java.sql.SQLException;
import java.util.List;

import com.bean.Branch;
import com.bean.People;
import com.dao.BranchDao;
import com.dao.PeopleDao;

public class BranchService {
	/**
     * 添加
     * @param Branch
     * @return
     * @throws SQLException
     */
    public int add(Branch branch) throws SQLException {
        BranchDao dao=new BranchDao();
        return dao.add(branch);          
    }
    /**
     * 查询
     * @return
     * @throws SQLException
     */
    public  List<Branch>  fill() throws SQLException{
        BranchDao dao=new BranchDao();    
        return dao.fill();
    }
    public List<Branch> fill(int page,int rows)throws SQLException {
		BranchDao dao = new BranchDao();
		return dao.fill(page, rows);
	}
    public  Branch  fill(String  Id) throws SQLException{
        BranchDao dao=new BranchDao();    
        return dao.fill(Id);
    }
    /**
     * 修改
     * @param Branch
     * @return
     * @throws SQLException
     */
    public int update(Branch branch) throws SQLException{
        BranchDao dao=new BranchDao();
        return dao.update(branch);       
    }
    /**
     *  删除
     * @param Id
     * @throws SQLException
     */
    public void delete(String Id) throws SQLException{
        BranchDao dao=new BranchDao();
         dao.delete(Id);;  
    }
     
    public void delete(String[] Id) throws SQLException {
        BranchDao dao=new BranchDao();
        dao.delete(Id);
    }

    /**
     * 根据branchid查出branch值
     * @param branch
     * @return
     * @throws SQLException
     */
    public boolean check(Branch branch)throws SQLException {
		BranchDao dao = new BranchDao();
		return dao.check(branch);
	}
}
