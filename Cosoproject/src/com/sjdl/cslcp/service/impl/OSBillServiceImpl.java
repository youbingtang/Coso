package com.sjdl.cslcp.service.impl;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.OSBillMapper;
import com.sjdl.cslcp.service.OSBillService;

/**
 * @author 陈鹏
 * @version 创建时间：2021年7月07日 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OSBillServiceImpl implements OSBillService{
	@Autowired
	private OSBillMapper oSBillMapper;
	@Override
	public List<Map<String,Object>> findAllCompany() {
		return oSBillMapper.findAllCompany();
	}
	@Override
	public List<Map<String, Object>> findAllRole() {
		return oSBillMapper.findAllRole();
	}
	@Override
	public List<Map<String, Object>> findAllBill() {
		return oSBillMapper.findAllBill();
	}
	@Override
	public List<Map<String, Object>> findAllBillScAuditStatus() {
		return oSBillMapper.findAllBillScAuditStatus();
	}
	@Override
	public List<Map<String, Object>> findAllBillFreightCharges() {
		return oSBillMapper.findAllBillFreightCharges();
	}
	@Override
	public List<Map<String, Object>> findAllBillConfirmStatus() {
		return oSBillMapper.findAllBillConfirmStatus();
	}
	@Override
	public List<Map<String, Object>> findAllBillSendBillDate() {
		return oSBillMapper.findAllBillSendBillDate();
	}
	@Override
	public List<Map<String, Object>> findAllBillSignatureCompanyId() {
		return oSBillMapper.findAllBillSignatureCompanyId();
	}
	@Override
	public List<Map<String, Object>> findAllBillSendCompanyId() {
		return oSBillMapper.findAllBillSendCompanyId();
	}
	@Override
	public List<Map<String, Object>> findCompanyAndBus() {
		return oSBillMapper.findCompanyAndBus();
	}
	@Override
	public List<Map<String, Object>> findCompanyAndBill() {
		return oSBillMapper.findCompanyAndBill();
	}
	@Override
	public List<Map<String, Object>> findBillCurrent() {
		return oSBillMapper.findBillCurrent();
	}
	@Override
	public List<Map<String, Object>> findBillLastCurrent() {
		return oSBillMapper.findBillLastCurrent();
	}
	@Override
	public List<Map<String, Object>> findBillOne() {
		return oSBillMapper.findBillOne();
	}
	@Override
	public List<Map<String, Object>> findBillTwo() {
		return oSBillMapper.findBillTwo();
	}
	@Override
	public List<Map<String, Object>> findBillThree() {
		return oSBillMapper.findBillThree();
	}
	@Override
	public List<Map<String, Object>> findBillFour() {
		return oSBillMapper.findBillFour();
	}
	@Override
	public List<Map<String, Object>> findBillFive() {
		return oSBillMapper.findBillFive();
	}
	@Override
	public List<Map<String, Object>> findBillSix() {
		return oSBillMapper.findBillSix();
	}

}
