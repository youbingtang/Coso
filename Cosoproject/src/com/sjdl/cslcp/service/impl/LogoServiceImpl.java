package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.LogoMapper;
import com.sjdl.cslcp.service.LogoService;
/**
 * @author 陈鹏
 * @version 创建时间：2021年7月01日 
 */
@Service
@Transactional
public class LogoServiceImpl implements LogoService {
    @Autowired
    private LogoMapper logoMapper;
    
    @Override
	public List<Map<String, Object>> findAllLogo() {
		return logoMapper.findAllLogo();
	}

	@Override
	public int save(Map<String, Object> map) {
		return logoMapper.save(map);
	}

	@Override
	public int edit(int copanyId, String name, String file, int isEnable, int confirmStatus, int id) {
		return logoMapper.edit(copanyId, name, file, isEnable, confirmStatus, id);
	}

	@Override
	public int del(String name) {
		return logoMapper.del(name);
	}

	@Override
	public Map<String, Object> findlogobycid(String copanyId) {
		
		return logoMapper.findlogobycid(copanyId);
	}
}
