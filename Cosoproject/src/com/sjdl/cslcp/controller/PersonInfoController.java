package com.sjdl.cslcp.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.PersonInfoService;
/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 下午3:09:45 
*/
@RestController
public class PersonInfoController {
	@Autowired
	private PersonInfoService personInfoServiceImpl;
	
	/**
	 * 更新个人信息
	 * @param account 账号
	 * @param password 密码
	 * @param repassword 确认密码
	 * @param name 姓名
	 * @param idCardNo 身份证号码
	 * @return 返回更新结果 影响数据的行数
	 */
	@RequestMapping("/updatePersonInfor")
	public int updatePersonInfor(String account,String password,String repassword,String name,String idCardNo) {
		int result = personInfoServiceImpl.checkRepassword(password, repassword);
		if(result == 1) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("account", account);
			map.put("password", password);
			map.put("repassword", repassword);
			map.put("name", name);
			map.put("idCardNo",idCardNo);
			result = personInfoServiceImpl.updatePersonInfo(map);
		}else {
			result = 0;
		}
		return result;
	}
	
	/**
	 * 根据企业信息查到所有归属于这个共司的用户信息
	 * @param currentPage 当前页数
	 * @param cid 企业ID
	 * @return 返回所有用户数据
	 */
	String name="";
	String idCardNo="";
	String account="";
	@RequestMapping("/findAllAccount")
	public PageInfo<Map<String,Object>> findAllAccount(@RequestParam(required = true,defaultValue = "1",value = "currentPage")int currentPage,String cid,String name1,String idCardNo1,String account1){
		if(name1 != null) {
			name=name1;
		}
		if(idCardNo1 != null) {
			idCardNo=idCardNo1;
		}
		if(account1 != null) {
			account=account1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", account);
		map.put("name", name);
		map.put("idCardNo", idCardNo);
		map.put("cid", cid);
		
		int rowNum = 5;
		PageHelper.startPage(currentPage, rowNum);
		List<Map<String,Object>> list = personInfoServiceImpl.findAllAccount(map);
		PageInfo<Map<String,Object>> pageinfo = new PageInfo<Map<String,Object>>(list);
		return pageinfo;
	}

	
	/**
	 * 企业管理员启用禁用用户账号 修改账号的状态
	 * @param id 用户账号的id
	 * @return 返回影像数据的行数
	 */
	@RequestMapping("/updateAccountStatus")
	public int updateAccountStatus(String id) {
		return personInfoServiceImpl.updateStatus(id);
	}
	
	/**
	 * 企业管理员添加用户
	 * @param name 姓名
	 * @param account 账号
	 * @param cid 企业 ID
	 * @param idCardNo 身份证号码
	 * @param hiredDate 入职时间
	 * @param adminId 管理员ID
	 * @param adminName 管理员姓名
	 * @return 返回影像数据的行数
	 */
	@RequestMapping("addAccount")
	public int addAccount(String name,String account,String cid,String idCardNo,String hiredDate,String adminId,String adminName,String roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("account", account);
		map.put("cid", cid);
		map.put("idCardNo",idCardNo);
		map.put("hiredDate", hiredDate);
		map.put("adminId", adminId);
		map.put("adminName", adminName);
		map.put("roleId", roleId);
		return personInfoServiceImpl.addAccount(map);
	}
	
	@RequestMapping("/delAccount")
	public int delAccount(String id) {
		return personInfoServiceImpl.delAccount(id);
	}

}
