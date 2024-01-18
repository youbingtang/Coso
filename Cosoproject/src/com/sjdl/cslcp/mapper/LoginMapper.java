package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 上午8:18:49 
*/
public interface LoginMapper {
	/**
	 * 根据账号找到对应的密码 以便前面和用户登录时的密码作比较
	 * @param account 账号
	 * @return 返回密码和相关信息
	 */
	public Map<String,Object> findPassworrd(String account);
	
	/**
	 * 根据账号找对应角色信息
	 * @param account 账号
	 * @return 返回账号所对应的角色信息集合
	 */
	public List<Map<String,Object>> findAuthority(String account);
	
	/**
	 * 根据账号找到所对应的所有权限集合
	 * @param account 账号
	 * @return 返回所有权限集合
	 */
	public List<Map<String,Object>> findAllAuthority(String account);
	
	/**
	 * 根据账号找到所对应的个人信息
	 * @param account 账号
	 * @return  返回个人信息数据
	 */
	public Map<String,Object> findPersonInfo(String account);
}
