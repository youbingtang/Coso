package com.sjdl.cslcp.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.StatisticsMapper;
import com.sjdl.cslcp.service.StatisticsService;

/** 
* @author 作者：关鑫达 
* @version 创建时间：2021年6月29日 上午11:25:41 
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Override
	public Map<String,Object> findStatistics() {
		return statisticsMapper.findStatistics();
	}

	@Override
	public Map<String, Object> findCompanyBill(String id) {
		return statisticsMapper.findCompanyBill(id);
	}
}
