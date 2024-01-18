package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.PersonInfoMapper;
import com.sjdl.cslcp.service.PersonInfoService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 下午1:35:29 
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class PersonInfoServiceImpl implements PersonInfoService{
	
	@Autowired
	private PersonInfoMapper personInfoMapper;

	@Override
	public int checkRepassword(String password, String repassword) {
		int result = -1;
		if(password.equals(repassword)) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}

	@Override
	public int updatePersonInfo(Map<String, Object> map) {
		return personInfoMapper.updatePersonInfo(map);
	}

	@Override
	public List<Map<String, Object>> findAllAccount(Map<String,Object> map) {
		return personInfoMapper.findAllAccount(map);
	}

	@Override
	public int updateStatus(String id) {
		return personInfoMapper.updateStatus(id);
	}

	@Override
	public int addAccount(Map<String, Object> map) {
		int result = -1;
		int flag = personInfoMapper.checkAccount(map);
		int flag1=personInfoMapper.checkIdCardNo(map);
		if(flag == 0) {
			if(flag1 == 0) {
				personInfoMapper.addAccount(map);
				result = personInfoMapper.insertRoleAccount(map);
			}else {
				result = 3;
			}
		}else {
			result = 2;
		}
		return result;
	}

	@Override
	public int delAccount(String id) {
		return personInfoMapper.delAccount(id);
	}
}
