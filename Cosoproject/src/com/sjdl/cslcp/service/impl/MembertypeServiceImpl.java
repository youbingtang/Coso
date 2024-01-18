package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.MembertypeMapper;
import com.sjdl.cslcp.service.MembertypeService;

/**
*作者：杨兰兰
*创建时间：2021年6月30日 上午9:10:45
**/
@Service
@Transactional(rollbackFor = Exception.class)
public class MembertypeServiceImpl implements MembertypeService{

	@Autowired
	private MembertypeMapper membertypeMapper;
	
	@Override
	public Map<String, Object> findMemberTypeById(int id) {
		
		return membertypeMapper.findMemberTypeById(id);
	}

	@Override
	public int insertMemberType(Map<String, Object> map) {
		
		return membertypeMapper.insertMemberType(map);
	}

	@Override
	public int delMemberType(int id) {
		
		return membertypeMapper.delMemberType(id);
	}

	@Override
	public int editMemberType(Map<String, Object> map) {
		
		return membertypeMapper.editMemberType(map);
	}

	@Override
	public List<Map<String, Object>> findLikeMemberType(Map<String, Object> map) {
		
		return membertypeMapper.findLikeMemberType(map);
	}

}
