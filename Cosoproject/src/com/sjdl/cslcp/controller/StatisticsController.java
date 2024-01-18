package com.sjdl.cslcp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjdl.cslcp.service.StatisticsService;



/** 
* @author 作者：关鑫达 
* @version 创建时间：2021年6月29日 上午11:30:41 
*/
@RestController
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsServiceImpl;
	
	/**
	 * 查找平台、公司、提单基本信息
	 * @return 返回所有有关数据集合
	 */
	@RequestMapping("/statistics")
	public Map<String,Object> findStatistics(){
		Map<String,Object> map = statisticsServiceImpl.findStatistics();
		return map;
	}
	
	/**
	 * 查找平台、公司、提单基本信息
	 * @return 返回所有有关数据集合
	 */
	@RequestMapping("/findCompanyBill")
	public Map<String,Object> findCompanyBill(String id){
		Map<String,Object> map = statisticsServiceImpl.findCompanyBill(id);
		return map;
	}
}
