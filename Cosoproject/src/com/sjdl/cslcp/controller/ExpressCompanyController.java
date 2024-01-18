package com.sjdl.cslcp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sjdl.cslcp.mapper.ExpressCompanyMapper;

/**
 * @author 作者 赵莹:
 * @version 创建时间：2021年6月30日 上午8:18:49
 */
public class ExpressCompanyController {
	@Autowired
	private ExpressCompanyMapper expressCompanyServiceImpl;

	/**
	 * 对于快递信息编辑跳入到对应的页面
	 * @param id 要编辑的快递公司id
	 * @return 成功跳到编辑的界面
	 */
	@RequestMapping("/expressCompanyToEdit")
	public Map<String, Object> expressCompanyToEdit(int id) {
		Map<String, Object> map = expressCompanyServiceImpl.findById(id);
		return map;
	}

	
	/**
	 * 在编辑页面进行编辑
	 * @param id 快递公司id
	 * @param name 快递公司名
	 * @param createDate 创建时间
	 * @param createUserId 创建用户id
	 * @param createUserName  创建用户名
	 * @param modifyDate 修改日期
	 * @param modifyUserId 修改用户id
	 * @param modifyUserName 修改用户名
	 * @param isDel 是否删除
	 * @param version 乐观锁
	 * @return 返回编成功的条数，成功编辑
	 */
	@RequestMapping("/expressCompanydoEdit")
	public Map<String, Object> expressCompanydoEdit(int id, String name, Date createDate, int createUserId,
			String createUserName, Date modifyDate, int modifyUserId, String modifyUserName, int isDel, int version) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("name", name);
		param.put("createDate", createDate);
		param.put("createUserId", createUserId);
		param.put("createUserName", createUserName);
		param.put("modifyDate", modifyDate);
		param.put("modifyUserId", modifyUserId);
		param.put("modifyUserName", modifyUserName);
		param.put("isDel", isDel);
		param.put("version", version);
		int result = expressCompanyServiceImpl.edit(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	/**
	 * 查找全部快递公司信息
	 * @param currentPage 当前页
	 * @param id 快递公司id
	 * @param name 快递公司名
	 * @param createDate 创建时间
	 * @param createUserId 创建用户id
	 * @param createUserName 创建用户名
	 * @param modifyDate 修改时间
	 * @param modifyUserId 修改用户id
	 * @param modifyUserName 修改用户名
	 * @param version 乐观锁
	 * @param isDel 是否删除
	 * @return 返回所有快递公司的信息
	 */
	@RequestMapping("/expressCompanyListPage")
	public PageInfo<Map<String, Object>> expressCompanyListPage(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage,
			@RequestParam(defaultValue = "", value = "id") String id,
			@RequestParam(defaultValue = "", value = "name") String name,
			@RequestParam(defaultValue = "", value = "createDate") String createDate,
			@RequestParam(defaultValue = "", value = "createUserId") String createUserId,
			@RequestParam(defaultValue = "", value = "createUserName") String createUserName,
			@RequestParam(defaultValue = "", value = "modifyDate") String modifyDate,
			@RequestParam(defaultValue = "", value = "modifyUserId") String modifyUserId,
			@RequestParam(defaultValue = "", value = "modifyUserName") String modifyUserName,
			@RequestParam(defaultValue = "", value = "version") String version,
			@RequestParam(defaultValue = "", value = "isDel") String isDel) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rowNum = 5;
		
		map.put("id", id);
		map.put("name", name);
		map.put("createDate", createDate);
		map.put("createUserId", createUserId);
		map.put("createUserName", createUserName);
		map.put("modifyDate", modifyDate);
		map.put("modifyUserId", modifyUserId);
		map.put("modifyUserName", modifyUserName);
		map.put("isDel", isDel);
		map.put("version", version);
		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = expressCompanyServiceImpl.findAllExpressCompany(map);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		
		return pageInfo;
	}

	/**
	 * 删除快递的某条信息
	 * @param id 删除的快递公司id
	 * @return 返回删除的条数，删除成功
	 */
	@RequestMapping("/expressCompanydoDel")
	public int expressCompanydoDel(int id) {
		int result = expressCompanyServiceImpl.del(id);
		
		return result;
	}

	
}