package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.BillgoodsService;

/** 
* @author 作者 褚国庚: 
* @version 创建时间：2021年6月30日 上午8:16:01 
*/
public class BillgoodsServiceImplTest extends BaseTest{

	@Autowired
	private BillgoodsService billgoodsServiceImpl;

	@Test
	public void testfindBillgoodsByblid() {
		assertEquals(7,billgoodsServiceImpl.findBillgoodsByblid(1).size());
	}
		
	@Test
	public void testdeleteBillgoods() {
		int blId=33;
		assertEquals(1,billgoodsServiceImpl.deleteBillgoods(blId));
	}
		
	@Test
	public void testupdateBillgoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blId","156");
		map.put("MarksAndNos","集给箱");
		map.put("NoAndKindOfPackages","集装箱");
		map.put("DescriptionOfgoods","集装箱");
		map.put("GrossWeight","28954");
		map.put("Measurement","6584");
		assertEquals(1,billgoodsServiceImpl.updateBillgoods(map));
	}

	@Test
	public void testinsertBillgoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blId","2354");
		map.put("MarksAndNos","集H和");
		map.put("NoAndKindOfPackages","集装箱");
		map.put("DescriptionOfgoods","集装箱");
		map.put("GrossWeight","2654");
		map.put("Measurement","6584");
		assertEquals(1,billgoodsServiceImpl.insertBillgoods(map));
	}
	
	@Test
	public void testfindBillgoods() {
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("blId","5");
		assertEquals(7,billgoodsServiceImpl.findBillgoods(map));
	}
}
