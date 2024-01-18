package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.ScAuditService;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年7月2日 下午17:01:18 
*/
public class ScAuditServiceImplTest extends BaseTest{
	
	@Autowired
	private ScAuditService scAuditServiceImpl;
	
	@Test
	public void testfindRecordingByblNo() {
		String blNo="B/L-No.COSU2107010001";
		Map<String,Object> m1 =scAuditServiceImpl.findrecordingbyblNo(blNo);
		assertEquals("沈阳鑫迈线缆有限公司",m1.get("shipper").toString());
		}
	
	@Test
	public void testscapply() {
		Map<String,Object> m =new HashMap<String, Object>();
		m.put("account","14771878390");
		m.put("blNo", "B/L-No.COSU2107010001");
		
		assertEquals(1,scAuditServiceImpl.scapply(m));
	}
	
	@Test
	public void testscunapply() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account","14771878390");
		map.put("blNo","B/L-No.COSU2107010001");
		map.put("scfAuditOpinion","aaa");
		assertEquals(1,scAuditServiceImpl.scunapply(map));
	}
	
	@Test
	public void testfindAllStatesIsOne() {
		assertEquals(172,scAuditServiceImpl.findAllStatesIsOne(1).size());
	}
	
	@Test
	public void testsendrecordingtopingtai() {
		assertEquals(1,scAuditServiceImpl.sendrecordingtopingtai("B/L-No.COSU2107010001"));
	}
	
	@Test
	public void testfindAllStatesidThree() {
		assertEquals(51,scAuditServiceImpl.findAllstatesidthree(4).size());
	}
	
	@Test
	public void testselectSendPriceByblNo() {
		assertEquals(2,scAuditServiceImpl.selectSendPriceByblNo("B/L-No.COSU2107010001").size());
	}
	
	@Test
	public void testprintone() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("paystatus",1);
		map.put("blNo","B/L-No.COSU2107010003");
		map.put("account","18321669759");
		assertEquals(0, scAuditServiceImpl.updatetype(map));
		assertEquals(4, scAuditServiceImpl.findrecordingbyblNo("B/L-No.COSU2107010003").get("billStatus"));
	}
}
