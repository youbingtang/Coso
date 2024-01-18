package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.ProjectdataService;

/**
 * @author 作者 阎春絮:
 * @version 创建时间：2021年6月29日 上午13:54:32
 */
@RestController
public class ProjectdataController {
// 整体代码有错误，为避免生成JavaDoc产生警告和错误，注释代码
//	@Autowired
//	private ProjectdataService projectdataServiceImpl;
//	
//	/**
//	 * 根据查询返回查询结果
//	 * @param map
//	 * @return 返回查询的信息
//	 */
//	@RequestMapping("/projectdataList")
//	public List<Map<String, Object>> projectdataList() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String, Object>> list = projectdataServiceImpl.findAllProjectdata(map);
//		return list;
//	}
//    
//	/**
//	 * 根据输入的id值进行查询
//	 * @param id
//	 * @return 返回查询的结果
//	 */
//	@RequestMapping("/projectdataToEdit")
//	public Map<String, Object> projectdataToEdit(int id) {
//		Map<String, Object> map = projectdataServiceImpl.findById(id);
//		return map;
//	}
//	
//	/**
//	 * 在编辑页面进行编辑操作
//	 * @param map
//	 * @return 返回编辑后的表数据
//	 */
//	@RequestMapping("/projectdatadoEdit")
//	public Map<String, Object> projectdatadoEdit(String blld, String type, String cause, String destruction,
//			String guarantee, String applyCompanyName, String applyUserAccount, String applyUserName, String applyDay,
//			String platformAuditResult, String platformAuditOpinion, String platformAuditUserAccount,
//			String platformAuditUserName, String platformAuditDay, String version) {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("blId", blld);
//		param.put("type", type);
//		param.put("cause", cause);
//		param.put("destruction", destruction);
//		param.put("guarantee", guarantee);
//		param.put("applyCompanyName", applyCompanyName);
//		param.put("applyUserAccount", applyUserAccount);
//		param.put("applyUserName", applyUserName);
//		param.put("applyDay", applyDay);
//		param.put("platformAuditResult", platformAuditResult);
//		param.put("platformAuditOpinion", platformAuditOpinion);
//		param.put("platformAuditUserAccount", platformAuditUserAccount);
//		param.put("platformAuditUserName", platformAuditUserName);
//		param.put("platformAuditDay", platformAuditDay);
//		param.put("version", version);
//
//		int result = projectdataServiceImpl.edit(param);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", result);
//		
//		return map;
//	}
//	
//	/**
//	 * 进行分页操作
//	 * @param map
//	 * @return 将查询到的值进行分页操作
//	 */
//	@RequestMapping("/projectdataListPage")
//	public PageInfo<Map<String, Object>> projectdataListPage(
//			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage,
//			@RequestParam(defaultValue = "", value = "id") String id,
//			@RequestParam(defaultValue = "", value = "blId") String blId,
//			@RequestParam(defaultValue = "", value = "type") String type,
//			@RequestParam(defaultValue = "", value = "cause") String cause,
//			@RequestParam(defaultValue = "", value = "destruction") String destruction,
//			@RequestParam(defaultValue = "", value = "guarantee") String guarantee,
//			@RequestParam(defaultValue = "", value = "applyCompanyName") String applyCompanyName,
//			@RequestParam(defaultValue = "", value = "applyUserAccount") String applyUserAccount,
//			@RequestParam(defaultValue = "", value = "applyUserName") String applyUserName,
//			@RequestParam(defaultValue = "", value = "applyDay") String applyDay,
//			@RequestParam(defaultValue = "", value = "platformAuditResult") String platformAuditResult,
//			@RequestParam(defaultValue = "", value = "platformAuditOpinion") String platformAuditOpinion,
//			@RequestParam(defaultValue = "", value = "platformAuditUserAccount") String platformAuditUserAccount,
//			@RequestParam(defaultValue = "", value = "platformAuditUserName") String platformAuditUserName,
//			@RequestParam(defaultValue = "", value = "platformAuditDay") String platformAuditDay,
//			@RequestParam(defaultValue = "", value = "version") String version) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		map.put("id", id);
//		map.put("blId", blId);
//		map.put("type", type);
//		map.put("cause", cause);
//		map.put("destruction", destruction);
//		map.put("guarantee", guarantee);
//		map.put("applyCompanyName", applyCompanyName);
//		map.put("applyUserAccount", applyUserAccount);
//		map.put("applyUserName", applyUserName);
//		map.put("applyDay", applyDay);
//		map.put("platformAuditResult", platformAuditResult);
//		map.put("platformAuditOpinion", platformAuditOpinion);
//		map.put("platformAuditUserAccount", platformAuditUserAccount);
//		map.put("platformAuditUserName", platformAuditUserName);
//		map.put("platformAuditDay", platformAuditDay);
//		map.put("version", version);
//		// 每页五条数据
//		int rowNum = 5;
//		// 当前页数，条数
//		PageHelper.startPage(currentPage, rowNum);
//		// 显示数据
//		List<Map<String, Object>> list = projectdataServiceImpl.findAllProjectdata(map);
//		// 翻页
//		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
//		
//		return pageInfo;
//	}
//	
//	/**
//	 * 删除某条信息
//	 * @param id
//	 * @return 返回删除后的表
//	 */
//	@RequestMapping("/subjectdoDel")
//	public int projectdatadoDel(int id) {
//		int result = projectdataServiceImpl.del(id);
//		
//		return result;
//	}
//	
//	/**
//	 * 插入新数据
//	 * @param map
//	 * @return 插入后的表数据
//	 */
//	@RequestMapping("/projectdatadoAdd")
//	public int projectdatadoAdd(String stem, int type) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("stem", stem);
//		map.put("type", type);
//		int result = projectdataServiceImpl.insertProjectdata(map);
//		
//		return result;
//	}
}

