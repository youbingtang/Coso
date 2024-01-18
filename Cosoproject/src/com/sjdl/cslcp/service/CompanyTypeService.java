package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author 作者 陶富春
 * @version 创建时间：2021年7月5日 上午8:45：50
 */
public interface CompanyTypeService {
	/**
	 * 根据前台传递的name查询内容
	 * @param name 公司类型名称
	 * @return 返回该name对应的公司类型信息
	 */
	public List<Map<String, Object>> finAllCompanyList(@Param("name") String name);

	/**
	 * 根据前台传递的信息对数据库进行添加操作
	 * @param map 前台返回的数据
	 * @return 返回添加的公司类型信息
	 */
	public int insertCompany(Map<String, Object> map);

	/**
	 * 根据前台传入的id删除数据
	 * @param id 公司类型id
	 * @return 返回相关信息
	 */
	public int delCompany(int id);

	/**
	 * 根据前台传递的信息对数据库进行更新修改
	 * @param map 前台传递的数据
	 * @return 返回修改后的公司类型信息
	 */
	public int updateCompany(Map<String, Object> map);

}
