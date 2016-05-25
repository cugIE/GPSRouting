package com.service;

import java.sql.SQLException;
import java.util.List;

import sun.launcher.resources.launcher;

import com.bean.AlarmMsg;
import com.bean.Faultmsg;
import com.dao.AlarmMsgDao;
import com.dao.FaultmsgDao;
import com.sun.swing.internal.plaf.metal.resources.metal;

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
	 * 统计未读的故障信息条数
	 * @return
	 * @throws SQLException
	 */
	public int faultcount()throws SQLException {
		FaultmsgDao dao = new FaultmsgDao();
		return dao.faultcount();
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public int Alarmscount()throws SQLException {
		AlarmMsgDao dao = new AlarmMsgDao();
		return dao.Alarmscount();
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
	
	/**
	 * 添加报警信息
	 * @param alarmMsg
	 * @return
	 * @throws SQLException
	 */
	public int addAlarm(AlarmMsg alarmMsg)throws SQLException {
		AlarmMsgDao dao = new AlarmMsgDao();
		return dao.addAlarm(alarmMsg);
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<AlarmMsg> fillAlarm()throws SQLException {
		AlarmMsgDao dao = new AlarmMsgDao();
		return dao.fillAlarm();
	}
}
