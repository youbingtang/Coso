package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjdl.cslcp.service.LoginService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 上午8:11:32 
*/
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginServiceImpl;
	
	/**
	 * 登录验证密码是否正确
	 * @param account 账号
	 * @param password 密码
	 * @return 返回登录是否成功 0为用户不存在，1是密码错误，2是账号已锁定，3是用户已删除，4是登陆成功
	 */
	@RequestMapping("/login")
	public int login(String account,String password) {
		int result = loginServiceImpl.checkPassword(account, password);
		
		return result;
	}
	
	/**
	 * 根据账号查到所对应的权限和角色信息
	 * @param account 账号
	 * @return 返回map,map中的 list时角色集合  authoritylist所对应的权限集合
	 */
	@RequestMapping("/findMenuByAccount")
	public Map<String,Object> findMenuByAccount(String account){
		List<Map<String,Object>> list = loginServiceImpl.findAuthority(account);
		List<Map<String,Object>> authoritylist = loginServiceImpl.findAllAuthority(account);
		
		Map<String,Object> map = new HashMap<String,Object>();
 		map.put("list", list);
 		map.put("authoritylist", authoritylist);
 		
		return map;
	}
	
	/**
	 * 根据账号找到对应的个人信息
	 * @param account 账号
	 * @return 返回个人信息数据
	 */
	@RequestMapping("/findPersonInfo")
	public Map<String,Object> findPersonInfo(String account){
		return loginServiceImpl.findPersonInfo(account);
	}
}
