package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.ExpressCompanyService;

/**
 * @author 作者 赵莹:
 * @version 创建时间：2021年6月30日 上午8:18:49
 */
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

	@Autowired
	private ExpressCompanyService expressCompanyMapper;

	@Override
	public List<Map<String, Object>> findAllExpressCompany(Map<String, Object> map) {
		return expressCompanyMapper.findAllExpressCompany(map);
	}

	@Override
	public Map<String, Object> findById(int id) {
		return expressCompanyMapper.findById(id);
	}

	@Override
	public int edit(Map<String, Object> map) {
		return expressCompanyMapper.edit(map);
	}

	@Override
	public int del(int id) {
		return expressCompanyMapper.del(id);
	}

	@Override
	public int insertExpressCompany(Map<String, Object> map) {
		return expressCompanyMapper.insertExpressCompany(map);
	}

}
