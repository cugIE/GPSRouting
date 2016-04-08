package com.test.xin;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.bean.Sheet;

public class SheetTest {
	Sheet sheet = new Sheet();
	//@Test
	public void testAddOneSheet() {
		sheet.setBranch_id(1);
		sheet.setGener_id(1);
		sheet.setIntro("这是第三个表");
		sheet.setName("表2");
		try {
			assertEquals(1,Sheet.addOneSheet(sheet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	public void testDeleteOneSheet() {
		try {
			assertEquals(1,Sheet.deleteOneSheet("3"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangeOneSheet() {
		sheet.setId("4");
		sheet.setBranch_id(2);
		sheet.setGener_id(2);
		sheet.setIntro("这是第三个表");
		sheet.setName("表3");
		try {
			assertEquals(1,Sheet.changeOneSheet(sheet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllSheet() {
		try {
			assertNotEquals(new ArrayList<Sheet>(), Sheet.getAllSheet("2"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOneSheet() {
		try {
			assertNotEquals(new Sheet(), Sheet.getOneSheet("2"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
