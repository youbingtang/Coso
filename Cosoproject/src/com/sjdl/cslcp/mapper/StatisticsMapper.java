package com.sjdl.cslcp.mapper;

import java.util.Map;

/**
 * @author 作者：关鑫达
 * @version 创建时间：2021年6月29日 上午11:30:39
 */

public interface StatisticsMapper {
	
	//查询操作
	public Map<String,Object>findStatistics();
	
	//查询操作
	public Map<String,Object>findCompanyBill(String id);
}
