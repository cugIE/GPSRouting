package com.service;

import java.sql.SQLException;
import java.util.List;

import com.bean.Faultmsg;
import com.dao.FaultmsgDao;

public class MsgService {
	/**
	 * 查询所有故障信息
	 * @return
	 * @throws SQLException
	 */
	public  List<Faultmsg>  fill() throws SQLException{
        FaultmsgDao dao=new FaultmsgDao();   
        return dao.fill();
    }
	
	/**
	 * 查询指定故障信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Faultmsg fill(String id)throws SQLException {
		FaultmsgDao dao = new FaultmsgDao();
		return dao.fill(id);
	}
	
	/**
	 * 更新故障信息状态
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updatestatus(String id)throws SQLException {
		FaultmsgDao dao = new FaultmsgDao();
		return dao.updatestatus(id);
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public int faultcount()throws SQLException {
		FaultmsgDao dao = new FaultmsgDao();
		return dao.faultcount();
	}
	
	/**
	 * 添加故障信息
	 * @param faultmsg
	 * @return
	 * @throws SQLException
	 */
	public int add(Faultmsg faultmsg) throws SQLException{
		FaultmsgDao dao = new FaultmsgDao();
		return dao.add(faultmsg);
	}
}
