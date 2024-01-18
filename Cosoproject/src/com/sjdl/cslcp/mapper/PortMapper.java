package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author 陈鹏
 * @version 创建时间：2021年6月29日
 */
public interface PortMapper {
	/**
	  * 分页查询与根据港口名称查询
	 * @return 港口表中的所有数据
	 */
	public List<Map<String, Object>> findAllPort();

	/**
	  * 根据港口id进行删除操作
	 * @param id 港口信息表中的id
	 * @param isDel 删除标记
	 * @return 1 表示删除标记修改成功
	 */
	public int del(@Param("id") int id);

	/**
	 * 根据港口id更改港口详细信息
	* @param name 港口名称
	* @param ename 港口英文名
	* @param countryId 所在国家id
	* @param provinceId 所在省份id
	* @param cityId 所在城市id
	* @param sendPrice 出单费用
	* @param id 港口表id
	* @return 1 表示修改港口信息表成功
	*/	
	public int edit(@Param("name")String name,
			@Param("ename")String ename,
			@Param("sendPrice")int sendPrice,
			@Param("id")int id
			);

	/**
	* 给港口表添加一条新数据
	* @param map 将 港口名称、港口英文名称、所在国家id、所在城市id、所在省份id封装到map里
	* @return 1 添加信息成功 
	*/
	public int insertPort(Map<String, Object> map);
	/**
	* 国家、省份、城市三级联动查询
	* @return 将数据封装成一个list集合
	*/
	public List<Map<String,Object>> findCountry();
	/**
	* 国家、省份、城市三级联动查询
	* @param pid2 address表中国家的pid值
	* @return 将数据封装成一个list集合
	*/
	public List<Map<String,Object>> findProvince(int pid2);
	/**
	* 国家、省份、城市三级联动查询
	* @param pid3 address表中省份的pid值
	* @return 将数据封装成一个list集合
	*/
	public List<Map<String,Object>> findCity( int pid3 );
}
