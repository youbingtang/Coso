//package com.sjdl.cslcp.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.sjdl.cslcp.mapper.MobileMapper;
//import com.sjdl.cslcp.service.MobileService;
//
///** 
//
//* @author 作者 张鑫博: 
//
//* @version 创建时间：2021年7月12日 上午8:36:54 
//
//*/
//@Service
//@Transactional(rollbackFor = Exception.class)
//public class MobileServiceImpl implements MobileService{
//	
//	@Autowired
//	private MobileMapper mobileMapper;
//
//	/**
//	 * 移动端登录验证
//	 * @param account 账号
//	 * @param password 密码
//	 * @return 返回验证结果以及companyid
//	 */
//	
//	@Override
//	public Map<String, Object> loginFindcompanyIdByAccount(String account) {
//		return mobileMapper.loginFindcompanyIdByAccount(account);
//	}
//
//	/**
//	 * 移动端 查询当前账户拥有的公司名称，以及提单详细
//	 * @param blNo提单号
//	 * @param companyId 公司id
//	 * @return 返回全部提单信息
//	 */
//	@Override
//	public List<Map<String, Object>> findSfangTidanByCompanyId(String companyId) {
//		return mobileMapper.findSfangTidanByCompanyId(companyId);
//	}
//
//	/**
//	 * 查询该公司全部待审核提单
//	 * @param companyId 公司id
//	 * @return 返回全部未审核的提单
//	 */
//	@Override
//	public String findNameByCompanyIdForTidan(String companyId) {
//		return mobileMapper.findNameByCompanyIdForTidan(companyId);
//	}
//
//}
