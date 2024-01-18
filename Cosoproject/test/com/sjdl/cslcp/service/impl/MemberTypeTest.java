package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.MembertypeService;

public class MemberTypeTest extends BaseTest {
	@Autowired
	private MembertypeService membertypeServiceImpl;

	/*
	 * @Test public void testfindMemberTypeAll() {
	 * assertEquals(2,membertypeServiceImpl.findMemberTypeAll().size()); }
	 */

	
	public void testfindLikeMemberType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "");
		map.put("discount", "");
		assertEquals(6, membertypeServiceImpl.findLikeMemberType(map));
	}

	  public void testfindMemberTypeById() { 
		  assertEquals(1,membertypeServiceImpl.findMemberTypeById(1)); }
	  
	 
	  public void testinsertMemberType() {
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("name", "钻石"); 
		  map.put("discount", 0.70);
		  assertEquals(1, membertypeServiceImpl.insertMemberType(map)); 
		  }
	  @Test
	  public void testdelMemberType() { 
		  assertEquals(1,membertypeServiceImpl.delMemberType(2)); }
	  
	 
	  public void testupdateMemberType() {
		  Map<String, Object> map = new HashMap<String, Object>(); 
		  map.put("id", 18); 
		  map.put("name", "白银");
		  map.put("discount", 0.80); 
		  assertEquals(1,membertypeServiceImpl.editMemberType(map)); }
	 
}
