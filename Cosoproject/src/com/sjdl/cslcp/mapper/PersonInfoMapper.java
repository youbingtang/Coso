package com.sjdl.cslcp.mapper;
import java.util.List;
import java.util.Map;
/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 下午1:43:11 
*/
public interface PersonInfoMapper {
	/**
	 * 更新个人信息
	 * @param map 待更新的数据
	 * @return 返回影响的数据行数
	 */
	public int updatePersonInfo(Map<String,Object> map);
	/**
	 * 根据企业ID查到所有归属这个企业的用户信息
	 * @param cid 企业id
	 * @return 归属该企业的用户信息
	 */
	public List<Map<String,Object>> findAllAccount(Map<String,Object> map);
	/**
	 * 根据前台传过来的id去启用或禁用帐号状态
	 * @param id 账号id
	 * @return 返回影响的数据行数
	 */
	public int updateStatus(String id);
	/**
	 * 添加账号
	 * @param map 待插入的数据
	 * @return 返回影响的数据行数
	 */
	public int addAccount(Map<String,Object> map);
	/**
	 * 添加账号时判断账号是否已存在
	 * @param map 查询条件
	 * @return 0没有符合条件的账号信息，大于1有符合条件的账号信息
	 */
	public int checkAccount(Map<String,Object> map);
	
	/**
	* 添加账号时判断身份证号码是否已存在
	 * @param map 查询条件
	 * @return 0没有符合条件的账号信息，大于1有符合条件的账号信息
	 */
	public int checkIdCardNo(Map<String,Object> map);
	
	/**
	 * 删除账号
	 * @param id
	 * @return
	 */
	public int delAccount(String id);
	
	/**
	 * 插入角色账号关联表
	 * @param map
	 * @return
	 */
	public int insertRoleAccount(Map<String,Object> map);

}
