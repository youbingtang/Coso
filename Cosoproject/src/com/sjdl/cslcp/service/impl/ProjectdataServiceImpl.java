package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.ProjectdataMapper;
import com.sjdl.cslcp.service.ProjectdataService;

/**
 * @author 作者 阎春絮:
 * @version 创建时间：2021年6月29日 上午13:37:12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectdataServiceImpl implements ProjectdataService {
//	// 整体代码有错误，为避免生成JavaDoc产生警告和错误，注释代码
//	@Autowired
//	private ProjectdataMapper projectdataMapper;
//
//	@Override
//	public List<Map<String, Object>> findAllProjectdata(Map<String, Object> map) {
//		return projectdataMapper.findAllProjectdata(map);
//	}
//
//	@Override
//	public Map<String, Object> findById(int id) {
//		return projectdataMapper.findById(id);
//	}
//
//	@Override
//	public int edit(Map<String, Object> map) {
//		return projectdataMapper.edit(map);
//	}
//
//	@Override
//	public int del(int id) {
//		return projectdataMapper.del(id);
//	}
//
//	@Override
//	public int insertProjectdata(Map<String, Object> map) {
//		return projectdataMapper.insertProjectdata(map);
//	}
}
