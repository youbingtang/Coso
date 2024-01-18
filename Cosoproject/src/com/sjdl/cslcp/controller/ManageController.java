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
import com.sjdl.cslcp.service.ManageService;

/**
 * @author 作者：管文斌
 * @version 创建时间：2021年6月30日15:52:30
 */
@RestController
public class ManageController {

	@Autowired
	private ManageService manageServiceImpl;
	
	/**
	 * 禁用企业和用户
	 * @param cid 公司id
	 * @return 1成功，-1未知异常
	 */
	@RequestMapping("/delUserAndCompany")
	public int delUserAndCompany(String cid) {
		return manageServiceImpl.delUserAndCompany(cid);
	}
	
	/**
	 * logo审核通过
	 * @param cid 公司id
	 * @return 1成功，-1未知异常
	 */
	@RequestMapping("/updateLogoApply")
	public int updateLogoApply(String cid) {
		return manageServiceImpl.updateLogoApply(cid);
	}
	
	/**
	 * logo审核不通过
	 * @param cid 公司id
	 * @return 1成功，-1未知异常
	 */
	@RequestMapping("/updateLogoUnapply")
	public int updateLogoUnapply(String cid) {
		return manageServiceImpl.updateLogoApply(cid);
	}
	
	/**
	 * 查询所有待审核logo
	 * @param currentPage 当前页数
	 * @return 待审核logo信息
	 */
	@RequestMapping("/selectUnDefindLogo")
	public PageInfo<Map<String, Object>> selectUnDefindLogo(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = manageServiceImpl.selectUnDefindLogo();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}
	
	/**
	 * 查询所有企业信息
	 * @param currentPage 当前页数
	 * @return 企业详细信息
	 */
	@RequestMapping("/selectAllCompanyInfo")
	public PageInfo<Map<String, Object>> selectAllCompanyInfo(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = manageServiceImpl.selectAllCompanyInfo();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}
	
	/**
	 * 查看企业logo
	 * @param cid 公司id
	 * @return 查看企业logo
	 */
	@RequestMapping("/selectCompanyLogo")
	public Map<String,Object> selectCompanyLogo(String cid){
		return manageServiceImpl.selectCompanyLogo(cid);
	}
	
	/**
	 * 查看企业seal
	 * @param cid 公司id
	 * @return 查看企业seal
	 */
	@RequestMapping("/selectCompanySeal")
	public List<Map<String,Object>> selectCompanySeal(String cid){
		return manageServiceImpl.selectCompanySeal(cid);
	}
	
	/**
	 * 平台通过服务申请
	 * @param cid 公司id
	 * @return 1成功，-1未知异常
	 */
	@RequestMapping("/applyService")
	public int applyService(String cid) {
		int result  = -1;
		
		result = manageServiceImpl.applyService(cid);
		
		return result;
	}
	
	/**
	 * 平台不同意服务申请
	 * @param cid 公司id
	 * @return 1成功，-1未知错误
	 */
	@RequestMapping("/unapplyService")
	public int unapplyService(String cid) {
		return manageServiceImpl.unapplyService(cid);
	}
	
	/**
	 * 查询所有申请服务的公司信息
	 * @param currentPage 当前页数
	 * @return 公司信息
	 */
	@RequestMapping("/selectAllServiceApplay")
	public PageInfo<Map<String, Object>> selectAllServiceApplay(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = manageServiceImpl.selectAllServiceApplay();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}
	
	/**
	 * 平台审核企业通过
	 * @param cid 企业id
	 * @param account 账号
	 * @return 返回 1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/updateCompanyStatusToOne")
	public int updateCompanyStatusToOne(String cid,String account) {
		int result = -1;
		
		Map<String,Object> map = new HashMap<>();
		map.put("cid", cid);
		map.put("account", account);
		result = manageServiceImpl.updateCompanyStatusToOne(map);
		
		return result;
	}
	
	/**
	 * 平台审核企业不通过
	 * @param cid 企业id
	 * @param account 账号
	 * @return 返回 1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/updateCompanyStatusToTwo")
	public int updateCompanyStatusToTwo(String cid,String account) {
		int result = -1;
		
		Map<String,Object> map = new HashMap<>();
		map.put("cid", cid);
		map.put("account", account);
		result = manageServiceImpl.updateCompanyStatusToTwo(map);
		
		return result;
	}

	/**
	 * 查询所有出单申请的提单（平台的）提单状态为2
	 * @param currentPage 显示的页数
	 * @return 返回List提单详细信息
	 */
	@RequestMapping("/findAllStatesIsTwo")
	public PageInfo<Map<String, Object>> findAllStatesIsTwo(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = manageServiceImpl.findAllStatesIsTwo();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}
	
	/**
	 * 平台通过申请（提单状态为变为3）
	 * @param blNo 提单号
	 * @return 返回int，1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/pingTaiApply")
	public int pingTaiApply(String blNo) {
		int result = -1;

		result = manageServiceImpl.pingTaiApply(blNo);

		return result;
	}

	/**
	 * 平台不通过（置空所有状态位）
	 * @param blNo 单号
	 * @return 返回int，1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/pingTaiUnapply")
	public int pingTaiUnapply(String blNo) {
		int result = -1;

		result = manageServiceImpl.pingTaiUnapply(blNo);

		return result;
	}

	/**
	 * 找到可以打印的订单，提单状态为4（平台）
	 * @param currentPage 当前要显示的页数
	 * @return 分页数据及提单信息集合
	 */
	@RequestMapping("/findAllstatesisfour")
	public PageInfo<Map<String, Object>> findAllstatesisfour(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = manageServiceImpl.findAllstatesisfour();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}

	/**
	 * 平台点击打印以后状态位由4变成5
	 * @param blNo 提单号
	 * @return 返回int，1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/painting")
	public int painting(String blNo) {
		int result = -1;

		result = manageServiceImpl.painting(blNo);

		return result;
	}

	/**
	 * 平台显示所有邮寄申请的提单(状态位为6）
	 * @param currentPage 当前要显示的页数
	 * @return 分页数据及提单信息集合
	 */
	@RequestMapping("/findAllstatesissix")
	public PageInfo<Map<String, Object>> findAllStatesIssix(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;

		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = manageServiceImpl.findAllstatesissix();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}

	/**
	 * 查找所有邮寄公司信息备用
	 * @return 邮寄公司id，name
	 */
	@RequestMapping("/findAllMallingcompany")
	public List<Map<String, Object>> findAllMallingcompany() {
		List<Map<String, Object>> list = manageServiceImpl.findAllMallingcompany();

		return list;
	}

	/**
	 * 邮寄通过选择快递公司开始邮寄
	 * @param blNoid 提单id
	 * @param recordUserAccount 登录人（平台管理员）账号
	 * @param orderNo 快递单号
	 * @param expressCompanyId 邮寄公司id
	 * @return 返回int，1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/malling")
	public int malling(String blNoid, String recordUserAccount, String orderNo, String expressCompanyId) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("blNoid", blNoid);
		map.put("recordUserAccount", recordUserAccount);
		map.put("orderNo", orderNo);
		map.put("expressCompanyId", expressCompanyId);

		return manageServiceImpl.malling(map);
	}
	

}
