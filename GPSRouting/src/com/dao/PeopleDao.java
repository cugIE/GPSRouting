package com.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.bean.People;
import com.util.DB;

public class PeopleDao {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pre;

	/**
	 * 查询全部人员信息
	 * @return
	 * @throws SQLException
	 */
	public List<People> fill() throws SQLException {
		List<People> list = new ArrayList<People>();
		String sql = "select * from T_building";
		conn = DB.getConn();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		People p = null;
		while (rs.next()) {
			p = new People();
			p.setId(rs.getInt("people_id"));
			p.setUsername(rs.getString("people_username"));
			p.setName(rs.getString("people_name"));
			p.setPassword(rs.getString("people_password"));
			p.setBranchId(rs.getInt("branch_id"));
			list.add(p);
		}
		rs.close();
		stmt.close();
		conn.close();
		return list;
	}

	/**
	 * 添加人员信息
	 * 
	 * @param people
	 * @return
	 * @throws SQLException
	 */
	public int add(People people) throws SQLException {
		String sql = "insert into people(people_username,people_name,people_password) values ('"
				+ people.getUsername()
				+ "','"
				+ people.getName()
				+ "','"
				+ people.getPassword() + "')";
		conn = DB.getConn();
		stmt = DB.getStatement(conn);
		int result = stmt.executeUpdate(sql);
		// result值为1则添加成功！
		stmt.close();
		conn.close();
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public People fill(String Id) throws SQLException{
        conn = DB.getConn();
        String sql="select * from people where people_id = '"+Id+"'";
        pre = conn.prepareStatement(sql);
        pre.setString(1, Id);
        rs=pre.executeQuery();
        People p = null;
        if(rs.next()){
        	p = new People();
			p.setId(rs.getInt("people_id"));
			p.setUsername(rs.getString("people_username"));
			p.setName(rs.getString("people_name"));
			p.setPassword(rs.getString("people_password"));
			p.setBranchId(rs.getInt("branch_id"));
        }
        rs.close();
        pre.close();
        conn.close();
        return p;
    }

	/**
	 * 检查用户名和密码
	 * 
	 * @param people
	 * @return
	 * @throws SQLException
	 */
	public boolean check(People people) throws SQLException {
		boolean i = false;
		People p = null;
		Connection conn = DB.getConn();
		String userName = people.getUsername();
		String passWord = people.getPassword();
		String sql = "select * from people where people_username = '"
				+ userName + "'";
		Statement stmt = DB.getStatement(conn);
		// 根据sql语句获取查询结果
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			if (!rs.next()) {
				throw new Exception("用户不存在：" + userName);
			} else {
				if (!passWord.equals(rs.getString("people_password"))) {
					throw new Exception("密码不正确！");
				}
				i = true;
				people.setId(rs.getInt("people_id"));
				people.setName(rs.getString("people_name"));
				people.setUsername(rs.getString("people_username"));
				people.setPassword(rs.getString("people_password"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return i;
	}

	/**
	 * 根据Id删除
	 * 
	 * @param Id
	 * @throws SQLException
	 */
	public void delete(String Id) throws SQLException {
		String sql = "delete from people where people_id= '" + Id + "'";
		conn = DB.getConn();
		pre = conn.prepareStatement(sql);
		pre.setString(1, Id);
		pre.executeUpdate();
		pre.close();
		conn.close();
	}

	/**
	 * 多项选择id删除
	 * 
	 * @param Id
	 * @throws SQLException
	 */
	public void delete(String[] Id) throws SQLException {
		conn = DB.getConn();
		String ids = "'" + Id[0] + "'";
		for (int i = 1; i < Id.length; i++) {
			ids = ids + ",'" + Id[i] + "'";
		}
		String sql = "delete from people where people_id in (" + ids + ")";
		pre = conn.prepareStatement(sql);
		pre.executeUpdate();
		pre.close();
		conn.close();
	}

}