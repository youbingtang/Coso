package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
* @author 作者陈梁: 
* @version 创建时间：2021年7月1日 下午1:38:00 
*/
public interface ScAuditMapper {
	
	/**
	 * 查询该出单公司的所有提单
	 * @param sendCompanyId 出单公司id
	 * @return 所有该公司的提单集合
	 */
	public List<Map<String,Object>> findsccompanyrecording(String sendCompanyId);
	
	/**
	 * 根据账号查找个人信息
	 * @param account 账号
	 * @return 个人基本信息
	 */
	public Map<String,Object> findpersoninfobyacccount(String account);
	
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
	  * 根据出单公司id将三方状态都为1的提单的状态更新为1根据出单
	 * @param sendCompanyId 出单公司id
	 * @return 1代表成功 0代表失败
	 */
	public int update1(int sendCompanyId);
	
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
	  * 为支付做准备
	 * @param blNo 提单号
	 * @return blNo提单号 ,sendPrice价格 
	 */
	public Map<String,Object> selectSendPriceByblNo(String blNo);
	
	/**
	  * 向打印申请表中插入基本信息，返回新插入记录的id
	 * @param map 出单方需要向打印申请表插入的的必要字段
	 * @return id 返回打印申请表的id
	 */
	public int insertBillInfoByblNo(Map<String,Object> map);
	
	/**
	  * 更新提单文件表，将向打印申请表中插入记录新增的id插入提单文件表
	 * @param map 出单方需要向提单文件表插入的的必要字段
	 * @return 1代表成功 0代表失败
	 */
	public int updatebillfile(Map<String,Object> map);
	
	/**
	 * 支付后返给前台的状态
	 * @param map 包含支付的状态账号以及提单号
	 * @return 0代表成功-1代表失败
	 */
	public int updatetype(Map<String,Object> map);
	
	/**
	  * 打印申请
	  *付款后 将提单的状态更新为4
	 * @param blNo 提单号
	 * @return 1代表成功 0代表失败
	 */
	public int printingapply(String blNo);
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


