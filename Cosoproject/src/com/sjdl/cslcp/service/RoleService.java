package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年6月29日 上午9:21:41 
*/
public interface RoleService {
	
	/**
	 * 查询对应企业下的所有企业
	 * @param map 参数为cid代表企业id,ftype代表企业类型
	 * @return 返回该企业所有的角色
	 */
    public List<Map<String,Object>> findAllRoleByCidAndType(Map<String,Object> map);
    
    /**
	 * 查询管理员对应的权限
	 * @param cid 企业id
	 * @return 返回管理员的所有权限
	 */
  	public List<Map<String,Object>> findrole(int cid); 
  	
  	/**
  	 * 修改员工对应的角色
  	 * @param map 参数为account账号,roleId权限id
  	 * @return  1代表成功0代表失败
  	 */
  	public int updatecompanyrole(Map<String,Object> map);
  	
  	/**
  	 * 修改角色权限
  	 * @param id 角色id
  	 * @param authorityId 代表权限id
  	 * @return  1代表成功0代表失败
  	 */
  	public int updateroleauthority(int id,int[] authorityId);

  	/**
  	 * 修改角色权限
  	 * @param id 角色id
  	 * @return  1代表成功0代表失败
  	 */
  	public int delrole(int id);

  	/**
  	 * 添加角色
  	 * @param name 角色名
  	 * @param account 账号
  	 * @param authorityId 权限id
  	 * @return 1代表成功0代表失败
  	 */
  	public int addroleauthority(String name,String account,int[] authorityId);
  	
  	
  	public List<Map<String,Object>> findauthoritybyroid(String roleId);
}


