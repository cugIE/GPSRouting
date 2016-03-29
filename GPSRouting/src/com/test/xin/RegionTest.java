package com.test.xin;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.bean.Region;

public class RegionTest {
	Region region = new Region();
	
	
	@Test
	public void testGetAllRegion() throws SQLException {
		assertNotNull(Region.getAllRegion());
	}

	@Test
	public void testGetAllRegionString() throws SQLException {
		assertNotNull(Region.getAllRegion("1"));
	}

	@Test
	public void testGetOneRegion() throws SQLException {
		assertNotNull(Region.getOneRegion("1"));
	}

	@Test
	public void testDeleteOneRegion() throws SQLException {
		//assertEquals(0,Region.deleteOneRegion("3"));
		//assertEquals(0,Region.deleteOneRegion("0"));
		assertEquals(0,Region.deleteOneRegion("2"));
		//assertEquals(-1,Region.deleteOneRegion(null));
	}
	

	@Test
	public void testAddOneRegion() throws SQLException {
		region.setName("region3");
		region.setBranch_id(1);
		region.setGener_id(1);
		region.setGps("{122,231}");
		region.setIntro("这是第N个region");
		region.setQrcode("/asd/dfd.jpg");
		region.setType("site");
		region.setRange(6);
		assertEquals(1,Region.addOneRegion(region));
		
	}

	@Test
	public void testChangeOneRegion() throws SQLException {
		region.setId("5");
		region.setName("region3");
		region.setBranch_id(1);
		region.setGener_id(1);
		region.setGps("{122,231}");
		region.setIntro("这是第N个region");
		region.setQrcode("/asd/dfd.jpg");
		region.setType("site");
		assertEquals(1,Region.changeOneRegion(region));
	}

}
