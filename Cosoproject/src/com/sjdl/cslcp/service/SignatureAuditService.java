package com.sjdl.cslcp.service;
import java.util.List;
import java.util.Map;
/** 
* @author 作者 曲浩焱: 
* @version 创建时间：2021年7月2日 上午8:38:20 
*/
public interface SignatureAuditService {
	/**
	 * 根据账号查找个人信息
	 * @param account 账号
	 * @return 返回用户基本信息
	 */
	public Map<String,Object> findpersoninfobyacccount(String account);
	/**
	 * 提单详情
	 * @param blNo 提单号
	 * @return 返回提单详情
	 */
	public Map<String,Object> findrecordingbyblNo(String blNo);
	/**
	 * 签章方通过提单  
	 * @param map 提单号和审核意见
	 * @return 1代表成功 0代表失败
	 */
	public int siapply(Map<String,Object> map);
	/**
	 * 签章方不通过提单  
	 * @param map 提单号和审核意见
	 * @return 1代表成功 0代表失败
	 */
	public int siunapply(Map<String,Object> map);
	/**
	 * 查询属于该公司的提单并且签章审核状态为0 
	 * @param map 签章公司和签章方审核状态
	 * @return 返回符合信息的提单信息
	 */
	public List<Map<String,Object>> findallrecording(Map<String,Object> map);
}
