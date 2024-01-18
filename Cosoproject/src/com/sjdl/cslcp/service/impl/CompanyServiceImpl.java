package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.CompanyMapper;
import com.sjdl.cslcp.service.CompanyService;

/** 
* @author 作者 管文斌
* @version 创建时间：2021年6月30日09:40:51 
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyMapper companyMapper;

	// 注册所需省份和城市
	@Override
	public List<Map<String, Object>> findProvince() {
		return companyMapper.findProvince();
	}

	@Override
	public List<Map<String, Object>> findCity(String provinceId) {
		return companyMapper.findCity(provinceId);
	}

	@Override
	public List<Map<String, Object>> findAllCompanyType() {
		return companyMapper.findAllCompanyType();
	}

	// 注册企业信息
	@Override
	public int insertCompany(Map<String, Object> parammap) {
		int result = -1;
		int num = -1;
		
		String account = (String) parammap.get("account");
		
		Map<String,Object> map = 
				companyMapper.selectAccountCount(account);
		num =Integer.parseInt(map.get("count").toString());
		if(num == 0) {
			// 插入用户
			companyMapper.insertUser(account);
			// 根据账号查找用户id
			Map<String, Object> accountmap = companyMapper.selectUserId(account);
			String accountid = accountmap.get("id").toString();
			// 插入企业信息
			parammap.put("adminAccountId", accountid);
			companyMapper.insertCompany(parammap);
			// 根据账号id查找企业id
			Map<String, Object> companymap = companyMapper.selectCompanyId(accountid);
			String companyid = companymap.get("id").toString();
			// 将企业id插入账号表
			Map<String, Object> idmap = new HashMap<String, Object>();
			idmap.put("companyId", companyid);
			idmap.put("accountId", accountid);
			companyMapper.updateUserCompany(idmap);
			// 插入角色表数据
			companyMapper.insertRole(companyid);
			// 根据所属企业id找到角色表id
			Map<String, Object> role = companyMapper.selectroleid(companyid);
			String roleid = role.get("id").toString();
			
			int[] list= {1,4,16,17,18,8,29};
			// 插入角色账号对照表
			Map<String, Object> roleaccountid = new HashMap<>();
			roleaccountid.put("roleID", roleid);
			roleaccountid.put("accountID", accountid);
			
			for(int i=0 ;i<list.length;i++) {
				roleaccountid.put("authorityId", list[i]);
				companyMapper.insertAuthorityRoleRef(roleaccountid);
			}
			result = companyMapper.insertRoleAccountRef(roleaccountid);
		}else {
			result = 2;
		}
		
		return result;
	}

	// 查找所有没有审核的企业
	@Override
	public List<Map<String, Object>> findAllUndifindCompany() {
		return companyMapper.findAllUndifindCompany();
	}

	// 查看单个企业详情
	@Override
	public Map<String, Object> findAllCompanyInfor(int cid) {
		return companyMapper.findAllCompanyInfor(cid);
	}

	// 修改企业信息
	@Override
	public int updateCompanyInfor(Map<String, Object> map) {
		String account = (String) map.get("account");
		Map<String, Object> accountidmap = companyMapper.selectUserId(account);
		String accountid = accountidmap.get("id").toString();
		map.put("adminAccountId", accountid);
		
		return companyMapper.updateCompanyInfor(map);
	}

	// 查找公司name，id
	@Override
	public Map<String, Object> findCompany() {
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String, Object>> SignatureList = companyMapper.findCompany1();
		List<Map<String, Object>> SendBlList = companyMapper.findCompany2();
		map.put("出单", SendBlList);
		map.put("签章", SignatureList);
		
		return map;
	}

	// 平台审核通过
	@Override
	public int confim(int id) {
		return companyMapper.confim(id);
	}

	// 平台审核不通过
	@Override
	public int unconfim(int id) {
		return companyMapper.unconfim(id);
	}

	// 申请收单服务
	@Override
	public int companyapplyA(Map<String, Object> map) {
		int result = -1;
		
		String account = map.get("applyAccount").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		map.put("companyId", mmap.get("companyId"));
		map.put("applyUserName", mmap.get("name"));
		result = companyMapper.companyapplyA(map);
		
		return result;
	}

	// 开通签章服务申请
	@Override
	public int companyapplyC(Map<String, Object> map) {
		int result = -1;
		
		String account = map.get("applyAccount").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		map.put("companyId", mmap.get("companyId"));
		map.put("applyUserName", mmap.get("name"));
		result = companyMapper.companyapplyC(map);
		
		return result;
	}

	// 开通出单服务申请
	@Override
	public int companyapplyB(Map<String, Object> map) {
		int result = -1;
		
		String account = map.get("applyAccount").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		map.put("companyId", mmap.get("companyId"));
		map.put("applyUserName", mmap.get("name"));
		result = companyMapper.companyapplyB(map);
		
		return result;
	}

	// 签章上传签章
	@Override
	public int insertSignatureSeal(Map<String, Object> map) {
		int result = -1;
		
		String account = map.get("account").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		map.put("companyId", mmap.get("companyId"));
		map.put("createUserName", mmap.get("name"));
		result = companyMapper.insertSignatureSeal(map);
		
		return result ;
	}

	// 出单上传签章和Logo
	@Override
	public int insertSendBlSeal(Map<String, Object> map) {
		int result = -1;
		
		String account = map.get("account").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		map.put("companyId", mmap.get("companyId"));
		map.put("createUserName", mmap.get("name"));
		result =companyMapper.insertSendBlSeal(map);
		
		return result;
	}

	@Override
	public int insertSendBlLogo(Map<String, Object> map) {
		int result = -1;
		
		String account = map.get("account").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		map.put("companyId", mmap.get("companyId"));
		map.put("createUserName", mmap.get("name"));
		result =companyMapper.insertSendBlLogo(map);
		
		return result;
	}

	@Override
	public int updatelogobycid(Map<String, Object> map) {
		String account = map.get("account").toString();
		Map<String, Object> mmap = companyMapper.findApplyUserInfo(account);
		
		map.put("createUserName", mmap.get("name"));
		map.put("copanyId",mmap.get("companyId"));
		return companyMapper.updatelogobycid(map);
	}

	@Override
	public String findlogofilebycid(String cid) {
		return companyMapper.findlogofilebycid(cid);
	}

	@Override
	public int deleteSendBlLogo(String logofile) {
		return companyMapper.deleteSendBlLogo(logofile);
	}

}
