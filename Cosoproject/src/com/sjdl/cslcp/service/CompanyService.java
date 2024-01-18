package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 管文斌
 * @version 创建时间：2021年6月30日09:40:51
 */
public interface CompanyService {
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
	 * 注册企业信息
	 * @param map 注册企业的全部数据
	 * @return 返回int 1成功 其他失败
	 */
	public int insertCompany(Map<String, Object> map);

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
	 * 查询所有出单和签章公司
	 * @return map {出单：list}{签章：list}
	 */
	public Map<String, Object> findCompany();

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
	 * 出单方上传印章
	 * @param map 账号、文件、文件名
	 * @return	返回int 1成功 其他失败
	 */
	public int insertSendBlSeal(Map<String, Object> map);
	
	/**
	 * 出单方上传logo
	 * @param map 账号、文件、文件名
	 * @return 返回int 1成功 其他失败
	 */
	public int insertSendBlLogo(Map<String,Object> map);
	
	/**
	 * 
	 * @param map
	 * @return 返回int 1成功 其他失败
	 */
	public int updatelogobycid(Map<String,Object> map);
	
	public String findlogofilebycid(String cid);
	
	public int deleteSendBlLogo(String logofile);

}
