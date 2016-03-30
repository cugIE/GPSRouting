package com.test.xin;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.Test;

import com.bean.Period;

public class PeriodTest {
	Period prd = new Period();

	@Test
	public void testAddOnePeriod() {
		prd.setSheet_id(1);
		prd.setShift("晚班");
		Time time = Time.valueOf("12:00:0");
		prd.setTime(time);
		prd.setGener_id(1);
		try {
			assertEquals(1,Period.addOnePeriod(prd));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	public void testDeleteOnePeriod() {
//		
//		try {
//			assertEquals(1,Period.deleteOnePeriod("4"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testGetAllPeriod() {
		try {
			assertNotEquals(Period.getAllPeriod("1"), new ArrayList<Period>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
