package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.CompanyTypeMapper;
import com.sjdl.cslcp.service.CompanyTypeService;

/**
 * @author 作者 陶富春
 * @version 创建时间：2021年7月5日 上午8:45：50
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyTypeServiceImpl implements CompanyTypeService {

	@Autowired
	private CompanyTypeMapper companyTypeMapper;

	// 根据name查询公司类型信息
	@Override
	public List<Map<String, Object>> finAllCompanyList(String name) {
		return companyTypeMapper.finAllCompanyList(name);
	}

	// 插入公司类型
	@Override
	public int insertCompany(Map<String, Object> map) {
		return companyTypeMapper.insertCompany(map);
	}

	// 删除公司类型
	@Override
	public int delCompany(int id) {
		return companyTypeMapper.delCompany(id);
	}

	// 修改公司类型
	@Override
	public int updateCompany(Map<String, Object> map) {
		return companyTypeMapper.updateCompany(map);
	}

}
