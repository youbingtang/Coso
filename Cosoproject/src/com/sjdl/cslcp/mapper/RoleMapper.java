package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年6月29日 上午9:19:39 
*/
public interface RoleMapper {

	/**
	 * 查询对应企业下的所有企业
	 * @param map 参数为cid代表企业id type代表企业类型
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
	 * @param map 参数为account账号,roleId角色id
	 * @return 1代表成功0代表失败
	 */
	public int updatecompanyrole(Map<String,Object> map);
//该方法暂时无需使用！！！！！！！！！
//	/**
//	 * 修改角色权限
//	 * 更新权限名称
//	 * @param map
//	 * @return 1代表成功0代表失败
//	 */
//	public int updatename(Map<String,Object> map);
	
	/**
	 * 删除角色权限关联关系表中角色
	 * @param id 索引id
	 * @return 1代表成功0代表失败
	 */
	public int deleteauthorityRoleRef(int id);	

	/**
	 * 角色权限关联关系表中插入角色id为id，权限id为authorityid的N条数据
	 * @param authority 为authorityid数组
	 * @return 1代表成功0代表失败
	 */
	public int insertauthorityRoleRef(Map<String,Object> authority);
	
	/**
	 * 删除角色
	 * @param id 角色对应的id
	 * @return 1代表成功0代表失败
	 */
	public int delrole(int id);
	
	/**
	 * 添加角色
	 * 查询添加人详细信息
	 * @param account 账号
	 * @return 返回添加人详细信息
	 */
	public Map<String,Object> findmsg(String account);
	
	/**
	 * 向角色表中添加信息
	 * @param rolemap 企业id,创建人姓名,创建人id,角色名
	 * @return 1代表成功0代表失败
	 */
	public int insertmsgtorole(Map<String,Object> rolemap);
	
	/**
	 * 向权限角色关联关系表中添加信息
	 * @param authorityRoleRefmap 角色id,权限id,创建人id,创建人姓名
	 * @return 1代表成功0代表失败
	 */
	public int insertmsgtoauthorityRoleRef(Map<String,Object> authorityRoleRefmap);

	
	public List<Map<String,Object>> findauthoritybyroid(String roleId);
	
	/**
	 * 查询父级id
	 * @param authorityId 父级
	 * @return 父级id
	 */
	public List<Map<String,Object>> findfuji(@Param("authorityId") int[] authorityId);
}


