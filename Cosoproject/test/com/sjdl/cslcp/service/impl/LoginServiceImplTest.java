package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.LoginService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月30日 上午8:16:01 
*/
public class LoginServiceImplTest extends BaseTest{
	
	@Autowired
	private LoginService loginServiceImpl;
	
	@Test
	public void testCheckPassword() {
		Assert.assertEquals(0, loginServiceImpl.checkPassword("123", "123"));
		Assert.assertEquals(1, loginServiceImpl.checkPassword("1000", "8520"));
		Assert.assertEquals(2, loginServiceImpl.checkPassword("222", "222"));
		Assert.assertEquals(3, loginServiceImpl.checkPassword("111", "111"));
		Assert.assertEquals(4, loginServiceImpl.checkPassword("1000", "111"));
	}
	
	@Test
	public void findAuthority() {
		List<Map<String,Object>> list = loginServiceImpl.findAuthority("1000");
		Assert.assertEquals(1,list.size());
	}
	
	@Test
	public void findAllAuthority() {
		List<Map<String,Object>> list = loginServiceImpl.findAllAuthority("1000");
		Assert.assertEquals(2,list.size());
	}
	
	@Test
	public void findPersonInfo() {
		Map<String,Object> map = loginServiceImpl.findPersonInfo("1000");
		Assert.assertEquals("111",map.get("password"));
	}
}
