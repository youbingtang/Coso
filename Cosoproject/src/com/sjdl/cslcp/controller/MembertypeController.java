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
import com.sjdl.cslcp.service.MembertypeService;
/**
*作者：杨兰兰
*创建时间：2021年6月30日 上午9:10:45
**/
@RestController
public class MembertypeController {
	
	@Autowired
	private MembertypeService membertypeServiceImpl;

	/**
	 * 根据条件分页查询表中数据
	 * @param currentPage 页数
	 * @param name        类型名称
	 * @param discount    会员折扣
	 * @return 返回符合查询条件的数据集合
	 */
	@RequestMapping("/findLikeMemberType")
	public PageInfo<Map<String, Object>> findLikeMemberType(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage,
			@RequestParam("name") String name, @RequestParam("discount") String discount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("discount", discount);
		map.put("name", name);
		// 设置当前每页显示5行数据
		int rowNum = 5;
		// 将当前页以及每页显示的数据行数
		PageHelper.startPage(currentPage, rowNum);
		// 查询全部数据
		List<Map<String, Object>> list = membertypeServiceImpl.findLikeMemberType(map);
		// pageInfo中包含了分页之后的数据,以及和分页有关的所有信息
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

		return pageInfo;
	}

	/**
	 * 根据id查询会员类型表中数据
	 * 
	 * @param id 编号
	 * @return 返回符合条件的数据信息
	 */
	@RequestMapping("/findMemberTypeById")
	public Map<String, Object> findMemberTypeById(int id) {
		Map<String, Object> map = membertypeServiceImpl.findMemberTypeById(id);
		
		return map;
	}

	/**
	 * 向会员类型表中插入数据
	 * 
	 * @param name     类型名称
	 * @param discount 会员折扣
	 * @return 返回0或1，1代表执行插入操作成功，0代表为成功
	 */
	@RequestMapping("/insertMemberType")
	public int insertMemberType(String name, String discount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("discount", discount);
		map.put("name", name);
		int v = membertypeServiceImpl.insertMemberType(map);
		
		return v;
	}

	/**
	 * 删除会员类型表中某条数据
	 * 
	 * @param id 编号
	 * @return 返回0或1，0代表执行删除失败，1代表成功
	 */
	@RequestMapping("/delMemberType")
	public int delMemberType(int id) {
		
		return membertypeServiceImpl.delMemberType(id);
	}

	/**
	 * 修改会员类型表中某条数据
	 * 
	 * @param id       编号
	 * @param name     类型名称
	 * @param discount 会员折扣
	 * @return 返回0或1，0代表执行修改操作失败，1代表成功
	 */
	@RequestMapping("/editMemberType")
	public int editMemberType(String id, String name, String discount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("discount", discount);
		map.put("name", name);
		map.put("id", id);
		
		return membertypeServiceImpl.editMemberType(map);
	}
}
