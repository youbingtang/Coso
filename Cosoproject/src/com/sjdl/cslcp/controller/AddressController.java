package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjdl.cslcp.service.AddressService;

/**
 * @author 作者 徐丽艳
 * @version 创建时间：2021年6月29日 上午8:56:12
 */
@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressServiceImpl;
	
	/**
	 * 根据id查找所对应的所有信息
	 * @param id 所有省份或城市id
	 * @return 返回该id对应的所有信息
	 */
	@RequestMapping("/findAddressById")
	public Map<String,Object> findById(int id){
		Map<String, Object> map = addressServiceImpl.findById(id);
		
		return map;
	}
	
	/**
	 * 根据id更新该条数据中的内容
	 * @param id 所有省份或城市id
	 * @param pid 省份id
	 * @param name 省份或城市名称
	 * @return 返回影响数据的行数 result中存放的是更改过后该条数据中的所有内容
	 */
	@RequestMapping("/updateAddress")
	public Map<String,Object> updateAddress(String id,String pid,String name){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("pid", pid);
		param.put("name", name);
		
		int result = addressServiceImpl.updateAddress(param);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
	}
	
	/**
	 * 根据id删除某条数据
	 * @param id 国家或所有省份或城市id
	 * @return 返回影响数据的行数
	 */
	@RequestMapping("/delAddress")
	public int delAddress(int id) {
		int result = addressServiceImpl.delAddress(id);
		
		return result;
	}

	/**
	 * 添加一条新数据
	 * @param id 所添加数据的id
	 * @param pid 省份id
	 * @param name 省份或城市名称
	 * @return 返回影响数据的行数
	 */
	@RequestMapping("/addAddress")
	public int addAddress(String id,String pid,String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("name", name);
		
		int result = addressServiceImpl.addAddress(map);
		
		return result;
	}
}