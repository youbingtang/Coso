package com.sjdl.cslcp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.CompanyTypeService;

/**
 * @author 作者 陶富春
 * @version 创建时间：2021年7月5日 上午8:45：50
 */
public class CompanyTypeServiceImplTest  extends BaseTest{
	
	@Autowired
	private CompanyTypeService companyTypeServiceImpl;
	
//	@Test
	public void testSearchCompanyType() {
		String name="集体制型公司";
		assertEquals(0,companyTypeServiceImpl.finAllCompanyList(name).size());
		}
	
//	@Test
	public void testDelCompanyType() {
		assertEquals(1,companyTypeServiceImpl.delCompany(20));
	}
	
//	@Test
	public void testUpdateCompanyType() {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("id", "22");
		map.put("name", "集体制型公司");
		map.put("modifyUserId", "1");
		map.put("modifyUserName", "张三");
		assertEquals(1,companyTypeServiceImpl.updateCompany(map));
	}

//	@Test
	public void testInsertCompanyType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "娱乐型号公司");
		map.put("createUserId", "2");
		map.put("createUserName", "管理员");
		assertEquals(1,companyTypeServiceImpl.insertCompany(map));
	}
}
