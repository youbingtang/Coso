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
import com.sjdl.cslcp.service.PortService;

/**
 * @author 陈鹏
 * @version 创建时间：2021年6月29日 
 */
@RestController
public class PortController {
	@Autowired
	private PortService portServiceImpl;
	/**
	* 分页查询港口信息
	* @param currentPage 当前页码
	* @return 港口表的分页查询
	*/	
	@RequestMapping( "/portListPage" ) 
	public PageInfo<Map<String,Object>> portListPage(
		@RequestParam(defaultValue="1",value="currentPage")int currentPage
			) {
		int rowNum = 3;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> list = portServiceImpl.findAllPort();
		System.out.println(list);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return pageInfo; 
				}
				
	/**
	 * 根据港口id删除信息
	* @param id 港口id
	* @return 1 更该删除标记成功
	*/	
	@RequestMapping("/portdoDel")
	public int portdoDel(int id) {
		int result = portServiceImpl.del(id);
		return result;
		}
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
	@RequestMapping("/portEdit")
	public int portdoEdit(String name,String ename,int sendPrice,int id) {
		int result=portServiceImpl.edit(name, ename,  sendPrice, id);
		return result;
	}
	/**
	* 给港口表添加一条新数据
	* @param name 港口名称
	* @param ename 港口英文名称
	* @param countryId 所在国家id
	* @param provinceId 所在省份id
	* @param cityId 所在城市id
	* @param longitude 经度
	* @param latitude 维度
	* @param sendPrice 出单费用
	* @param createDate 创建时间
	* @return 1 添加信息成功 
	*/
	@RequestMapping("/portAdd")
	public int portdoAdd(String name,String ename,int countryId,int provinceId,int cityId,
			String longitude,String latitude,int sendPrice,String createDate) {
		Map<String,Object> m =new HashMap<String, Object>();
		m.put("createDate", createDate);
		m.put("sendPrice", sendPrice);
		m.put("name", name);
		m.put("ename", ename);
		m.put("countryId", countryId);
		m.put("provinceId", provinceId);
		m.put("cityId", cityId);
		m.put("longitude", longitude);
		m.put("latitude", latitude);
		int result = portServiceImpl.insertPort(m);
		return result;
	}
	/**
	* 国家、省份、城市三级联动查询
	* @return 将数据封装成一个list集合
	*/
	@RequestMapping( "/findCountry1" ) 
	public List<Map<String, Object>> findCountry() {
		return portServiceImpl.findCountry();
	}
	/**
	* 国家、省份、城市三级联动查询
	* @param pid2 address表中国家的pid值
	* @return 将数据封装成一个list集合
	*/
	@RequestMapping( "/findProvince2" ) 
	public List<Map<String, Object>> findProvince(int pid2) {
		return portServiceImpl.findProvince(pid2);
	}
	/**
	* 国家、省份、城市三级联动查询
	* @param pid3 address表中国家的pid值
	* @return 将数据封装成一个list集合
	*/
	@RequestMapping( "/findCity3" ) 
	public List<Map<String, Object>> findCity(int pid3) {
		return portServiceImpl.findCity(pid3);
	}
	
	/**
	* 不分页查询港口信息
	* @param currentPage 当前页码
	* @return 港口表的分页查询
	*/	
	@RequestMapping( "/portListPage2" ) 
	public List<Map<String,Object>> portListPage2() {
		List<Map<String,Object>> list = portServiceImpl.findAllPort();
		return list; 
				}
}
