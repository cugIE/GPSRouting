package com.test.xin;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.bean.Region;

public class RegionTest {
	Region region = new Region();
	
	
	@Test
	public void testGetAllRegion()  {
		try {
			assertNotEquals(Region.getAllRegion(),new ArrayList<Region>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllRegionString()  {
		try {
			assertNotEquals(Region.getAllRegion("1"),new ArrayList<Region>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOneRegion()  {
		try {
			assertNotEquals(Region.getOneRegion("1"),new Region());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteOneRegion() throws SQLException {
		//assertEquals(0,Region.deleteOneRegion("3"));
		//assertEquals(0,Region.deleteOneRegion("0"));
		assertEquals(0,Region.deleteOneRegion("6"));
		//assertEquals(-1,Region.deleteOneRegion(null));
	}
	

//	@Test
//	public void testAddOneRegion() throws SQLException {
//		region.setName("region3");
//		region.setBranch_id(1);
//		region.setGener_id(1);
//		region.setGps("{122,231}");
//		region.setIntro("这是第N个region");
//		region.setQrcode("/asd/dfd.jpg");
//		region.setType("site");
//		region.setRange(6);
//		assertEquals(1,Region.addOneRegion(region));
//		
//	}

	@Test
	public void testChangeOneRegion() throws SQLException {
		region.setId("5");
		region.setName("region3");
		region.setBranch_id(1);
		region.setGener_id(1);
		region.setGps("{126,231}");
		region.setIntro("这是第N个region");
		region.setQrcode("/asd/dfd.jpg");
		region.setType("site");
		assertEquals(1,Region.changeOneRegion(region));
	}

}
