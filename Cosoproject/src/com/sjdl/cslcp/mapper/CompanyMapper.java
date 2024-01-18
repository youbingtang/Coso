package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 管文斌
* @version 创建时间：2021年6月30日09:40:51 
*/
public interface CompanyMapper {
	/**
	 *	注册之前查找省份信息
	 * @return 所有省份名称和id
	 */
	public List<Map<String, Object>> findProvince();
	
	/**
	 * 省份变更时查找对应的城市
	 * @param provinceId 城市id
	 * @return 该城市下的城市名称name和id
	 */
	public List<Map<String, Object>> findCity(String provinceId);

	/**
	 * 注册前查找所有公司类型
	 * @return 所有公司类型表里的名称name和id
	 */
	public List<Map<String, Object>> findAllCompanyType();

	/**
	 * 查找表中是否有account存在
	 * @param account 账号
	 * @return 存在数量
	 */
	public Map<String,Object> selectAccountCount(String account);
	
	/**
	 * 添加用户信息
	 * @param account 管理员账号
	 * @return 返回int 1成功 其他失败
	 */
	public int insertUser(String account);

	/**
	 * 根据account查找用户id
	 * @param account 管理员账号
	 * @return	返回accountid
	 */
	public Map<String, Object> selectUserId(String account);

	/**
	 *  添加企业信息
	 * @param map 前台传的值和accountid
	 * @return	返回int 1成功 其他失败
	 */
	public int insertCompany(Map<String, Object> map);

	/**
	 * 根据accountid查找companyid
	 * @param adminAccountId 账号id
	 * @return 账号所在公司id
	 */
	public Map<String, Object> selectCompanyId(String adminAccountId);

	/**
	 * 将companyid插入用户表中
	 * @param map  更新数据和companyid
	 * @return 返回int 1成功 其他失败
	 */
	public int updateUserCompany(Map<String, Object> map);

	/**
	 * 添加角色信息
	 * @param companyId 公司id
	 * @return 返回int 1成功 其他失败
	 */
	public int insertRole(String companyId);

	/**
	 * 查找角色id
	 * @param companyId 公司id
	 * @return	返回map，角色id
	 */
	public Map<String, Object> selectroleid(String companyId);

	/**
	 * 添加角色账号对照表信息
	 * @param map 公司id和用户id
	 * @return 返回int 1成功 其他失败
	 */
	public int insertRoleAccountRef(Map<String, Object> map);
	
	/**
	 * 添加角色权限对照表信息
	 * @param map accountid和权限id
	 * @return 返回1成功
	 */
	public int insertAuthorityRoleRef(Map<String,Object> map);

	/**
	 * 平台审核通过
	 * @param id 企业id
	 * @return 返回int 1成功 其他失败
	 */
	public int confim(int id);

	/**
	 * 平台审核不通过
	 * @param id 企业id
	 * @return 返回int 1成功 其他失败
	 */
	public int unconfim(int id);

	/**
	 * 查找所有没有审核的企业
	 * @return 返回list，企业的详细信息
	 */
	public List<Map<String, Object>> findAllUndifindCompany();

	/**
	 * 查看单个企业详情
	 * @param cid 企业id
	 * @return	map，单个企业信息
	 */
	public Map<String, Object> findAllCompanyInfor(int cid);

	/**
	 * 修改企业信息
	 * @param map 企业相关信息
	 * @return	返回int 1成功 其他失败
	 */
	public int updateCompanyInfor(Map<String, Object> map);

	/**
	 * 查找出单公司
	 * @return 所有开通出单服务的公司id、name
	 */
	public List<Map<String, Object>> findCompany1();

	/**
	 * 查找签章公司
	 * @return 所有开通出单服务的公司id、name
	 */
	public List<Map<String, Object>> findCompany2();

	/**
	 * 申请人信息
	 * @param account 账号
	 * @return 申请人详细信息
	 */
	public Map<String, Object> findApplyUserInfo(String account);

	/**
	 *  开通收单服务申请
	 * @param map 收单服务相关数据
	 * @return	返回int 1成功 其他失败
	 */
	public int companyapplyA(Map<String, Object> map);

	/**
	 *  开通签章服务申请
	 * @param map 签章服务相关数据
	 * @return	返回int 1成功 其他失败
	 */
	public int companyapplyC(Map<String, Object> map);

	/**
	 *  开通出单服务申请
	 * @param map 出单服务相关数据
	 * @return	返回int 1成功 其他失败
	 */
	public int companyapplyB(Map<String, Object> map);

	/**
	 * 签章方签章上传
	 * @param map 签章的有关数据
	 * @return	返回int 1成功 其他失败
	 */
	public int insertSignatureSeal(Map<String, Object> map);

	/**
	 * 出单方签章上传
	 * @param map 签章的有关数据
	 * @return	返回int 1成功 其他失败
	 */
	public int insertSendBlSeal(Map<String, Object> map);

	/**
	 * 出单方logo上传
	 * @param map logo的有关数据
	 * @return	返回int 1成功 其他失败
	 */
	public int insertSendBlLogo(Map<String, Object> map);
	
	/**
	 * 修改logo
	 * @param map
	 * @return 返回int 1成功 其他失败
	 */
	public int updatelogobycid(Map<String,Object> map);
	
	public String findlogofilebycid(String cid);
	
	public int deleteSendBlLogo(String logofile);
}
