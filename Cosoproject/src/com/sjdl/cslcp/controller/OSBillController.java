package com.sjdl.cslcp.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjdl.cslcp.service.OSBillService;

/**
 * @author 陈鹏
 * @version 创建时间：2021年7月07日 
 */
@RestController
public class OSBillController {
	@Autowired
	private OSBillService oSBillServiceImpl;
	
	/**
	 * 查询中远海平台数据展示
	 * @return 将查询的所有信息封装成一个map
	 */	
	@RequestMapping( "/osbillCompany" ) 
	public Map<String, Object> osbcompany(
				) {
		
	Map<String,Object> mapBig = new HashMap<String,Object>();
	List<Map<String, Object>> list1 = oSBillServiceImpl.findAllCompany();
	List<Map<String, Object>> list2 = oSBillServiceImpl.findAllRole();
	List<Map<String, Object>> list3 = oSBillServiceImpl.findAllBill();
	List<Map<String, Object>> list4 = oSBillServiceImpl.findAllBillScAuditStatus();
	List<Map<String, Object>> list5 = oSBillServiceImpl.findAllBillFreightCharges();
	List<Map<String, Object>> list6 = oSBillServiceImpl.findAllBillSignatureCompanyId();
	List<Map<String, Object>> list7 = oSBillServiceImpl.findAllBillConfirmStatus();
	List<Map<String, Object>> list8 = oSBillServiceImpl.findAllBillSendCompanyId();
	List<Map<String, Object>> list9 = oSBillServiceImpl.findAllBillSendBillDate();
	List<Map<String, Object>> list10 = oSBillServiceImpl.findCompanyAndBus();
	List<Map<String, Object>> list11 = oSBillServiceImpl.findCompanyAndBill();
	List<Map<String, Object>> list12 = oSBillServiceImpl.findBillCurrent();
	List<Map<String, Object>> list13 = oSBillServiceImpl.findBillLastCurrent();
	List<Map<String, Object>> result2 = oSBillServiceImpl.findBillOne();
	List<Map<String, Object>> result3 = oSBillServiceImpl.findBillTwo();
	List<Map<String, Object>> result4 = oSBillServiceImpl.findBillThree();
	List<Map<String, Object>> result5 = oSBillServiceImpl.findBillFour();
	List<Map<String, Object>> result6 = oSBillServiceImpl.findBillFive();
	List<Map<String, Object>> result7 = oSBillServiceImpl.findBillSix();
	double result1=list12.size()/list13.size()*100;
	mapBig.put("list1", list1.size());
	mapBig.put("list2", list2.size());
	mapBig.put("list3", list3.size());
	mapBig.put("list4", list4.size());
	mapBig.put("list5", list5.size());
	mapBig.put("list6", list6.size());
	mapBig.put("list7", list7.size());
	mapBig.put("list8", list8.size());
	mapBig.put("list9", list9.size());
	mapBig.put("list10", list10);
	mapBig.put("list11", list11);
	mapBig.put("result1", result1+"%");
	mapBig.put("result2", result2.size());
	mapBig.put("result3", result3.size());
	mapBig.put("result4", result4.size());
	mapBig.put("result5", result5.size());
	mapBig.put("result6", result6.size());
	mapBig.put("result7", result7.size());
	return mapBig;
				}
}
