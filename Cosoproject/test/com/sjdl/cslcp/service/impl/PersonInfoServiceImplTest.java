//package com.sjdl.cslcp.service.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.sjdl.cslcp.service.PersonInfoService;
//
///** 
//* @author 作者 刘军利: 
//* @version 创建时间：2021年6月30日 上午9:26:04 
//*/
//public class PersonInfoServiceImplTest extends BaseTest{
//	
//	@Autowired
//	private PersonInfoService personInfoServiceImpl;
//	
//	@Test
//	public void checkRepassword() {
//		Assert.assertEquals(1, personInfoServiceImpl.checkRepassword("123", "123"));
//	}
//	
//	@Test
//	public void updatePersonInfo() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("account","222");
//		map.put("password", "8520");
//		map.put("repassword", "8520");
//		map.put("idCardNo", "9875654523");
//		map.put("name","9874521");
//		Assert.assertEquals(1, personInfoServiceImpl.updatePersonInfo(map));
//	}
//	
//	@Test
//	public void findAllAccount() {
//		List<Map<String,Object>> list = personInfoServiceImpl.findAllAccount("1");
//		Assert.assertEquals(1,list.size());
//	}
//	
//	@Test
//	public void updateStatus() {
//		Assert.assertEquals(1,personInfoServiceImpl.updateStatus("2"));
//	}
//	
//	@Test
//	public void addAccount() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("name", "1234");
//		map.put("account", "111");
//		map.put("cid", "1");
//		map.put("idCardNo","12345678");
//		map.put("hiredDate", "2020-02-21");
//		map.put("adminId", "1");
//		map.put("adminName", "1234");
//		Assert.assertEquals(2,personInfoServiceImpl.addAccount(map));
//	}
//
//}
