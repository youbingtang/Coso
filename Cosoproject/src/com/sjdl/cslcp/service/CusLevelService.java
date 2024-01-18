 package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 张泽华: 
* @version 创建时间：2021年6月29日 上午8:30:07 
*/
public interface CusLevelService {
	
	/**
	 * 根据账号查找所有客户信息
	 * @param map 前台传入的信息数据
	 * @return 返回所有的客户级别信息集合
	 */
	public List<Map<String,Object>> findAllCusLevel(Map<String,Object> map);
	
	/**
	 * 根据账号查找对应客户信息
	 * @param id 账号
	 * @return 返回客户级别相关信息
	 */
	public Map<String,Object> findById(int id);
	
	/**
	 * 根据账号修改客户信息
	 * @param map 前台传入的信息数据
	 * @return 返回影响数据的行数
	 */
	public int updateCusLevel(Map<String,Object> map);
	
	/**
	 * 根据账号删除某条客户信息
	 * @param id 账号
	 * @return 返回影响数据的行数
	 */
	public int delCusLevel(int id);
	
	/**
	 * 根据账号添加客户信息
	 * @param map 前台传入的信息数据
	 * @return 返回影响数据的行数
	 */
	public int addCusLevel(Map<String,Object> map);
}
