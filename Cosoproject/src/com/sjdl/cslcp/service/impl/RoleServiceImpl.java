package com.sjdl.cslcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.RoleMapper;
import com.sjdl.cslcp.service.RoleService;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年6月29日 上午9:23:06 
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Map<String, Object>> findAllRoleByCidAndType(Map<String, Object> map) {
		return roleMapper.findAllRoleByCidAndType(map);
	}
	@Override
	public int delrole(int id) {
		return roleMapper.delrole(id);
	}

	@Override
	public int addroleauthority(String name,String account,int[] authorityId) {
		int result=0;
		//查询添加人详细信息
		Map<String,Object> msg =new HashMap<String, Object>(); 
		msg=roleMapper.findmsg(account);
		//向角色表中添加信息
		if(msg!=null) {
			Map<String,Object> rolemap =new HashMap<String, Object>(); 
			rolemap.put("name",name );
			rolemap.put("createUserId",msg.get("id") );
			rolemap.put("createUserName",msg.get("name") );
			rolemap.put("companyId",msg.get("companyId") );
			roleMapper.insertmsgtorole(rolemap);
			System.out.print("aaaaaaaaaaaaaaaaaa"+rolemap.get("id"));
			//通过authorityId获取父级authorityId
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list=roleMapper.findfuji(authorityId);
			//向权限角色关联关系表中添加父级信息
			for(Map<String,Object> item:list) {
				Map<String,Object> authorityRoleRefmap =new HashMap<String, Object>(); 
				authorityRoleRefmap.put("authorityId",item.get("pid"));
				authorityRoleRefmap.put("roleId",rolemap.get("id"));
				authorityRoleRefmap.put("createUserId",msg.get("id"));
				authorityRoleRefmap.put("createUserName",msg.get("name"));
				roleMapper.insertmsgtoauthorityRoleRef(authorityRoleRefmap);
			}
			//向权限角色关联关系表中添加子级信息
			for(int item:authorityId) {
				Map<String,Object> authorityRoleRefmap =new HashMap<String, Object>(); 
				authorityRoleRefmap.put("authorityId", item);
				authorityRoleRefmap.put("roleId",rolemap.get("id"));
				authorityRoleRefmap.put("createUserId",msg.get("id"));
				authorityRoleRefmap.put("createUserName",msg.get("name"));
				roleMapper.insertmsgtoauthorityRoleRef(authorityRoleRefmap);
			}
			result = 1;
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findrole(int cid) {
		return roleMapper.findrole(cid);
	}

	@Override
	public int updatecompanyrole(Map<String, Object> map) {
		return roleMapper.updatecompanyrole(map);
	}

	@Override
	public int updateroleauthority(int id, int[] authorityId) {
		//更新权限名称
		int result=0;
		//删除角色权限关联关系表中角色id为id的所以数据
			result=roleMapper.deleteauthorityRoleRef(id);
			//角色权限关联关系表中插入角色id为id，权限id为authorityid的N条数据
			//通过authorityId获取父级authorityId
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list=roleMapper.findfuji(authorityId);
			//向权限角色关联关系表中添加父级信息
			for(Map<String,Object> item:list) {
				Map<String,Object> authority = new HashMap<String,Object>();
				authority.put("roleId", id);
				authority.put("authorityid",item.get("pid"));
				result=roleMapper.insertauthorityRoleRef(authority);
			}
			for(int item:authorityId) {
				Map<String,Object> authority = new HashMap<String,Object>();
				authority.put("roleId", id);
				authority.put("authorityid",item);
				result=roleMapper.insertauthorityRoleRef(authority);
			}
		return result;
	}
	@Override
	public List<Map<String, Object>> findauthoritybyroid(String roleId) {
		return roleMapper.findauthoritybyroid(roleId);
	}
}
