package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月30日 下午4:23:23 
*/

public interface SendMapper {
	
	/**
	 * 查找所有出单公司
	 * @return 返回出单公司集合
	 */
	public List<Map<String,Object>> findReceiveCompany();
	
	/**
	 * 查出所有签章公司
	 * @return 返回所有签章公司集合
	 */
	public List<Map<String,Object>> findSignCompany();
	
	/**
	 * 收单方录单时所填的信息以及录单人的账号和姓名
	 * @param map 包含提单字段 和rcfAuditUserAccount 是收单方账号 也是创建人账号rcfAuditUserName是收单放姓名
	 * 				也是创建人姓名
	 * @return 返回影响数据的行数
	 */
	public int insertLading(Map<String,Object> map);
	
	/**
	 * 插入货物明细表
	 * @param map 待插入的货物明细数据
	 * @return 返回影响数据的行数
	 */
	public int insertBillGoods(Map<String,Object> map);
	
//	/**
//	 * 插入提单文件表 提单的id 
//	 * @param map 包含提单id
//	 * @return 返回影响数据的行数
//	 */
//	public int insertBillFile(Map<String,Object> map);
	
	/**
	 * 根据提单号查库中所有提单  提单号是否重复
	 * @param map  待查询的条件
	 * @return 返回结果如果是2 提单号重复 返回1是插入成功
	 */
	public int findBlNo(Map<String,Object> map);
	
	/**
	 * 找到所有出单公司可以申请邮寄的提单
	 * @param receiveCompanyId 参数为出单公司的企业ID
	 * @return 返回所有符合条件的提单信息
	 */
	public List<Map<String,Object>> findStatusFive(String receiveCompanyId);
	
	/**
	 * 将提单状态变成6（收单方点击申请邮寄）
	 * @param map 待更新的数据
	 * @return 返回影响数据的行数
	 */
	public int updateStatusSix(Map<String,Object> map);
	
	/**
	 * 插入邮寄信息表 收件人 收货地址 收件人电话
	 * @param map 待插入的数据
	 * @return 返回 邮寄信息表的id 插入到提单文件信息表中
	 */
	public int insertExpress(Map<String,Object> map);
	
	/**
	 * 更新提单文件信息表中的提单所对应的邮寄信息表中的id expressId
	 * @param map 待插入的数据
	 * @return 返回影响数据的行数
	 */
	public int updateBillFile(Map<String,Object> map);
	
	/**
	 * 根据提单id找到打印申请表中的发件人，发件人电话和发件人地址
	 * @param blId 有提单id
	 * @return 返回发件人，发件人电话和发件人地址
	 */
	public Map<String,Object> findApply(String blId);
	
	/**
	 * 根据收单公司id查找提单信息
	 * @param receiveCompanyId 收单公司id
	 * @return 提单所有信息
	 */
	public List<Map<String,Object>> findallrecording(String receiveCompanyId);
	
	/**
	 * 根据提单编码获取到提单所有信息
	 * @param blNo 提单编码
	 * @return 提单所有信息
	 */
	public Map<String,Object> findrecordingbyblNo(String blNo);
	
	/**
	 * 修改收单方审核状态为通过
	 * @param blNo 提单编码
	 * @return 返回0代表修改失败，1代表修改成功
	 */
	public int rcapply(String blNo);
	
	/**
	 * 修改收单方审核状态为驳回
	 * @param blNo 提单编码
	 * @return 返回0代表修改失败，1代表修改成功
	 */
	public int rcunapply(String blNo); 
	
	/**
	 * 收单方修改提单信息
	 * @param map 包含提单字段 和 修改人姓名等
	 * @return 返回影响数据的行数
	 */
	public int rcmodify(Map<String,Object> map);
	
	/**
	 * 根据收单公司Id 找到可以签收的提单
	 * @param receiveCompanyId 收单公司id
	 * @return 返回所有符合条件的提单
	 */
	public List<Map<String,Object>> findtidanbymallingid(String receiveCompanyId);
	
	/**
	 * 根据提单号更新邮寄信息表中的status 为1 收单方签收提单
	 * @param blNo 提单号
	 * @return 返回影响数据的行数
	 */
	public int ok(String blNo);
}
