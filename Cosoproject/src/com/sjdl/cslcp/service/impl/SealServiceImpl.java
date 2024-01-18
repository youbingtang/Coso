package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.SealMapper;
import com.sjdl.cslcp.service.SealService;
/**
 * @author 陈鹏
 * @version 创建时间：2021年7月01日 
 */
@Service
@Transactional
public class SealServiceImpl implements SealService {
    @Autowired
    private SealMapper sealMapper;

	@Override
	public int save(Map<String, Object> map) {
		return sealMapper.save(map);
	}

	@Override
	public List<Map<String, Object>> findAllSeal(Map<String, Object> map) {
		return sealMapper.findAllSeal(map);
	}
    
}
