package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/**
*作者：杨兰兰
*创建时间：2021年6月30日 上午9:10:45
**/
public interface MembertypeService {
	/**
	 * 根据id查找对应的会员
	 * @param id 编号
	 * @return 返回一条会员信息数据
	 */
	public Map<String,Object> findMemberTypeById(int id);
	
	/**
	 * 向会员类型表中插入一条会员信息数据
	 * @param map 要插入的新数据的信息
	 * @return 返回0或1判断插入操作是否执行成功，0代表未成功，1代表成功
	 */
	public int insertMemberType(Map<String,Object> map);
	
	/**
	 * 删除会员类型表中一条数据
	 * @param id 要删除数据的编号
	 * @return 返回0或1判断执行删除操作是否成功，0代表未成功，1代表成功
	 */
	public int delMemberType(int id);
	
	/**
	 * 根据id修改会员类型表中数据
	 * @param map 要修改的数据1信息
	 * @return 返回0或1判断执行修改操作是否成功，0代表未成功，1代表成功
	 */
	public int editMemberType(Map<String,Object> map);
	
	/**
	 * 根据输入条件查询会员类型表中数据，如果不输入任何条件，既是查询所有数据
	 * @param map 输入条件信息
	 * @return 返回符合查询条件的数据集合，是controll中的list数据
	 */
	public List<Map<String,Object>> findLikeMemberType(Map<String,Object> map);
	}
