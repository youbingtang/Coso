package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 作者：张泽华
 * @version 创建时间：2021年6月29日 上午8:19:44
 */
public interface CusLevelMapper {
	
	/**
	 * 根据账号查找所有客户级别信息
	 * @param map 信息数据
	 * @return 返回所有的信息集合
	 */
	public List<Map<String,Object>> findAllCusLevel(Map<String,Object> map);
	
	/**
	 * 根据账号查找对应客户信息
	 * @param id 账号
	 * @return 返回客户相关信息
	 */
	public Map<String,Object> findById(int id);
	
	/**
	 * 根据账号修改客户信息
	 * @param map 信息数据
	 * @return 返回影响数据的行数
	 */
	public int updateCusLevel(Map<String,Object> map);
	
	/**
	 * 根据账号删除客户信息
	 * @param id 账号
	 * @return 返回影响数据的行数
	 */
	public int delCusLevel(int id);
	
	/**
	 * 根据账号添加客户信息
	 * @param map 信息数据
	 * @return 返回影响数据的行数
	 */
	public int addCusLevel(Map<String,Object> map);
}
