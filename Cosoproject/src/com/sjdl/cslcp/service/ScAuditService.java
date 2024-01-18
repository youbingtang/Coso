package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年7月1日 下午1:40:32 
*/
public interface ScAuditService {
	
	/**
	 * 查询该出单公司下所有提单
	 * @param sendCompanyId 出单公司id
	 * @return 出单公司对应的所有提单
	 */
	public List<Map<String,Object>> findsccompanyrecording(String sendCompanyId);
	
	/**
	 * 提单详情
	 * @param blNo 提单号
	 * @return 单个提单详情
	 */
	public Map<String,Object> findrecordingbyblNo(String blNo);
	
	/**
	 * 出单方审核通过
	 * @param map 提单号和账号
	 * @return 1代表成功 0代表失败
	 */
	public int scapply(Map<String,Object> map);
	
	/**
	 * 出单方审核不通过
	 * @param map 提单号和审核意见
	 * @return 1代表成功 0代表失败
	 */
	public int scunapply(Map<String,Object> map);
	
	/**
	 * 查询出本公司所有可以出单申请的提单
	 * @param sendCompanyId 出单公司id
	 * @return 1代表成功 0代表失败
	 */
	public List<Map<String,Object>>findAllStatesIsOne(int sendCompanyId);
	
	/**
	 * 根据提单号将提单状态为1的变为2，在出单方点击申请出单的时候
	 * @param blNo 提单号
	 * @return 1代表成功0代表失败
	 */
	public int sendrecordingtopingtai(String blNo);
	
	/**
	 * 查询所有本公司提单状态为3的提单代表可以打印申请
	 * @param sendCompanyId 出单公司id
	 * @return type 1代表一次打印，2代表二次打印
	 */
	public List<Map<String,Object>> findAllstatesidthree(int sendCompanyId);
	
	/**
	 * 根据提单号查询对应订单的发货港价格
	 * @param blNo 提单号
	 * @return 提单编号,价格
	 */
	public Map<String,Object> selectSendPriceByblNo(String blNo);
	
	/**
	 * 支付后返给前台的状态
	 * @param map 支付状态,账号,提单号
	 * @return 0代表成功 -1代表失败
	 */
	public int updatetype(Map<String,Object> map);
	/**
	 * 修改订单提单的状态
	 * @param blNO 提单状态
	 * @return 1成功
	 */
	public int updateBillStatus(@Param("blNO") String blNO);
	
	public List<Map<String,Object>> findblNoZuoFeiByAll();
	
	public int updateBlNoByJujue(Map<String,Object> map);
	
	public int zuofeiapply(Map<String,Object> map);
	
	public int zuofei(Map<String,Object> map);

}
