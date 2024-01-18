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
import com.sjdl.cslcp.service.SignatureAuditService;
/** 
* @author 作者 曲浩焱: 
* @version 创建时间：2021年7月2日 上午8:43:18 
*/
@RestController
public class SignatureAuditController {
	@Autowired
	private SignatureAuditService signatureAuditServiceImpl;
	/**
	 * 查看单个提单详情
	 * @param blNo 提单编码
	 * @return 返回单个提单详情信息
	 */
	@RequestMapping("/findrecordingbyblNoo")
	public Map<String,Object> findrecordingbyblNoo(@RequestParam("blNo")String blNo){
		return signatureAuditServiceImpl.findrecordingbyblNo(blNo);
	}
	/**
	 * 签章方通过提单
	 * @param account 账号
	 * @param blNo 提单编码
	 * @return 返回更新结果 影响数据的行数
	 */
	@RequestMapping("/siapply")
	public int siapply(String account,@RequestParam("blNo")String blNo) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account",account);
		map.put("blNo",blNo);
		return signatureAuditServiceImpl.siapply(map);
	}
	/**
	 * 签章方不通过提单
	 * @param account 账号
	 * @param blNo 提单编码
	 * @param signatureAuditOpinion 签章方审核意见
	 * @return 返回更新结果 影响数据的行数
	 */
	@RequestMapping("/siunapply")
	public int siunapply(String account,@RequestParam("blNo")String blNo,String signatureAuditOpinion) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("account",account);
		map.put("blNo",blNo);
		map.put("signatureAuditOpinion",signatureAuditOpinion);
		return signatureAuditServiceImpl.siunapply(map);
	}
	/**
	 * 查询属于该公司的提单并且签章审核状态为0
	 * @param signatureAuditStatus 签章方审核状态
	 * @param cid 企业ID
	 * @param currentPage 当前页码
	 * @return 返回符合条件的所有数据
	 */
	@RequestMapping("/findallRecording")
	public PageInfo<Map<String,Object>> findallRecording(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
			   @RequestParam("signatureAuditStatus")Integer signatureAuditStatus,
			   @RequestParam("cid")Integer cid){
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("signatureAuditStatus", signatureAuditStatus);
		 map.put("cid", cid);
		    //设置当前页显示5行数据
		 	int rowNum = 5;		   
		    //当前页以及每业显示的数据行数
		    PageHelper.startPage(currentPage,rowNum);	   
		    //查询全部数据
		    List<Map<String, Object>> list = signatureAuditServiceImpl.findallrecording(map);		   
		    PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>(list);	   
		 return pageInfo;
	}
	/**
	 * 根据账号account查询对应用户的基本信息
	 * @param account 账号
	 * @return 返回用户的基本信息
	 */
	@RequestMapping("/findpersoninfobyacccount")
	public Map<String,Object> findpersoninfobyacccount(String account){
		return signatureAuditServiceImpl.findpersoninfobyacccount(account);
	}
}
