package com.sjdl.cslcp.service;

import java.util.Map;

/** 
* @author 作者：关鑫达 
* @version 创建时间：2021年6月29日 上午11:21:41 
*/

public interface StatisticsService {
	/**
	 * 查找平台、公司、提单基本信息
	 * @return 返回所有有关数据集合
	 */
	public Map<String,Object> findStatistics();
	
	/**
	 * 查找本公司提单基本信息
	 * @return 返回所有有关数据集合
	 */
	public Map<String,Object> findCompanyBill(String id);
}
