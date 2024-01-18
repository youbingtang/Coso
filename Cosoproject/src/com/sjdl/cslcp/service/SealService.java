package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/**
 * @author 陈鹏
 * @version 创建时间：2021年7月01日 
 */
public interface SealService {
	/**
	 * 上传印章信息
	 * @param map 将 name 印章的名称、uploadDay 印章上传的日期封装到map里
	 * @return 1上传成功
	 */
	public int save(Map<String,Object> map);
	
	/**
	  * 查询seal表中的数据
	 * @param map 将seal表中id封装到map里
	 * @return 返回seal印章表中的所有数据
	 */
	public List<Map<String,Object>> findAllSeal(Map<String,Object> map);

}
