package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 作者：管文斌
 * @version 创建时间：2021年6月30日15:52:30
 */
public interface ManageMapper {
	
	public String selectblida(String blNo);
	public int updatepaintapply(String blId);
	
	/**
	 * 禁用企业
	 * @param cid 企业id
	 * @return 1成功
	 */
	public int delCompany(String cid);
	
	/**
	 * 禁用用户 
	 * @param cid 公司id
	 * @return 1成功
	 */
	public int delUser(String cid);
	
	/**
	 * logo审核通过
	 * @param cid 公司id
	 * @return 1成功
	 */
	public int updateLogoApply(String cid);
	
	/**
	 * logo审核不通过
	 * @param cid 公司id
	 * @return 1成功
	 */
	public int updateLogoUnapply(String cid);
	
	/**
	 * 查询所有待审核logo
	 * @return logo信息
	 */
	public List<Map<String,Object>> selectUnDefindLogo();
	
	/**
	 * 查找所有企业
	 * @return 所有企业信息
	 */
	public List<Map<String,Object>> selectAllCompanyInfo();
	
	/**
	 * 查看logo
	 * @param id 公司id
	 * @return 公司logo
	 */
	public Map<String,Object> selectCompanyLogo(String id);
	
	/**
	 * 查看seal
	 * @param id 公司id
	 * @return 公司印章
	 */
	public List<Map<String,Object>> selectCompanySeal(String id);
	
	/**
	 * 平台通过服务申请通过修改印章状态
	 * @param cid 公司id
	 * @return 返回1成功，其他失败
	 */
	public int updateSealStatusByCid(String cid);
	
	/**
	 * 平台通过服务申请通过修改公司状态
	 * @param cid 公司id
	 * @return 返回1成功，其他失败
	 */
	public int updateCompanyIsSendBl(String cid);
	
	/**
	 * 平台通过服务申请通过修改公司状态
	 * @param cid 公司id
	 * @return 返回1成功，其他失败
	 */
	public int updateCompanyIsReceive(String cid);
	
	/**
	 * 平台通过服务申请通过修改公司状态
	 * @param cid 公司id
	 * @return 返回1成功，其他失败
	 */
	public int updateCompanyIsSignature(String cid);
	
	/**
	 * 平台通过服务申请通过修改申请表状态 
	 * @param cid 公司id
	 * @return 返回1成功，其他失败
	 */
	public int updateBusApplyStatusByCid(String cid);
	
	/**
	 * 根据公司id查找公司管理员角色id
	 * @param cid 公司id
	 * @return 返回角色id
	 */
	public String selectRoleidByCid(String cid);
	
	/**
	 * 平台通过服务申请添加角色对照权限
	 * @param map 角色id和权限id
	 * @return 1成功
	 */
	public int insertPermissions(Map<String,Object> map);
	
	/**
	 * 查看申请类型
	 * @param cid 公司id
	 * @return 返回类型
	 */
	public int selectApplyTypeByCid(String cid);
	
	/**
	 * 平台不同意服务申请
	 * @param cid 公司id
	 * @return 返回int
	 */
	public int unapplyService(String cid);
	
	/**
	 * 查询所有申请服务的公司信息
	 * @return 返回公司信息
	 */
	public List<Map<String,Object>> selectAllServiceApplay();
	
	/**
	 * 平台通过企业审核
	 * @param map 修改人信息
	 * @return 返回int 1成功 其他失败
	 */
	public int updateCompanyStatusToOne(Map<String,Object> map);
	
	/**
	 * 平台不通过企业审核
	 * @param map 修改人信息
	 * @return 返回int 1成功 其他失败
	 */
	public int updateCompanyStatusToTwo(Map<String,Object> map);
	
	/**
	 * 查询所有出单申请的提单（平台的）提单状态为2
	 * @return 返回List提单详细信息
	 */
	public List<Map<String, Object>> findAllStatesIsTwo();

	/**
	 * 平台通过申请（提单状态为变为3）
	 * @param blNo 单号
	 * @return 返回int 1成功 其他失败
	 */
	public int pingTaiApply(String blNo);

	/**
	 * 通过申请之后查询所有企业信息备用
	 * @param id 企业id
	 * @return 企业信息和其商品信息
	 */
	public Map<String, Object> selectAllInfomationById(String id);

	/**
	 * 根据提单号更改正本名称
	 * @param map 提单号和正本名称
	 * @return 返回 1成功 其他失败
	 */
	public int updateOriginal(Map<String, Object> map);

	/**
	 * 根据blNo查询提单id
	 * @param blNo 单号
	 * @return 返回提单id
	 */
	public Map<String, Object> selectBlNoId(String blNo);

	/**
	 * 插入提单文件表数据
	 * @param map 插入的数据
	 * @return 返回int 1成功 其他失败
	 */
	public int insertBillOfFile(Map<String, Object> map);

	/**
	 * 平台不通过（置空所有状态位）
	 * @param blNo 单号
	 * @return 返回int 1成功 其他失败
	 */
	public int pingTaiUnapply(String blNo);

	/**
	 * 找到可以打印的订单，提单状态为4（平台）
	 * @return 返回所有符合的提单信息
	 */
	public List<Map<String, Object>> findAllstatesisfour();

	/**
	 * 平台点击打印以后状态位由4变成5
	 * @param blNo 单号
	 * @return 返回int 1成功 其他失败
	 */
	public int painting(String blNo);

	/**
	 * 平台显示所有邮寄申请的提单(状态位为6）
	 * @return 返回所有符合的提单信息
	 */
	public List<Map<String, Object>> findAllstatesissix();

	/**
	 * 查找所有邮寄公司信息备用
	 * @return 返回邮寄公司name，id
	 */
	public List<Map<String, Object>> findAllMallingcompany();

	/**
	 * 根据blNoid查找邮寄信息id
	 * @param blNoid 提单id
	 * @return 返回邮寄信息id
	 */
	public Map<String, Object> selectExpressId(String blNoid);

	/**
	 * 根据account查找account的name
	 * @param account 账号
	 * @return 返回账号主人真实姓名
	 */
	public Map<String, Object> selectAccountName(String account);

	/**
	 * 邮寄通过选择快递公司开始邮寄
	 * @param map 所需的字段
	 * @return 返回int 1成功 其他失败
	 */
	public int malling(Map<String, Object> map);
	
	/**
	 * 修改状态为8
	 * @param blid 提单id
	 * @return 1成功
	 */
	public int updatebltoeight(String blNoid);

}
