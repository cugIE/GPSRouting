package com.test.xin;

import static org.junit.Assert.*;

import java.awt.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;

import com.bean.Record;

public class RecordTest {

	Record rcd = new Record();
	@Test
	public void testGetAllRecord() {
//		try {
//	//		assertNotEquals(new ArrayList<Record>(), Record.getAllRecord("2"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	//@Test
	public void testGetSingleRecord(){
		try {
			assertNotEquals(Record.getOneRecord("1"),new Record());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//@Test
//	public void testDelete(){
//		try {
//			assertEquals(1,Record.deleteOneRecord("3"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	//@Test
//	public void testAdd(){
//		rcd.setGps("gps");
//		rcd.setAsws("asd");
//		rcd.setError("1");
//		rcd.setPicture("231");
//		rcd.setStart(new Timestamp(System.currentTimeMillis()-200));
//		rcd.setEnd(new Timestamp(System.currentTimeMillis()));
//		rcd.setSubmit(new Timestamp(System.currentTimeMillis()));
//		rcd.setPtr_id(3);
//		rcd.setGener_id(2);
//		rcd.setNote("this is a note");
//		try {
//			assertEquals(6,Record.addOneRecord(rcd));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	//@Test
	public void testGetChange(){
		rcd.setAsws("asd");
		rcd.setError("1");
		rcd.setPicture("231");
		rcd.setNote("this is a note");
		rcd.setId("4");
		try {
			assertEquals(1,Record.changeOneRecord(rcd));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	@Test
	public void testCheck(){
		try {
			assertEquals(1,Record.checkOneRecord("李欣","5", null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
