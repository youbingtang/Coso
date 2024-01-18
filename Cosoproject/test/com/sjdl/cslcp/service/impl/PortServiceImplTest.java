package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.PortService;




public class PortServiceImplTest extends BaseTest{
	
	@Autowired
	private PortService portServiceImpl;
	
	@Test
	public void testFindProt() {
		List<Map<String,Object>> list = portServiceImpl.findAllPort();
		assertEquals(12,portServiceImpl.findAllPort().size());
		}
	
	@Test
	public void testUpdatePort() {
		assertEquals(1,portServiceImpl.edit("a", "a", 1,  2));
	}
	
	@Test
	public void testDelAddress() {
		assertEquals(1,portServiceImpl.del(8));
	}
	
	@Test
	public void testAddAddress() {
		Map<String,Object> m =new HashMap<String, Object>();
		m.put("createDate", "2021-06-06");
		m.put("sendPrice", 10);
		m.put("name", "k");
		m.put("ename", "k");
		m.put("countryId", 10);
		m.put("provinceId", 10);
		m.put("cityId", 10);
		m.put("longitude", 10.5);
		m.put("latitude", 10.5);
		
		assertEquals(1,portServiceImpl.insertPort(m));
	}
	
}
