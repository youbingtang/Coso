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
import com.sjdl.cslcp.service.ScAuditService;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年7月1日 下午1:44:18 
*/
@RestController
public class ScAuditController {
	
	@Autowired
	private ScAuditService scAuditServiceImpl;
	
	
	@RequestMapping("/findsccompanyrecording")
	public PageInfo<Map<String,Object>> findsccompanyrecording(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,String sendCompanyId){
		int rowNum = 5;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> sclist = scAuditServiceImpl.findsccompanyrecording(sendCompanyId);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(sclist);
		
		return pageInfo;
		
	}
	/**
	 * 查询提单详情
	 * @param blNo 提单号
	 * @return 提单详情
	 */
	@RequestMapping("/findRecordingByblNo")
	public Map<String,Object> findRecordingByblNo(@RequestParam("blNo")String blNo){
		return scAuditServiceImpl.findrecordingbyblNo(blNo);
	}
	
	/**\
	 * 出单方通过审核
	 * @param account 用户账号
	 * @param blNo 提单号
	 * @return 1代表成功，0代表失败
	 */
	@RequestMapping("/scapply")
	public int scapply(String account,@RequestParam("blNo")String blNo) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account",account);
		map.put("blNo",blNo);
		
		return scAuditServiceImpl.scapply(map);
	}
	
	/**
	 * 出单方未通过审核
	 * @param account 用户账号
	 * @param blNo 提单号
	 * @param scfAuditOpinion 出单方意见
	 * @return 1代表成功 0代表失败
	 */
	@RequestMapping("/scunapply")
	public int scunapply(String account,@RequestParam("blNo")String blNo,String scfAuditOpinion) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account",account);
		map.put("blNo",blNo);
		map.put("scfAuditOpinion",scfAuditOpinion);
		
		return scAuditServiceImpl.scunapply(map);
	}
	
	/**
	 * 查询所有状态为1的提单
	 * @param currentPage 当前页
	 * @param sendCompanyId 出单公司id
	 * @return pageinfo 分页数据
	 */
	@RequestMapping("/findAllStatesIsOne")
	public PageInfo<Map<String,Object>> findAllStatesIsOne(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
														   @RequestParam("cid") int sendCompanyId){
		int rowNum = 5;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> statesisonelist = scAuditServiceImpl.findAllStatesIsOne(sendCompanyId);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(statesisonelist);
		
		return pageInfo;
		
	}
	
	/**
	 * 向平台发送出单申请
	 * @param blNo 提单号
	 * @return 1代表操作成功 0代表操作失败
	 */
	@RequestMapping("/sendRecordingtoPingtai")
	public int sendRecordingtoPingtai(String blNo){
		return scAuditServiceImpl.sendrecordingtopingtai(blNo);
	}
	
	/**
	 * 查询可以申请打印的提单信息
	 * @param currentPage 当前页
	 * @param sendCompanyId 出单公司id
	 * @return 返回的type中1是一次打印，2是二次打印
	 */
	@RequestMapping("/findAllStatesidThree")
	public PageInfo<Map<String,Object>> findAllStatesidThree(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
															 @RequestParam("cid")int sendCompanyId){
		int rowNum = 5;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> statesisthreelist= scAuditServiceImpl.findAllstatesidthree(sendCompanyId);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(statesisthreelist);
		
		return pageInfo;
	}
	
	/**
	 * 为支付准备提单号和价格
	 * @param blNo 提单号
	 * @return 返回提单编号和价格
	 */
	@RequestMapping("/selectSendPriceByblNo")
	public Map<String,Object> selectSendPriceByblNo(String blNo) {
		return scAuditServiceImpl.selectSendPriceByblNo(blNo);
	}
	
	/**
	 * 第一次打印申请
	 * @param paystatus 返回的支付状态1代表成功
	 * @param blNo 提单号
	 * @param account 用户账号
	 * @return 0代表支付成功并且提单状态位变成4,-1代表支付失败
	 */
	@RequestMapping("/printone")
	public int printone(int paystatus,String blNo,String account) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("paystatus",paystatus);
		map.put("blNo",blNo);
		map.put("account",account);
		
		return scAuditServiceImpl.updatetype(map);
	}
	/**
	 * 修改提单状态
	 * @param blNo 提单号
	 * @return 1代表成功
	 */
	@RequestMapping("/updateBillStatus")
	public int BillStatus(String blNO) {
		int result = scAuditServiceImpl.updateBillStatus(blNO);
		return result;
		}
	
	/**
	 * 查询全部的提单作废待审核
	 * @param 
	 * @return 返回待审核作废list
	 */
	@RequestMapping("/findblNoZuoFeiByAll")
	public PageInfo<Map<String,Object>> findblNoZuoFeiByAll(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage){
		int rowNum = 5;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> zuofeilist=scAuditServiceImpl.findblNoZuoFeiByAll();
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(zuofeilist);
		return pageInfo;
		
	}
	
	/**
	 * 提单作废申请不通过
	 * @param bid提单作废表id
	 * @param platformAuditOpinion拒绝意见
	 * @return 返回1更改成功，0失败
	 */
	@RequestMapping("/updateBlNoByJujue")
	public int updateBlNoByJujue(String bid,String platformAuditOpinion) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("bid", bid);
		map.put("platformAuditOpinion",platformAuditOpinion);
		return scAuditServiceImpl.updateBlNoByJujue(map);
	}
	/**
	 * 平台通过作废审核
	 * @param account 平台管理员账号
	 * @param blId 提单id
	 * @return 2代表成功0代表失败
	 */ 
	@RequestMapping("/zuofeiapply")
	public int zuofeiapply(String account,String blId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("blId", blId);
		return scAuditServiceImpl.zuofeiapply(map);
	}
	
/**
  * 申请作废
 * @param blId 提单id
 * @param applyCompanyName 申请人企业名
 * @param account 申请人账号
 * @param applyUserName   申请人姓名
 * @return
 */
	@RequestMapping("/zuofei")
	public int zuofei(String blId,String applyCompanyName,String account,String applyUserName,String cause) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("blId", blId);
		map.put("applyCompanyName", applyCompanyName);
		map.put("account", account);
		map.put("applyUserName", applyUserName);
		map.put("cause", cause);
		return scAuditServiceImpl.zuofei(map);
	}
}
