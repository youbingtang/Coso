package com.sjdl.cslcp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.LoginMapper;
import com.sjdl.cslcp.service.LoginService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 上午8:56:12 
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginMapper loginMapper;

	@Override
	public int checkPassword(String account, String password) {
		int result = -1;
		Map<String,Object> map = loginMapper.findPassworrd(account);
		
		//判断用户是否存在
		if(map != null) {
			//判断密码是否一致
			if(password.equals(map.get("password"))) {
				//判断账号状态是否为锁定
				if(Integer.parseInt( map.get("status").toString()) == 0) {
					//判断账号是否被删除
					if(Integer.parseInt(map.get("isDel").toString()) == 0) {
						//登录成功
						result = 4;
					}else {
						result = 3;
					}
				}else {
					result = 2;
				}
			}else {
				result = 1;
			}
		}else {
			result = 0;
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findAuthority(String account) {
		return loginMapper.findAuthority(account);
	}

	@Override
	public List<Map<String, Object>> findAllAuthority(String account) {
		List<Map<String,Object>> authortylist= loginMapper.findAllAuthority(account);
		List<Map<String,Object>> parentlist = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> childrenlist = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> promaxlist = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> allauthorty : authortylist) {
			if(allauthorty.get("pid")==null) {
				parentlist.add(allauthorty);
			}else {
				childrenlist.add(allauthorty);
			}
		}
		for (Map<String,Object> fumap : parentlist) {
			List<Map<String,Object>> newchildrenlist=new ArrayList<Map<String,Object>>();
			for(Map<String,Object>zimap : childrenlist) {
				if(fumap.get("id")==zimap.get("pid")) {
					newchildrenlist.add(zimap);
					fumap.put("childrenlist", newchildrenlist);
					
				}
			}
			promaxlist.add(fumap);
		}
		
		return promaxlist;

	}

	@Override
	public Map<String, Object> findPersonInfo(String account) {
		return loginMapper.findPersonInfo(account);
	}
}
