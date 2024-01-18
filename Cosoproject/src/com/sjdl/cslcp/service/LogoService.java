package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author 陈鹏
 * @version 创建时间：2021年7月01日 
 */
public interface LogoService {
	
	/**
	  * 上传logo
	 * @param map 将上传的logo名称和上传日期封装到map里
	 * @return 1 logo上传成功
	 */
	public int save(Map<String,Object> map);
	
	/**
	  * 查询logo表中的数据
	 * @return 返回logo表中的所有数据
	 */
    public List<Map<String,Object>> findAllLogo();
    
    /**
	 * 修改logo
	 * @param copanyId:公司id
	 * @param name:logo名称
	 * @param file:logo源文件
	 * @param isEnable:启用状态
	 * @param confirmStatus:确认状态
	 * @param id:表id
	 * @return 1 logo信息修改成功
	 */
  	public int edit(@Param("copanyId")int copanyId,
  				@Param("name")String name,
  				@Param("file")String file,
  				@Param("isEnable")int isEnable,
  				@Param("confirmStatus")int confirmStatus,
  				@Param("id")int id
  				);
  	/**
	 * 删除logo
	 * @param name:logo名称
	 * @return 1 logo删除成功
	 */
	public int del(String name);
	public Map<String,Object> findlogobycid(String copanyId);
}
