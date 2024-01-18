package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.ExpressCompanyService;

/**
 * @author 赵莹:
 * @version 时间：2021年7月5日 08点53分
 */
public class ExpressCompanyServiceImplTest {
	@Autowired
	private ExpressCompanyService expressCompanyMapperImpl;
//	@Test
//	public void testFindEmpress_company(Map<String, Object> map) {
//		assertEquals(2,expressCompanyMapperImpl.findAllExpressCompany(map).size());
//		}
	
//	@Test
//	public void testFindById() {
//		assertEquals(1,expressCompanyMapperImpl.findById(1).size());
//	}
	
//	@Test
//	public void testEdit() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id",1);
//		map.put("name", "zhangsan");
//		map.put("createDate","2021-5-18" );
//		map.put("createUserId", 1);
//		map.put("createUserName", "zhangsan");
//		map.put("modifyDate", "2021-6-20");
//		map.put("modifyUserId", 1);
//		map.put("modifyUserName","zhangzhang" );
//		map.put("isDel",1 );
//		map.put("version", 1);
//		assertEquals(1,expressCompanyMapperImpl.edit(map));
//		int id=(int) map.get("id");
//		Map<String,Object> expresscompanymap=expressCompanyMapperImpl.findById(id);
//		assertEquals(map.get("name"),expresscompanymap.get("name"));
	// }

//	@Test
//	public void testDel() {
//		Map<String, Object> map = new HashMap<String, Object>();
	
//		map.put("id", 1);
//		map.put("name", "zhangsan");
//		map.put("createDate","2021-5-18" );
//		map.put("createUserId", 1);
//		map.put("createUserName", "zhangsan");
//		map.put("modifyDate", "2021-6-20");
//		map.put("modifyUserId", 1);
//		map.put("modifyUserName","zhangzhang" );
//		map.put("isDel",1 );
//		map.put("version", 1);
	
//		assertEquals(1,expressCompanyMapperImpl.del(1));
//}

}
