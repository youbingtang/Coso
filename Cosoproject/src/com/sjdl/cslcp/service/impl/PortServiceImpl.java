package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.PortMapper;
import com.sjdl.cslcp.service.PortService;
/**
 * @author 陈鹏
 *@version 创建时间：2021年6月29日 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PortServiceImpl implements PortService{
	@Autowired
	private PortMapper portMapper;

	@Override
	public List<Map<String, Object>> findAllPort() {
		return portMapper.findAllPort();
	}

	@Override
	public int del(int id) {
		return portMapper.del(id);
	}


	@Override
	public int insertPort(Map<String, Object> map) {
		return portMapper.insertPort(map);
	}

	@Override
	public int edit(String name, String ename,  int sendPrice, int id) {
		return portMapper.edit(name, ename,  sendPrice, id);
	}
	@Override
	public List<Map<String, Object>> findCountry() {
		return portMapper.findCountry();
	}

	@Override
	public List<Map<String, Object>> findProvince(int pid2) {
		return portMapper.findProvince(pid2);
	}

	@Override
	public List<Map<String, Object>> findCity(int pid3) {
		return portMapper.findCity(pid3);
	}

}
