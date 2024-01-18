package com.sjdl.cslcp.service;
import java.util.List;
import java.util.Map;
/** 
* @author 作者 刘军利: 
* @version 创建时间：2021年6月29日 下午1:34:41 
*/
public interface PersonInfoService {
	/**
	 * 判断修改密码时  两次输入的密码是否一致
	 * @param password 密 码
	 * @param repassword 确认密码
	 * @return 返回状态 0 是两次输入不一致 1 是两次密码都一致
	 */
	public int checkRepassword(String password,String repassword);
	/**
	 * 更新个人信息
	 * @param map 个人需要修改的信息数据
	 * @return 返回影像数据的行数
	 */
	public int updatePersonInfo(Map<String,Object> map);
	/**
	 * 根据企业id查到所有归属这个企业的用户
	 * @param cid 企业id
	 * @return 返回所有符合条件的数据集合
	 */
	public List<Map<String,Object>> findAllAccount(Map<String,Object> map);
	/**
	 * 企业管理员启用禁用账号更改status状态位
	 * @param id 账号id
	 * @return 返回影像数据的行数
	 */
	public int updateStatus(String id);
	/**
	 * 企业管理员添加用户
	 * @param map 添加用户的信息 账号 ，姓名，入职时间等
	 * @return 返回影像数据的行数
	 */
	public int addAccount(Map<String,Object> map);
	
	/**
	 * 删除账号
	 * @param id
	 * @return
	 */
	public int delAccount(String id);
}
