package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.RoleService;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年6月29日 上午9:28:59 
*/
@RestController
public class RoleController {
	
	@Autowired 
	private RoleService roleServiceImpl;
	
	/**
	 * @param currentPage 当前页
	 * @param cid 企业id
	 * @param type 角色类型
	 * @return 返回该企业所有的角色
	 */
	@RequestMapping("/findAllRole")
	public PageInfo<Map<String,Object>> findAllRole(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
												@RequestParam(value="cid")int cid,
												@RequestParam(value="type")int type
									            ){
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("cid", cid);
			map.put("type", type);
		int rowNum = 5;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> rolelist = roleServiceImpl.findAllRoleByCidAndType(map);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(rolelist);
		
		return pageInfo;
	}
	
	/**
	 * 查询管理员对应的权限根据企业id
	 * @param cid 企业id
	 * @return 管理员的所有权限
	 */
	@RequestMapping("/findrole")
	public List<Map<String,Object>> findrole(int cid){
		List<Map<String, Object>> result = roleServiceImpl.findrole(cid); 
		
		return result;
	}
		
	/**
	 * 修改员工对应的角色
	 * @param account 账号
	 * @param roleId 角色id
	 * @return 1代表成功0代表失败
	 */
	@RequestMapping("/updatecompanyrole")
	public int updatecompanyrole(String account,int roleId) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("account", account);
			map.put("roleId", roleId);
			int result=roleServiceImpl.updatecompanyrole(map);
			
			return result;
	}
	
	/**
	 * 根据公司ID查找公司所有角色
	 * 修改角色权限
	 * @param id 角色id
	 * @param authorityId 为权限id
	 * @return result=0修改失败，result=1修改成功
	 */
	@RequestMapping("/updateroleauthority")
	@ResponseBody
	public int updateroleauthority(int id,int[] authorityId) {
		int result=roleServiceImpl.updateroleauthority(id, authorityId);
		
		return result;
	}
	
	/**
	 * 删除角色
	 * @param id 角色对应的id
	 * @return 1代表成功0代表失败
	 */
	@RequestMapping("/delrole")
	public int delrole(int id) {
		int result=roleServiceImpl.delrole(id);
		
		return result;
	}	
	
	/**
	 * 添加角色
	 * @param name 角色名
	 * @param account 账号
	 * @param authorityId 权限id
	 * @return 1代表成功0代表失败
	 */
	@RequestMapping("/addroleauthority")
	public int addroleauthority(String name,String account,int[] authorityId) {
		int result=roleServiceImpl.addroleauthority(name, account, authorityId);
		
		return result;
	}
	
	/**
	  * 根据角色id找相应的权限
	 * @param roleId 角色id
	 * @return 该角色对应的权限
	 */
	@RequestMapping("/findauthoritybyroid")
	public List<Map<String,Object>> findauthoritybyroid(String roleId){
		return roleServiceImpl.findauthoritybyroid(roleId);
	}
}
