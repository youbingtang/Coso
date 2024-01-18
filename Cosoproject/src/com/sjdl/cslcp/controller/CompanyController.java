package com.sjdl.cslcp.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.CompanyService;

/**
 * @author 编写人：管文斌 
 * @version 时间：2021年6月29日10:31:26
 */
@RestController
public class CompanyController {
	@Autowired
	private CompanyService companyServiceImpl;
	
	/**
	 * 注册之前查找省份信息
	 * @return 所有省份名称和id
	 */
	@RequestMapping("/findProvince")
	public List<Map<String, Object>> findProvince() {
		List<Map<String, Object>> list = companyServiceImpl.findProvince();
		
		return list;
	}

	/**
	 * 省份变更时查找对应的城市
	 * @param provinceId 城市id
	 * @return 该城市下的城市名称name和id
	 */
	@RequestMapping("/findCity")
	public List<Map<String, Object>> findCity(String provinceId) {
		String id = provinceId;
		List<Map<String, Object>> list = companyServiceImpl.findCity(id);
		
		return list;
	}
	
	/**
	 * 注册前查找所有公司类型
	 * @return 所有公司类型表里的名称name和id
	 */
	@RequestMapping("/findAllCompanyType")
	public List<Map<String, Object>> findAllCompanyType() {
		List<Map<String, Object>> list = companyServiceImpl.findAllCompanyType();
		
		return list;
	}

	/**
	 * 
	 * @param name 企业名称
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 * @param cerificateAttachment 组织机构代码证
	 * @param creditCode 企业信用代码
	 * @param account 管理员手机号
	 * @param email 企业邮箱 
	 * @param companyTypeId 企业类型id
	 * @return 返回注册是否成功,-1未知异常，1成功，2账号已存在
	 */
	@RequestMapping("/insertCompany")
	public int insertCompany(String name, String provinceId, String cityId, String cerificateAttachment,
			String creditCode, String account, String email, String companyTypeId) {
		Map<String, Object> parammap = new HashMap<String, Object>();
		parammap.put("name", name);
		parammap.put("provinceId", provinceId);
		parammap.put("cityId", cityId);
		parammap.put("cerificateAttachment", cerificateAttachment);
		parammap.put("creditCode", creditCode);
		parammap.put("account", account);
		parammap.put("email", email);
		parammap.put("companyTypeId", companyTypeId);
		return companyServiceImpl.insertCompany(parammap);
	}

	/**
	 * 查找所有没有审核的企业
	 * @param currentPage 要显示的页数，默认为1
	 * @return 返回pageinfo中有分页数据和未审核企业list集合
	 */
	@RequestMapping("/findAllUndifindCompany")
	public PageInfo<Map<String, Object>> findAllUndifindCompany(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage) {
		int rowNum = 5;
		
		PageHelper.startPage(currentPage, rowNum);
		List<Map<String, Object>> list = companyServiceImpl.findAllUndifindCompany();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		
		return pageInfo;
	}

	/**
	 * 平台查看单个企业的信息
	 * @param cid 企业id
	 * @return map中有要查询企业的信息
	 */
	@RequestMapping("/findAllCompanyInfor")
	public Map<String, Object> findAllCompanyInfor(String cid) {
		int id = Integer.parseInt(cid);
		Map<String, Object> map = companyServiceImpl.findAllCompanyInfor(id);
		
		return map;
	}

	/**
	 * @param name 企业名称
	 * @param companyTypeId 企业类型
	 * @param email 企业邮箱
	 * @param createUserName 管理员姓名
	 * @param account 管理员账号，用来查找对应企业
	 * @return 返回int，1修改成功，-1修改失败，其他未知异常
	 */
	@RequestMapping("/updateCompanyInfor")
	public int updateCompanyInfor(String name, String companyTypeId, String email, String createUserName,
			String account) {
		int result = -1;
		Map<String, Object> map = new HashMap<>();
		
		map.put("name", name);
		map.put("companyTypeId", companyTypeId);
		map.put("email", email);
		map.put("createUserName", createUserName);
		map.put("account", account);
		result = companyServiceImpl.updateCompanyInfor(map);
		
		return result;
	}

