package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.CusLevelMapper;
import com.sjdl.cslcp.service.CusLevelService;

/**
 * @author 作者：张泽华
 * @version 创建时间：2021年6月29日 上午8:33:45
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CusLevelServiceImpl implements CusLevelService{

	@Autowired
	private CusLevelMapper cusLevelMapper;

	@Override
	public List<Map<String, Object>> findAllCusLevel(Map<String, Object> map) {
		return cusLevelMapper.findAllCusLevel(map);
	}

	@Override
	public Map<String, Object> findById(int id) {
		return cusLevelMapper.findById(id);
	}

	@Override
	public int updateCusLevel(Map<String, Object> map) {
		return cusLevelMapper.updateCusLevel(map);
	}

	@Override
	public int delCusLevel(int id) {
		return cusLevelMapper.delCusLevel(id);
	}

	@Override
	public int addCusLevel(Map<String, Object> map) {
		return cusLevelMapper.addCusLevel(map);
	}
}
