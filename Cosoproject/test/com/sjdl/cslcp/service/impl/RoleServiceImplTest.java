package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.RoleService;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年6月30日 下午5:47:34 
*/
public class RoleServiceImplTest extends BaseTest{
	
	@Autowired
	private RoleService roleServiceImpl;
	
	@Test
	public void testFindAllRoleByCidAndType() {
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("cid",1);
			map.put("type",2);
		assertEquals(1,roleServiceImpl.findAllRoleByCidAndType(map).size());
	}
	
	@Test
	public void testFindrole() {
		assertEquals(8,roleServiceImpl.findrole(1).size());
	}
	
	@Test
	public void testUpdatecompanyrole() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account","17309401554");
		map.put("roleId",2);
		assertEquals(1, roleServiceImpl.updatecompanyrole(map));
	}
	
	@Test
	public void testUpdateroleauthority() {
		int id=3;
		int[] authorityId= {4,5};
		assertEquals(1,roleServiceImpl.updateroleauthority(id,authorityId));
	}
	
	@Test
	public void testdelrole() {
		int id=3;
		assertEquals(1, roleServiceImpl.delrole(id));
	}
	
	@Test
	public void addroleauthority() {
		String name="提单管理员";
		String account="13241206574";
		int[] authorityId= {4,5};
		assertEquals(1,roleServiceImpl.addroleauthority(name, account, authorityId));
	}	
}
