package com.test.xin;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.bean.Period;
import com.bean.PtrConnection;
import com.bean.Region;

public class PtrConnectionTest {
	PtrConnection pc = new PtrConnection();

//	public void testAddOneConnection() {
//		pc.setPeriod_id("2");
//		pc.setRegion_id("4");
//		pc.setGener_id(1);
//		pc.setSort(0);
//		
//		try {
//			assertEquals(1, PtrConnection.addOneConnection(pc));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testDeleteOneConnection() {
//		try {
//			assertEquals(1, PtrConnection.deleteOneConnection("4"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void getAllRegion(){
		try {
			assertNotEquals(PtrConnection.getAllRegion("1"),new ArrayList<PtrConnection>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getAllPeriod(){
		try {
			assertNotEquals(PtrConnection.getAllPeriod("2"),new ArrayList<Period>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
