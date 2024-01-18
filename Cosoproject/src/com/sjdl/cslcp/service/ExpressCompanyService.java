package com.sjdl.cslcp.service;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 赵莹:
 * @version 创建时间：2021年6月30日 上午8:18:49
 */
public interface ExpressCompanyService {
	/**
	 * 直接查找所有的公司快递信息
	 * @param map 存在的所有快递公司信息的id,名字，创建日期，创建用户id，创建用户名，修改日期，修改用户id，修改用户名
	 * @return 返回快递公司相关信息
	 */
	public List<Map<String, Object>> findAllExpressCompany(Map<String, Object> map);

	/**
	 * 根据id查找快递公司信息
	 * @param id 快递公司id
	 * @return 返回快递公司信息
	 */
	public Map<String, Object> findById(int id);

	/**
	 * 对快递信息内容进行编辑
	 * @param map 编辑的所有快递公司信息的id,名字，创建日期，创建用户id，创建用户名，修改日期，修改用户id，修改用户名
	 * @return 返回修改后的信息
	 */
	public int edit(Map<String, Object> map);

	/**
	 * 删除公司的快递信息
	 * @param id 删除的快递公司id
	 * @return 返回删除的信息的条数
	 */
	public int del(int id);

	/**
	 * 插入某个快递信息
	 * @param map 添加的所有快递公司信息的id,名字，创建日期，创建用户id，创建用户名，修改日期，修改用户id，修改用户名
	 * @return 返回添加成功
	 */
	public int insertExpressCompany(Map<String, Object> map);
}
