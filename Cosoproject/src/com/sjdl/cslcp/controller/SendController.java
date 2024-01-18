package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.SendService;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月30日 下午4:22:27 
*/

@RestController
public class SendController {
	
	@Autowired
	private SendService sendServiceImpl;
	
	/**
	 * 查询所有出单公司和签章公司集合
	 * @return 返回所有出单公司和签章公司集合 map中 出单公司 sendCompanyList 和签章公司集合 signCompanyList
	 */
	@RequestMapping("/findSendAndSignCompany")
	public Map<String,Object> findSendAndSignCompany(){
		return sendServiceImpl.findSendAndSignCompany();
	}
	
	/**
	 * 收单方录单时所填的信息以及录单人的账号和姓名,还插入提单文件信息表
	 * @param map 包含提单字段 和rcfAuditUserAccount 是收单方账号 也是创建人账号rcfAuditUserName是收单放姓名
	 * 				也是创建人姓名 
	 * @return 返回结果如果是2 提单号重复 返回1是插入成功
	 */
	@RequestMapping("/insertLading")
	public int insertLading(@RequestParam Map<String,Object> map) {
		return sendServiceImpl.insertLading(map);
	}
	
	/**
	 * 找到所有出单公司可以申请邮寄的提单
	 * @param currentPage 当前页码
	 * @param receiveCompanyId 参数为出单公司的企业ID
	 * @return 返回所有符合条件的提单信息
	 */
	@RequestMapping("/findAllStatesisFive")
	public PageInfo<Map<String,Object>> findAllStatesisFive(@RequestParam(required = true,defaultValue = "1",value = "currentPage")int currentPage,String receiveCompanyId){
		int rowNum = 5;
		PageHelper.startPage(currentPage, rowNum);
		List<Map<String,Object>> list = sendServiceImpl.findStatusFive(receiveCompanyId);
		PageInfo<Map<String,Object>> pageinfo = new PageInfo<Map<String,Object>>(list);
		return pageinfo;
	}
	
	/**
	 * 申请邮寄 将提单状态变成6 并插入邮寄信息表 返回id 更新到提单文件信息表中该提单所对应的邮寄信息id
	 * @param collectPerson 收件人collectPerson，
	 * @param collectAddress 收件地址collectAddress，
	 * @param collectPhone 收件人电话collectPhone，
	 * @param blId 提单id  ，
	 * @param blNo 提单号
	 * @return 返回最后影响数据的行数
	 */
	@RequestMapping("/applyForMailing")
	public int applyForMailing(String collectPerson,String collectAddress,String collectPhone,String blId,String blNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("collectPerson", collectPerson);
		map.put("collectAddress", collectAddress);
		map.put("collectPhone", collectPhone);
		map.put("blId", blId);
		map.put("blNo", blNo);
		return sendServiceImpl.applyForMailing(map);
	}
	
	/**
	 * 根据收单公司id查询提单信息
	 * @param receiveCompanyId 收单公司id
	 * @return 返回提单全部信息
	 */
	@RequestMapping("/findallrecording")
	public PageInfo<Map<String,Object>> findallrecording(@RequestParam(required = true,defaultValue = "1",value = "currentPage")int currentPage,String receiveCompanyId){

		//设置当前每页显示行数
		int rowNum = 5;
		//当前页和每页显示行数
		PageHelper.startPage(currentPage, rowNum);
		//查询所有数据
		List<Map<String,Object>> list = (List<Map<String, Object>>) sendServiceImpl.findallrecording(receiveCompanyId);
		//pageInfo返回所有数据以及和分页有关的数据
		PageInfo<Map<String,Object>> pageinfo = new PageInfo<Map<String,Object>>(list);
		return pageinfo;
	}
		
	/**
	* 根据提单编码查询提单表中提单全部信息
	* @param blNo 提单编码
	* @return 返回提单的全部信息
	*/
	@RequestMapping("/findrecordingbyblNo")
	public Map<String,Object> findrecordingbyblNo(String blNo){
		return sendServiceImpl.findrecordingbyblNo(blNo);
	}
		
	/**
	* 修改提单种收单审核状态为通过
	* @param blNo 提单编码
	* @return 返回0代表修改失败，1代表修改成功
	*/
	@RequestMapping("/rcapply")
	public int rcapply(String blNo) {
		return sendServiceImpl.rcapply(blNo);
	}
		
