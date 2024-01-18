package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.AddressService;

public class AddressServiceImplTest extends BaseTest{
	
	@Autowired
	private AddressService addressServiceImpl;
	
	@Test
	public void testFindProvince() {
		assertEquals(4,addressServiceImpl.findProvince().size());
		}
	
	@Test
	public void testFindCity() {
		assertEquals(3,addressServiceImpl.findCity(5).size());
	}
	
	@Test
	public void testFindById() {
		assertEquals(2,addressServiceImpl.findById(1).size());
	}
	
	@Test
	public void testUpdateAddress() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 17);
		map.put("pid", 1);
		map.put("name", "通话市");
		
		assertEquals(1,addressServiceImpl.updateAddress(map));
	}
	
	@Test
	public void testDelAddress() {
		assertEquals(1,addressServiceImpl.delAddress(22));
	}
	
	@Test
	public void testAddAddress() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", 1);
		map.put("name", "通话");
		
		assertEquals(1,addressServiceImpl.addAddress(map));
	}
}
