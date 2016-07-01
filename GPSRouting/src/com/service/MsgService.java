package com.service;

import java.sql.SQLException;
import java.util.List;

import sun.launcher.resources.launcher;

import com.bean.AlarmMsg;
import com.bean.Announcement;
import com.bean.Faultmsg;
import com.dao.AlarmMsgDao;
import com.dao.AnnounceDao;
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
	 * 根据上报人id查找故障信息
	 * @param generid
	 * @return
	 * @throws SQLException
	 */
	public  List<Faultmsg>  fillgenid(String generid) throws SQLException{
        FaultmsgDao dao=new FaultmsgDao();   
        return dao.fillgenId(generid);
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
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateAlarm(String id)throws SQLException {
		AlarmMsgDao dao = new AlarmMsgDao();
		return dao.updateAlarm(id);
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
	
	public int handlefault(String fault_id,String duty_man)throws SQLException {
		FaultmsgDao dao = new FaultmsgDao();
		return dao.handleFault(fault_id, duty_man);
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
	
	public List<AlarmMsg> fillpeoAlarm(String generid)throws SQLException {
		AlarmMsgDao dao = new AlarmMsgDao();
		return dao.fillpeoAlarm(generid);
	}
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public AlarmMsg fillAlarm(String id)throws SQLException {
		AlarmMsgDao dao = new AlarmMsgDao();
		return dao.fillAlarm(id);
	}
	
	public List<Announcement> fillAnnounce()throws SQLException {
		AnnounceDao dao = new AnnounceDao();
		return dao.fill();
	}
	
	public List<Announcement> fillgenidAnnounce(String generid)throws SQLException {
		AnnounceDao dao = new AnnounceDao();
		return dao.fillgenId(generid);
	}
	
	public Announcement fillAnnounce(String Id)throws SQLException {
		AnnounceDao dao = new AnnounceDao();
		return dao.fill(Id);
	}
}
