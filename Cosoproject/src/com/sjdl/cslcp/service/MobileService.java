package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/** 

* @author 作者 张鑫博: 

* @version 创建时间：2021年7月12日 上午8:36:21 

*/
public interface MobileService {
	/**
	 * 移动端登录验证
	 * @param account 账号
	 * @param password 密码
	 * @return 返回验证结果以及companyid
	 */
	public Map<String,Object> loginFindcompanyIdByAccount(String account);
	/**
	 * 移动端 查询当前账户拥有的公司名称，以及提单详细
	 * @param blNo提单号
	 * @param companyId 公司id
	 * @return 返回全部提单信息
	 */
	public List<Map<String,Object>> findSfangTidanByCompanyId(String companyId);
	/**
	 * 查询该公司全部待审核提单
	 * @param companyId 公司id
	 * @return 返回全部未审核的提单
	 */
	public String findNameByCompanyIdForTidan(String companyId);
}
