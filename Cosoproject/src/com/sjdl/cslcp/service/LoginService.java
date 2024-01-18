package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 上午8:56:53 
*/
public interface LoginService {
	
	/**
	 * 判断账号所对应的密码是否正确
	 * @param account 账号
	 * @param password 密码
	 * @return 返回 0为用户不存在，1是密码错误，2是账号已锁定，3是用户已删除，4是登陆成功
	 */
	public int checkPassword(String account,String password);
	
	/**
	 * 根据账号去查找所对应的角色数据
	 * @param account 账号
	 * @return 返回集合 是controller中的 list数据
	 */
	public List<Map<String,Object>> findAuthority(String account);
	
	/**
	 * 根据账号查找账号所对应的权限集合
	 * @param account 账号
	 * @return 返回权限集合 是前面controller中的authoritylist数据集合
	 */
	public List<Map<String,Object>> findAllAuthority(String account);
	
	/**
	 * 根据账号查找对应的个人信息数据
	 * @param account 账号
	 * @return 返回一条个人信息数据
	 */
	public Map<String,Object> findPersonInfo(String account);
	
}
