package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.sjdl.cslcp.service.CusLevelService;

public class CusLevelServiceImplTest extends BaseTest{
	@Autowired
	private CusLevelService cusLevelServiceImpl;
	
	@Test
	public void testfindAllCusLevel() {
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("name", "");
		map.put("low", "");
		map.put("hi", "");
		map.put("createDate", ""); 
		map.put("createUserId", "");
		map.put("createUserName", ""); 
		map.put("modifyDate","");
		map.put("modifyUserId", ""); 
		map.put("modifyUserName",""); 
		assertEquals(9,cusLevelServiceImpl.findAllCusLevel(map).size());
	}
	
	@Test
	public void testFindById(){
		assertEquals(9,cusLevelServiceImpl.findById(1).size());
	}

   @Test
	public void testUpdateCusLevel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("name", "Q");
		map.put("low", 999);
		map.put("hi", 109);
		map.put("createDate", "2021-06-10"); 
		map.put("createUserId", 889);
		map.put("createUserName", "三一"); 
		map.put("modifyDate","2021-06-18");
		map.put("modifyUserId", 59); 
		map.put("modifyUserName","王二"); 
		
		assertEquals(1,cusLevelServiceImpl.updateCusLevel(map));
	}
	
	@Test
	public void testDelCusLevel() {
		assertEquals(1,cusLevelServiceImpl.delCusLevel(3));
	}
	
	@Test
	public void testAddCusLevel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 9);
		map.put("name", "D");
		
		assertEquals(1,cusLevelServiceImpl.addCusLevel(map));
	}
}
