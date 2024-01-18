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
import com.sjdl.cslcp.service.CusLevelService;

/**
 * @author 作者：张泽华
 * @version 创建时间：2021年6月29日 上午8:49:11
 */
@RestController
public class CusLevelController {
	
	@Autowired
	private CusLevelService cusLevelServiceImpl;
	
	/**
	 * 分页查询所有客户级别信息
	 * @param currentPage 分页
	 * @param name 级别名称
	 * @param low 消费下限
	 * @param hi 消费上限
	 * @param createDate 创建时间
	 * @param createUserId 创建人账号ID
	 * @param createUserName 创建人姓名
	 * @param modifyDate 修改时间
	 * @param modifyUserId 修改人账号ID
	 * @param modifyUserName 修改人姓名
	 * @return pageInfo 返回分页查询后所有客户级别信息的集合
	 */
	@RequestMapping( "/findAllCusLevel" ) 
	public PageInfo<Map<String,Object>> findAllCusLevel(
			@RequestParam(defaultValue="1",value="currentPage")int currentPage,
			@RequestParam(defaultValue="",value="name") String name,
			@RequestParam(defaultValue="",value="low") String low,
			@RequestParam(defaultValue="",value="hi") String hi,
			@RequestParam(defaultValue="",value="createDate") String createDate,
			@RequestParam(defaultValue="",value="createUserId") String createUserId,
			@RequestParam(defaultValue="",value="createUserName") String createUserName,
			@RequestParam(defaultValue="",value="modifyDate") String modifyDate,
			@RequestParam(defaultValue="",value="modifyUserId") String modifyUserId,
			@RequestParam(defaultValue="",value="modifyUserName") String modifyUserName
			) {
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("name", name);
	    map.put("low", low);
	    map.put("hi", hi);
	    map.put("createDate", createDate); 
	    map.put("createUserId", createUserId);
	    map.put("createUserName", createUserName); 
	    map.put("modifyDate", modifyDate);
	    map.put("modifyUserId", modifyUserId); 
	    map.put("modifyUserName",modifyUserName); 
		  
	    //设置当前每页显示5行数据 
	    int rowNum = 5;	  
	    //将当前页以及每页显示的数据行数 
	    PageHelper.startPage(currentPage,rowNum);	  
	    //查询全部数据 
	    List<Map<String,Object>> list = cusLevelServiceImpl.findAllCusLevel(map);	  
	    //pageInfo中包含了分页之后的数据，以及和分页有关的所有的信息 
	    PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
	    
	    return pageInfo; 
	}

	/**
	 * 根据id查询对应的客户级别信息
	 * @param id 客户id
	 * @return map 返回所有的信息集合
	 */
	@RequestMapping("/findById")
	public Map<String, Object> FindById(int id) {
		Map<String, Object> map = cusLevelServiceImpl.findById(id);
		
		return map;
	}
	
	/**
	 * 根据id修改对应的客户信息数据
	 * @param id 客户id
	 * @param name 级别名称
	 * @param createDate 创建时间
	 * @param createUserId 创建人账号ID
	 * @param createUserName 创建人姓名
	 * @param modifyDate 修改时间
	 * @param modifyUserId 修改人账号ID
	 * @param modifyUserName 修改人姓名
	 * @param isDel 0表示未修改 1表示已修改
	 * @return map 返回影响数据的行数，result中存放的是修改后该条数据中的所有内容
	 */
	@RequestMapping("/updateCusLevel")
	public Map<String, Object> updateCusLevel(String id, String name, String createDate, String createUserId, 
			String createUserName, String modifyDate,String modifyUserId,String modifyUserName,String isDel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("name", name);
		param.put("createDate", createDate);
		param.put("createUserId", createUserId);
		param.put("createUserName", createUserName);
		param.put("modifyDate", modifyDate);
		param.put("modifyUserId", modifyUserId);
		param.put("modifyUserName", modifyUserName);
		param.put("isDel", isDel);
		int result = cusLevelServiceImpl.updateCusLevel(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
	}
	
	/**
	 * 根据id删除对应的客户信息数据
	 * @param id 客户id
	 * @return result 返回客户信息数据;isdel中0代表未删除,1代表已删除
	 */
	@RequestMapping("/delCusLevel")
	public int  delCusLevel(int id) {
		int result = cusLevelServiceImpl.delCusLevel(id);
		
		return result;
	}

	/**
	 * 根据id添加对应的客户级别信息数据
	 * @param id 客户id
	 * @param name 级别名称
	 * @param low 消费下限
	 * @param hi 消费上限
	 * @param createDate 创建时间
	 * @param createUserId 创建人账号ID
	 * @param createUserName 创建人姓名
	 * @param modifyDate 修改时间
	 * @param modifyUserId 修改人账号ID
	 * @param modifyUserName 修改人姓名
	 * @param isDel 0代表未修改 1代表已修改
	 * @return result 返回影响数据的行数
	 */
	@RequestMapping("/addCusLevel")
	public int addCusLevel(String id,String name,String low,String hi ,String createDate, String createUserId, String createUserName,
			String modifyDate,String modifyUserId,String modifyUserName,String isDel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("low", low);
		map.put("hi", hi);
		map.put("createDate", createDate);
		map.put("createUserId", createUserId);
		map.put("createUserName", createUserName);
		map.put("modifyDate", modifyDate);
		map.put("modifyUserId", modifyUserId);
		map.put("modifyUserName", modifyUserName);              
		map.put("isDel", isDel);
		int result = cusLevelServiceImpl.addCusLevel(map);
		
		return result;
	}
}