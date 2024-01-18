package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 陈鹏
 * @version 创建时间：2021年7月07日 
 */
public interface OSBillMapper {
	
	/**
	 * 查询注册公司总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllCompany();
	/**
	 * 查询用户总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllRole();
	/**
	 * 查询出单总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBill();
	/**
	 * 查询待审核提单总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBillScAuditStatus();
	/**
	 * 查询待付款提单总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBillFreightCharges();
	/**
	 * 查询签章总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBillSignatureCompanyId();
	/**
	 * 查询待签章核审总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBillConfirmStatus();
	/**
	 * 查询提单总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBillSendCompanyId();
	/**
	 * 查询本月提单总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findAllBillSendBillDate();
	/**
	 * 查询企业名称、申请开通服务、申请日期数据
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findCompanyAndBus();
	/**
	 * 查询企业名称、提单编号、申请日期数据
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findCompanyAndBill();
	/**
	 * 查询提单本月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillCurrent();
	/**
	 * 查询提单上月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillLastCurrent();
	/**
	 * 查询月提单1月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillOne();
	/**
	 * 查询月提单2月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillTwo();
	/**
	 * 查询月提单3月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillThree();
	/**
	 * 查询月提单4月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillFour();
	/**
	 * 查询月提单5月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillFive();
	/**
	 * 查询月提单6月总数
	 * @return 将查询的所有数据封装在一个list集合中
	 */
	public List<Map<String,Object>> findBillSix();
}
