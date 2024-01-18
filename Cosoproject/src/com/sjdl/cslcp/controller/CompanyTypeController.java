package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.CompanyTypeService;

/**
 * @author 作者 陶富春:
 * @version 创建时间：2021年7月5日 上午8：45：44
 */
@RestController
public class CompanyTypeController {
	@Autowired
	private CompanyTypeService companyTypeServiceImpl;

	/**
	 * 根据name查询公司类型
	 * @param currentPage 显示的页数
	 * @param name        公司类型名
	 * @return 返回公司类型信息
	 */
	@RequestMapping("/companyTypeListPage")
	public PageInfo<Map<String, Object>> companyListPage(
			@RequestParam(defaultValue = "1", value = "currentPage") int currentPage,
			@RequestParam(defaultValue = "", value = "name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = companyTypeServiceImpl.finAllCompanyList(name);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}

	/**
	 * 修改公司类型
	 * @param id             公司类型id
	 * @param name           公司类型名
	 * @param modifyUserId   修改人id
	 * @param modifyUserName 修改人姓名
	 * @param isDel          删除标记，0表示此公司类型未删除，1表示此公司类型已删除
	 * @return 返回公司类型修改后的信息
	 */
	@RequestMapping("/companyTypedoEdit")
	public int updateCompany(String id, String name, int modifyUserId, String modifyUserName, int isDel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("modifyUserId", modifyUserId);
		map.put("modifyUserName", modifyUserName);
		map.put("isDel", isDel);
		companyTypeServiceImpl.updateCompany(map);
		int result = 0;
		return result;
	}

	/**
	 * 添加公司类型
	 * @param id             公司类型id
	 * @param name           公司类型名
	 * @param createDate     创建时间
	 * @param createUserId   创建人id
	 * @param createUserName 创建人姓名
	 * @return 返回公司类型数据信息
	 */
	@RequestMapping("/companyTypeAdd")
	public int companyTypedoAdd(String id, String name, Data createDate, int createUserId, String createUserName) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("name", name);
		m.put("createUserId", createUserId);
		m.put("createUserName", createUserName);
		companyTypeServiceImpl.insertCompany(m);
		int result = 0;
		return result;
	}

	/**
	 * 删除公司类型
	 * @param id 公司类型id
	 * @return 返回公司类型信息数据，isDel为删除标记，返回isDel为1表示已删除 0表示此公司类型未删除，1表示此公司类型已删除
	 */
	@RequestMapping("/companyTypedoDel")
	public int companydoDel(int id) {
		int result = companyTypeServiceImpl.delCompany(id);
		return result;
	}

}
