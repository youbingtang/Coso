package com.sjdl.cslcp.controller;

//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sjdl.cslcp.service.LoginService;
//import com.sjdl.cslcp.service.MobileService;
//import com.sjdl.cslcp.service.ScAuditService;

/** 

* @author 作者 张鑫博: 

* @version 创建时间：2021年7月12日 上午8:35:09 

*/
//@RestController
//public class MobileController {
//	
//	@Autowired
//	private MobileService mobileServiceImpl;
//	
//	@Autowired
//	private LoginService loginServiceImpl;	
//	
//	@Autowired
//	private ScAuditService scAuditServiceImpl;
//	
//	/**
//	 * 移动端登录验证
//	 * @param account 账号
//	 * @param password 密码
//	 * @return 返回验证结果以及companyid
//	 */
//	@RequestMapping("/loginFindcompanyIdByAccount")
//	public Map<String,Object> login(String account,String password) {
//		int result = loginServiceImpl.checkPassword(account, password);
//		Map<String,Object> map = mobileServiceImpl.loginFindcompanyIdByAccount(account);
//		map.put("result", result);
//		return map;
//	}
//	
//	/**
//	 * 移动端 查询当前账户拥有的公司名称，以及提单详细
//	 * @param blNo提单号
//	 * @param companyId 公司id
//	 * @return 返回全部提单信息
//	 */
//	@RequestMapping("/findRecordingByyblNo")
//	public Map<String,Object> findRecordingByblNo(@RequestParam("blNo")String blNo,
//			@RequestParam("companyId")String companyId){
//		Map<String,Object> map = scAuditServiceImpl.findrecordingbyblNo(blNo);
//		String name = mobileServiceImpl.findNameByCompanyIdForTidan(companyId);
//		map.put("name", name);
//		return map;
//	}
//	/**
//	 * 查询该公司全部待审核提单
//	 * @param companyId 公司id
//	 * @return 返回全部未审核的提单
//	 */
//	@RequestMapping("/findSfangTidanByCompanyId")
//	public List<Map<String,Object>> findSfangTidanByCompanyId(String companyId){
//		return mobileServiceImpl.findSfangTidanByCompanyId(companyId);
//	}
//}
