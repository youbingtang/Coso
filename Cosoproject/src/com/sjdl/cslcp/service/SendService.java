package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月30日 下午4:24:13 
*/

public interface SendService {
	
	/**
	 * 查找所有出单公司和签章公司
	 * @return 返回所有出单公司 sendCompanyList 和签章公司集合 signCompanyList
	 */
	public Map<String,Object> findSendAndSignCompany();
	
	/**
	 * 收单方录单时所填的信息以及录单人的账号和姓名,还插入提单文件信息表
	 * @param map 包含提单字段 和rcfAuditUserAccount 是收单方账号 也是创建人账号rcfAuditUserName是收单放姓名
	 * 				也是创建人姓名 
	 * @return 返回结果如果是2 提单号重复 返回1是插入成功
	 */
	public int insertLading(Map<String,Object> map);
	
	/**
	 * 找到所有出单公司可以申请邮寄的提单
	 * @param receiveCompanyId 参数为出单公司的企业ID
	 * @return 返回所有符合条件的提单信息
	 */
	public List<Map<String,Object>> findStatusFive(String receiveCompanyId);
	
	/**
	 * 申请邮寄 将提单状态变成6 并插入邮寄信息表 返回id 更新到提单文件信息表中该提单所对应的邮寄信息id
	 * @param map （收件人collectPerson，收件地址collectAddress，收件人电话collectPhone，提单id blId ，提单号blNo）
	 * @return 返回最后影响数据的行数
	 */
	public int applyForMailing(Map<String,Object> map);
	
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
	 * @param map 包含提单字段和修改人姓名等
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