	/**
	* 修改提单种收单审核状态为通过
	* @param blNo 提单编码
	* @return 返回0代表修改失败，1代表修改成功
	*/
	@RequestMapping("/rcunapply")
	public int rcunapply(String blNo) {
		return sendServiceImpl.rcunapply(blNo);
	}
	
	/**
	 * 收单方修改提单信息
	 * @param id 提单id
	 * @param shipper 托运人
	 * @param consignee 收货人
	 * @param notifyParty 被通知人
	 * @param placeOfreceipt 接货地点
	 * @param vessel 船名
	 * @param portOfLoading 装货港
	 * @param portOfTransshipment 中转港
	 * @param portOfDischarge 卸货港
	 * @param placeOfDelivery 目的港
	 * @param freightCharges 运费支付
	 * @param noOfOriginal 提单签发的份数
	 * @param declared 申报交个
	 * @param voyageNo 航次编号
	 * @param preCarriageBy 前段运输
	 * @param placeAndDate 签发地点与日期
	 * @param totalNumber 总件数
	 * @param finalDestination 最终目的港
	 * @param fordelivery 交货申请
	 * @param typeOfMovement 运输方式
	 * @param modifierUserAccount 更新人账号
	 * @param modifierUserName 更新人姓名
	 * @param modifyTime 更新人事件
	 * @return 返回影响数据的行数		
	 */
	@RequestMapping("/rcmodify")
	public int rcmodify(String id, String shipper, String consignee, 
			String notifyParty, String placeOfreceipt,
			  String vessel,String portOfLoading,
			  String portOfTransshipment,String portOfDischarge , 
			  String placeOfDelivery, String freightCharges, 
			  String noOfOriginal, String declared, 
			  String voyageNo, String preCarriageBy, 
			  String placeAndDate, String totalNumber, 
			  String finalDestination,String fordelivery, 
			  String typeOfMovement, String modifierUserAccount, 
			  String modifierUserName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("shipper", shipper);
		param.put("consignee", consignee);
		param.put("notifyParty", notifyParty);
		param.put("placeOfreceipt", placeOfreceipt);
		param.put("vessel", vessel);
		param.put("portOfLoading", portOfLoading);
		param.put("portOfTransshipment", portOfTransshipment);
		param.put("PortOfDischarge", portOfDischarge);
		param.put("PlaceOfDelivery", placeOfDelivery);
		param.put("freightCharges", freightCharges);
		param.put("NoOfOriginal", noOfOriginal);
		param.put("declared", declared);
		param.put("voyageNo", voyageNo);
		param.put("preCarriageBy", preCarriageBy);
		param.put("placeAndDate", placeAndDate);
		param.put("totalNumber", totalNumber);
		param.put("finalDestination", finalDestination);
		param.put("fordelivery", fordelivery);
		param.put("typeOfMovement", typeOfMovement);
		param.put("modifierUserAccount", modifierUserAccount);
		param.put("modifierUserName", modifierUserName);
		int result = sendServiceImpl.rcmodify(param);	
		return result;
	}
		
	/**
	 * 根据收单公司Id 找到可以签收的提单
	 * @param currentPage 当前页数
	 * @param receiveCompanyId 收单公司id
	 * @return 返回所有符合条件的提单
	 */
	@RequestMapping("/findtidanbymallingid")
	public PageInfo<Map<String,Object>> findtidanbymallingid(@RequestParam(required = true,defaultValue = "1",value = "currentPage")int currentPage,String receiveCompanyId) {
		int rowNum = 5;
		PageHelper.startPage(currentPage, rowNum);
		List<Map<String,Object>> list= sendServiceImpl.findtidanbymallingid(receiveCompanyId);
		PageInfo<Map<String,Object>> pageinfo = new PageInfo<Map<String,Object>>(list);
		return pageinfo;
	}
		
	/**
	 * 根据提单号更新邮寄信息表中的status 为1 收单方签收提单
	 * @param blNo 提单号
	 * @return 返回影响数据的行数
	 */
	@RequestMapping("/ok")
	public int ok(String blNo) {	
		int result = sendServiceImpl.ok(blNo);
		return result;
	}
}
