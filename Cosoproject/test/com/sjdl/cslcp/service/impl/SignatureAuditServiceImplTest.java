package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.SignatureAuditService;

/** 
* @author 作者 曲浩焱: 
* @version 创建时间：2021年7月2日 下午18:14:34 
*/
public class SignatureAuditServiceImplTest extends BaseTest{
	
	@Autowired
	private SignatureAuditService signatureAuditServiceImpl;
	
	@Test
	public void testfindRecordingByblNoo() {
		String blNo="B/L-No.COSU2107010001";
		Map<String,Object> map =signatureAuditServiceImpl.findrecordingbyblNo(blNo);
		assertEquals("沈阳鑫迈线缆有限公司",map.get("shipper").toString());
		}
	
	@Test
	public void testsiapply() {
		Map<String,Object> map1 =new HashMap<String, Object>();
		map1.put("account","14771878390");
		map1.put("blNo", "B/L-No.COSU2107010001");	
		assertEquals(1,signatureAuditServiceImpl.siapply(map1));
	}
	
	@Test
	public void testsiunapply() {
		Map<String,Object> map2=new HashMap<String, Object>();
		map2.put("account","14771878390");
		map2.put("blNo","B/L-No.COSU2107010001");
		map2.put("signatureAuditOpinion","审核不通过");
		assertEquals(1,signatureAuditServiceImpl.siunapply(map2));
	}
}
