package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.AddressMapper;
import com.sjdl.cslcp.service.AddressService;

@Service
@Transactional(rollbackFor=Exception.class)
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public Map<String, Object> findById(int id) {
		return addressMapper.findById(id);
	}

	@Override
	public int updateAddress(Map<String, Object> map) {
		return addressMapper.updateAddress(map);
	}

	@Override
	public int delAddress(int id) {
		return addressMapper.delAddress(id);
	}

	@Override
	public int addAddress(Map<String, Object> map) {
		return addressMapper.addAddress(map);
	}

	@Override
	public List<Map<String, Object>> findProvince() {
		return addressMapper.findProvince();
	}

	@Override
	public List<Map<String, Object>> findCity(int pid) {
		return addressMapper.findCity(pid);
	}
}
