package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/** 
* @author 作者 褚国庚: 
* @version 创建时间：2021年6月29日 上午8:18:49 
*/
public interface BillgoodsMapper {
	/**
	 * 根据提单id找到对应的货物信息
	 * @param blId 提单id
	 * @return 返回对应货物的相关信息
	 */
	public List<Map<String,Object>> findBillgoodsByblid(Integer blId);
	
	/**
	 * 根据条件分页查询全部货物信息
	 * @param map 信息数据
	 * @return 返回所有货物信息
	 */	
	public List<Map<String,Object>> findBillgoods(Map<String,Object> map);
	
	/**
	 * 根据提单id删除对应货物信息
	 * @param blId 提单id
	 * @return 返回影响数据的行数
	 */
	public int deleteBillgoods(Integer blId);
	
	/**
	 * 修改货物信息
	 * @param map 修改的货物信息数据
	 * @return 返回影响数据的行数
	 */
	public int updateBillgoods(Map<String,Object> map);
	
	/**
	 * 插入货物信息
	 * @param map 插入的货物信息数据
	 * @return 返回影响数据的行数
	 */
	public int insertBillgoods(Map<String,Object> map);
}
