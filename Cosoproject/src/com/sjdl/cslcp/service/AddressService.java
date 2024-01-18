package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 徐丽艳
 * @version 创建时间：2021年6月29日 上午8:51:12
 */
public interface AddressService {
	/**
	 * 根据前台传递过来的id查找内容
	 * @param id 省份或城市的id
	 * @return 返回该id对应省份或城市的所有内容
	 */
	public Map<String,Object> findById(int id);
	
	/**
	 * 根据前台传过来的信息对数据库进行更新
	 * @param map 前台传过来的数据
	 * @return 影响数据的行数
	 */
	public int updateAddress(Map<String,Object> map);
	
	/**
	 * 根据前台传过来的id删除某条数据
	 * @param id 某省份或城市的id
	 * @return 影响数据的行数
	 */
	public int delAddress(int id);
	
	/**
	 * 根据前台传过来的信息对数据库进行添加操作
	 * @param map 前台传过来的数据
	 * @return 影响数据的行数
	 */
	public int addAddress(Map<String,Object> map);
	
	/**
	 * 查询所有省份
	 * @return 返回所有省份的集合
	 */
	public List<Map<String,Object>> findProvince();
	
	/**
	 * 根据前台传递的省份id出对应的城市
	 * @param pid 省份id
	 * @return 返回该省份对应的所有城市
	 */
	public List<Map<String,Object>> findCity(int pid);
}