	/**
	 * 平台审核同意时修改状态
	 * @param id 企业id
	 * @return 返回int，1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/confim")
	public int confim(String id) {
		int result = -1;
		int sid = Integer.parseInt(id);
		
		result = companyServiceImpl.confim(sid);
		
		return result;
	}

	/**
	 * 平台审核不同意时修改状态
	 * @param id 企业id
	 * @return 返回int，1成功，-1失败，其他未知异常
	 */
	@RequestMapping("/unconfim")
	public int unconfim(String id) {
		int result = -1;
		int sid = Integer.parseInt(id);
		
		result = companyServiceImpl.unconfim(sid);
		
		return result;
	}

	/**
	 * 收单方新建录单时查找签章和出单公司
	 * @return 返回map里有两个list, 一个签章公司name、id，一个出单公司name、id
	 */
	@RequestMapping("/findCompany")
	public Map<String, Object> findCompany() {
		return companyServiceImpl.findCompany();
	}

	/**
	 * 企业申请收单服务
	 * @param account 管理员账号
	 * @return 返回int，1申请成功，-1失败，其他未知异常
	 */
	@RequestMapping("/companyapplyA")
	public int companyapplyA(String account) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("applyAccount", account);
		
		return companyServiceImpl.companyapplyA(map);
	}

	/**
	 * 企业申请签章服务
	 * @param account 管理员账号
	 * @return 返回int，1申请成功，-1失败，其他未知异常
	 */
	@RequestMapping("/companyapplyC")
	public int companyapplyC(String account) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("applyAccount", account);
		
		return companyServiceImpl.companyapplyC(map);
	}

	/**
	 * 企业申请出单服务
	 * @param account 管理员账号
	 * @return 返回int，1申请成功，-1失败，其他未知异常
	 */
	@RequestMapping("/companyapplyB")
	public int companyapplyB(String account) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("applyAccount", account);
		
		return companyServiceImpl.companyapplyB(map);
	}

	/**
	 * 签章方上传签章
	 * @param seal 签章文件
	 * @param account 登录账号
	 * @param name 签章名称
	 * @return 返回int，-1未知错误，0上传文件不存在,1成功，2上传错误
	 */
	// 签章方签章上传（上传地址待定）
	@RequestMapping("/insertSignatureSeal")
	public int insertSignatureSeal(MultipartFile file, String account, String name) {
		Map<String, Object> map = new HashMap<>();
		// -1未知错误，0上传文件不存在,1成功，2上传错误
		// 设置上传文件的保存地址
		String path = "D:\\\\upload\\signature\\";
		int flag = -1;

		// 判断上传文件是否存在
		if (file.isEmpty()) {
			flag = 0;
		} else {
			// 获取文件名称
			String originalFilename = file.getOriginalFilename();
			File filePath = new File(path);
			// 判断有没有要上传的路径，如果没有建创一个新的
			if (!filePath.exists()) {
				filePath.mkdir();
			}
			// 获取文件后缀
			String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			// 使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
			String newFileName = UUID.randomUUID() + "." + suffix;
			try {
				// 创建可能会有错误,强制添加异常
				file.transferTo(new File(path + newFileName));
				flag = 1;
				map.put("account", account);
				map.put("name", name);
				map.put("file", newFileName);
				companyServiceImpl.insertSignatureSeal(map);
			} catch (IOException e) {
				e.printStackTrace();
				flag = 2;
			}
		}
		
		return flag;
	}

	/**
	 * 出单方上传签章
	 * @param account 登录账号
	 * @param seal 印章文件
	 * @param sealname 印章名称
	 * @return 返回int，-1未知错误，0上传文件不存在,1成功，2上传错误
	 */
	// 出单上传签章和Logo（上传地址待定）
	@RequestMapping("/insertSendBlSeal")
	public int insertSendBlSealAndLogo(String account, MultipartFile file,String sealname) {
		// -1未知错误，0上传签章文件不存在,1签章成功，2上传错误
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置上传文件的保存地址
		String path = "D:\\\\upload\\signature\\";
		int flag = -1;

		// 判断上传文件是否存在
		if (file.isEmpty()) {
			flag = 0;
		} else {
			// 获取文件名称
			String sealFilename = file.getOriginalFilename();

			File filePath = new File(path);
			// 判断有没有要上传的路径，如果没有建创一个新的
			if (!filePath.exists()) {
				filePath.mkdir();
			}
			// 获取文件后缀
			String sealsuffix = sealFilename.substring(sealFilename.lastIndexOf(".") + 1);
			// 使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
			String newsealFileName = UUID.randomUUID() + "." + sealsuffix;
			try {
				// 创建可能会有错误,强制添加异常
				file.transferTo(new File(path + newsealFileName));

				flag = 1;
				map.put("account", account);
				map.put("sealname", sealname);
				map.put("seal", newsealFileName);

				companyServiceImpl.insertSendBlSeal(map);

			} catch (IOException e) {
				e.printStackTrace();
				flag = 2;
			}
		}
		
		return flag;
	}
	
	/**
	 * 出单方上传logo
	 * @param account 登录账号
	 * @param logo 文件
	 * @param logoname logo名称
	 * @return 返回int，-1未知错误，0上传文件不存在,1成功，2上传错误
	 */
	// 出单上传签章和Logo（上传地址待定）
	@RequestMapping("/insertSendBlLogo")
	public int insertSendLogo(String account, MultipartFile file,String logoname) {
		// -1未知错误，0上传签章文件不存在,1签章成功，2上传错误
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置上传文件的保存地址
		String path = "D:\\\\upload\\logo\\";
		int flag = -1;

		// 判断上传文件是否存在
		if (file.isEmpty()) {
			flag = 0;
		} else {
			// 获取文件名称
			String sealFilename = file.getOriginalFilename();

			File filePath = new File(path);
			// 判断有没有要上传的路径，如果没有建创一个新的
			if (!filePath.exists()) {
				filePath.mkdir();
			}
			// 获取文件后缀
			String sealsuffix = sealFilename.substring(sealFilename.lastIndexOf(".") + 1);
			// 使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
			String newFileName = UUID.randomUUID() + "." + sealsuffix;
			try {
				// 创建可能会有错误,强制添加异常
				file.transferTo(new File(path + newFileName));

				flag = 1;
				map.put("account", account);
				map.put("logoname", logoname);
				map.put("logo", newFileName);

				companyServiceImpl.insertSendBlLogo(map);

			} catch (IOException e) {
				e.printStackTrace();
				flag = 2;
			}
		}
		
		return flag;
	}
	
	@RequestMapping("/updateBlLogo")
	public int updateBlLogo(String account, MultipartFile file,String logoname,String cid) throws IllegalStateException, IOException {
		int flag=0;
		String path = "D:\\\\upload\\logo\\";
		String sealFilename = file.getOriginalFilename();
		String sealsuffix = sealFilename.substring(sealFilename.lastIndexOf(".") + 1);
		String newFileName = UUID.randomUUID() + "." + sealsuffix;
		//根据企业id 查询该公司logo文件
		String logofile=companyServiceImpl.findlogofilebycid(cid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account", account);
		map.put("logoname", logoname);
		map.put("logo", newFileName);
		//如果有logo文件
		if(logofile!=null) {
			new File(path + logofile).delete();
			companyServiceImpl.deleteSendBlLogo(logofile);
			file.transferTo(new File(path + newFileName));
			companyServiceImpl.insertSendBlLogo(map);
			companyServiceImpl.updatelogobycid(map);
		}
		
		return flag=1;
	}

}
