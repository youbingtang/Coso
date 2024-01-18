package com.sjdl.cslcp.mapper;

import java.util.List;
import java.util.Map;

/**
*作者：杨兰兰
*创建时间：2021年6月30日 上午9:10:45
**/
public interface MembertypeMapper {
	/**
	 * 根据id查找对应的会员信息
	 * @param id 编号
	 * @return 返回指定id对应的会员信息
	 */
	public Map<String,Object> findMemberTypeById(int id);
	
	/**
	 * 向会员类型表中插入一条数据
	 * @param map 要插入的会员信息
	 * @return 返回0或1判断执行插入操作是否成功
	 */
	public int insertMemberType(Map<String,Object> map);
	
	/**
	 * 根据id删除会员类型表中数据
	 * @param id 编号
	 * @return 返回0或1判断执行删除操作是否成功
	 */
	public int delMemberType(int id);
	
	/**
	 * 修改会员类型表中数据
	 * @param map 要修改的数据信息
	 * @return 返回0或1判断执行修改操作是否成功
	 */
	public int editMemberType(Map<String,Object> map);
	
	/**
	 * 根据条件模糊查询会员类型表中数据，不输入任何条件既是查询表中所有信息
	 * @param map 要查询条件信息
	 * @return 返回根据条件查询到的数据集合
	 */
	public List<Map<String,Object>> findLikeMemberType(Map<String,Object> map);
	
}
