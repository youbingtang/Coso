package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.SendService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年7月1日 上午10:53:05 
*/

public class SendServiceImplTest extends BaseTest{
	
	@Autowired
	private SendService sendServiceImpl;
	
//	@Test
//	public void findSendAndSignCompany() {
//		Map<String,Object> map = sendServiceImpl.findSendAndSignCompany();
//		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("signCompanyList");
//		Assert.assertEquals(10,list.size());
//	}
//	
//	@Test
//	public void insertLading() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("blNo", "2");
//		map.put("sendCompanyId", "1");
//		map.put("receiveCompanyId", "1");
//		map.put("signatureCompanyId", "1");
//		map.put("proId", "1");
//		map.put("shipper", "1");
//		map.put("consignee", "1");
//		map.put("notifyParty", "1");
//		map.put("placeOfreceipt", "1");
//		map.put("vessel", "1");
//		map.put("portOfLoading", "1");
//		map.put("portOfTransshipment", "1");
//		map.put("PortOfDischarge", "1");
//		map.put("PlaceOfDelivery", "1");
//		map.put("freightCharges", "1");
//		map.put("NoOfOriginal", "1");
//		map.put("declared", "1");
//		map.put("voyageNo", "1");
//		map.put("preCarriageBy", "1");
//		map.put("planceAndDate", "1");
//		map.put("totalNumber", "1");
//		map.put("finalDestination", "1");
//		map.put("fordelivery", "1");
//		map.put("typeOfMovement", "1");
//		map.put("rcfAuditUserAccount", "1");
//		map.put("rcfAuditUserName", "1");
//		Assert.assertEquals(2,sendServiceImpl.insertLading(map));
//	}
//	
//	@Test
//	public void findStatusFive() {
//		List<Map<String,Object>> list = sendServiceImpl.findStatusFive("1");
//		Assert.assertEquals(1,list.size());
//	}
//	
//	@Test
//	public void applyForMailing() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("collectPerson", "1");
//		map.put("collectAddress", "1");
//		map.put("collectPhone", "1");
//		map.put("blId", "1");
//		map.put("blNo", "1");
//		Assert.assertEquals(1,sendServiceImpl.applyForMailing(map));
//	}
	
	@Test
	public void applyForMailing() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("collectPerson", "1");
		map.put("collectPhone", "1");
		map.put("collectAddress", "1");
		map.put("blId","3");
		map.put("blNo", "B/L-No.COSU2107010001");
		Assert.assertEquals(1,sendServiceImpl.applyForMailing(map));
	}
	

}
