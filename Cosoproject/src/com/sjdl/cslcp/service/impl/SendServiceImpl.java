package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.SendMapper;
import com.sjdl.cslcp.service.SendService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月30日 下午4:24:49 
*/

@Service
@Transactional(rollbackFor = Exception.class)
public class SendServiceImpl implements SendService{
	
	@Autowired
	private SendMapper sendMapper;

	@Override
	public Map<String, Object> findSendAndSignCompany() {
		List<Map<String,Object>> sendCompanyList = sendMapper.findReceiveCompany();
		List<Map<String,Object>> signCompanyList = sendMapper.findSignCompany();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sendCompanyList", sendCompanyList);
		map.put("signCompanyList", signCompanyList);
		return map;
	}

	@Override
	public int insertLading(Map<String, Object> map) {
		int result = -1;
		int flag = sendMapper.findBlNo(map);
		
		if(flag == 0) {
			sendMapper.insertLading(map);
			result = sendMapper.insertBillGoods(map);
		}else {
			result = 2;
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findStatusFive(String receiveCompanyId) {
		return sendMapper.findStatusFive(receiveCompanyId);
	}

	@Override
	public int applyForMailing(Map<String, Object> map) {
		int result = -1;
			String blId =  map.get("blId").toString();
			
			System.out.println("**********************************************");
			System.out.println(blId);
			System.out.println("**********************************************");
			Map<String,Object> map1 = sendMapper.findApply(blId);
			System.out.println(map1);
			String receiverName = map1.get("receiverName").toString();
			String receiverPhone = map1.get("receiverPhone").toString();
			String receiverAddress =  map1.get("receiverAddress").toString();
			map.put("receiverName", receiverName);
			map.put("receiverPhone", receiverPhone);
			map.put("receiverAddress", receiverAddress);
			sendMapper.updateStatusSix(map);
			sendMapper.insertExpress(map);
			result = sendMapper.updateBillFile(map);

		return result;
	}
	
	@Override
	public List<Map<String,Object>> findallrecording(String receiveCompanyId) {
		return sendMapper.findallrecording(receiveCompanyId);
	}

	@Override
	public Map<String, Object> findrecordingbyblNo(String blNo) {
		return sendMapper.findrecordingbyblNo(blNo);
	}

	@Override
	public int rcapply(String blNo) {
		return sendMapper.rcapply(blNo);
	}

	@Override
	public int rcunapply(String blNo) {
		return sendMapper.rcunapply(blNo);
	}
	
	@Override
	public int rcmodify(Map<String, Object> map) {
		return sendMapper.rcmodify(map);
	}

	@Override
	public List<Map<String, Object>> findtidanbymallingid(String receiveCompanyId) {
		return sendMapper.findtidanbymallingid(receiveCompanyId);
	}
	
	public int ok(String blNo){
		return sendMapper.ok(blNo);
	}
}
