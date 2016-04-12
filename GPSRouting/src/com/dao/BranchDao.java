package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Branch;
import com.bean.People;
import com.util.DB;

public class BranchDao {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pre;
	
	
	/**
	 * 查询所有部门信息
	 * @return
	 * @throws SQLException
	 */
	public List<Branch> fill() throws SQLException {
        List<Branch> list = new ArrayList<Branch>();
        String sql = "select * from branch";
        conn = DB.getConn();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        Branch b = null;
        while (rs.next()) {
            b = new Branch();              
            b.setId(rs.getString("branch_id"));
            b.setBranchCode(rs.getString("branch_code"));
            b.setBranchName(rs.getString("branch_name"));
            b.setBranchType(rs.getString("branch_type"));
            b.setComId(rs.getString("com_id"));
            b.setComName(rs.getString("com_name"));
            b.setGenerId(rs.getInt("gener_id"));
            list.add(b);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
	
	/**
	 * 根据Id查找部门信息
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public Branch fill(String Id) throws SQLException{
        
        conn = DB.getConn();
        String sql="select * from branch where branch_id=?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, Id);
        rs=pre.executeQuery();
        Branch b = null;
        if(rs.next()){
        	b = new Branch();              
            b.setId(rs.getString("branch_id"));
            b.setBranchCode(rs.getString("branch_code"));
            b.setBranchName(rs.getString("branch_name"));
            b.setBranchType(rs.getString("branch_type"));
            b.setComId(rs.getString("com_id"));
            b.setComName(rs.getString("com_name"));
            b.setGenerId(rs.getInt("gener_id"));
        }
        rs.close();
        pre.close();
        conn.close();
        return b;
    }
	
	
	/**
	 * 添加部门信息
	 * @param branch
	 * @return
	 * @throws SQLException
	 */
	public int add(Branch branch) throws SQLException {
		 
        String sql = "insert into branch(branch_code,branch_name,branch_type,com_name,com_id) values ('" + branch.getBranchCode() + "','"
                + branch.getBranchName() + "','" + branch.getBranchType() + "','"
                + branch.getComName() + "','" + branch.getComId()
                + "')";
        System.out.println(sql);
        conn = DB.getConn();
        stmt = conn.createStatement();       
        int result = stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
        return result;
    }
	/**
     * 修改
     * @param branch
     * @return
     * @throws SQLException
     */
    public int  update(Branch  branch) throws SQLException {
        String sql="UPDATE branch SET branch_code=?,branch_name=?,branch_type =?,com_id=?,com_name=? WHERE branch_id=?";
        conn=DB.getConn();
        pre = conn.prepareStatement(sql);
        pre.setString(1,  branch.getBranchCode());
        pre.setString(2,  branch.getBranchName());
        pre.setString(3,  branch.getBranchType());
        pre.setString(4,  branch.getComId());
        pre.setString(5,  branch.getComName());
        pre.setString(6,  branch.getId());
        int count=pre.executeUpdate();
        pre.close();
        conn.close();
        return count;      
        // TODO Auto-generated method stub
         
    }
    /**
     * 根据ID删除一项
     * @param Id
     * @throws SQLException
     */
    public void  delete(String Id) throws SQLException {
        String sql="delete from branch where branch_id=?";
        conn=DB.getConn();
        pre = conn.prepareStatement(sql);
        pre.setString(1,Id);
        pre.executeUpdate();
        pre.close();
        conn.close();               
        // TODO Auto-generated method stub      
    }
    /**
     * 多项选择Id删除
     * @param Id
     * @throws SQLException
     */
    public void  delete(String[] Id) throws SQLException {
        conn = DB.getConn();
        String ids="'"+Id[0]+"'";
        for(int i=1;i<Id.length;i++) {
            ids=ids+",'"+Id[i]+"'";
        }
        String sql="delete from branch where  Id  in ("+ids+")";
        pre = conn.prepareStatement(sql);     
        pre.executeUpdate();
        pre.close();
        conn.close();               
        // TODO Auto-generated method stub      
    }
}
