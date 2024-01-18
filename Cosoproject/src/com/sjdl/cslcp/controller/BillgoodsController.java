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
import com.sjdl.cslcp.service.BillgoodsService;

/** 
* @author 作者 褚国庚: 
* @version 创建时间：2021年6月29日 上午8:11:32 
*/
@RestController
public class BillgoodsController {

	@Autowired
	private BillgoodsService billgoodsServiceImpl;
	
	/**
	 * 插入货物信息
	 * @param blId 提单id
	 * @param MarksAndNos 标志和号码
	 * @param NoAndKindOfPackages 包装数量与类别
	 * @param DescriptionOfgoods 货物描述
	 * @param GrossWeight 总重量
	 * @param Measurement 货物体积或尺码
	 * @return 返回影响数据的行数
	 */
	@RequestMapping("/insertBillgoods")
	public int insertBillgoods(String blId,String MarksAndNos,String NoAndKindOfPackages,String DescriptionOfgoods,String GrossWeight,String Measurement) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blId", blId);
		map.put("MarksAndNos", MarksAndNos);
		map.put("NoAndKindOfPackages", NoAndKindOfPackages);
		map.put("DescriptionOfgoods", DescriptionOfgoods);
		map.put("GrossWeight", GrossWeight);
		map.put("Measurement", Measurement);
		int result =  billgoodsServiceImpl.insertBillgoods(map);
		return result;
	}
		
	/**
	 * 修改货物信息
	 * @param blId 提单Id
	 * @param MarksAndNos 标志和号码
	 * @param NoAndKindOfPackages 包装数量与类别
	 * @param DescriptionOfgoods 货物描述
	 * @param GrossWeight 总重量
	 * @param Measurement 货物体积或尺码
	 * @return 返回影响数据的行数
	 */
	@RequestMapping("/updateBillgoods")
	public int updateBillgoodsdo(String blId,String MarksAndNos,String NoAndKindOfPackages,String DescriptionOfgoods,String GrossWeight,String Measurement) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blId", blId);
		map.put("MarksAndNos", Measurement);
		map.put("NoAndKindOfPackages", NoAndKindOfPackages);
		map.put("DescriptionOfgoods", DescriptionOfgoods);
		map.put("GrossWeight", GrossWeight);
		map.put("Measurement", Measurement);
		int result = billgoodsServiceImpl.updateBillgoods(map);
		return result;
	}
	
	/**
	 * 根据提单id删除对应的信息
	 * @param blId 提单id
	 * @return 返回影响数据的行数
	 */
	@RequestMapping("/deleteBillgoods")
	public int deleteBillgoods(Integer blId) {
		int result = billgoodsServiceImpl.deleteBillgoods(blId);
		return result;
	}
	
	/**
	 * 根据提单id查询货物详细信息
	 * @param blId 提单id
	 * @return 返回相关货物的详细信息
	 */
	@RequestMapping("findBillgoodsByblid")
	public List<Map<String,Object>> findBillgoodsByblid(Integer blId){ 
		List<Map<String,Object>> result = billgoodsServiceImpl.findBillgoodsByblid(blId);
		return result;
	}
	
	/**
	 *  根据条件分页查询全部货物信息
	 * @param currentPage 显示页数
	 * @param blId 提单id
	 * @param MarksAndNos 标志和号码
	 * @param NoAndKindOfPackages 包装数量与类别
	 * @param DescriptionOfgoods 货物描述
	 * @param GrossWeight 总重量
	 * @param Measurement 货物体积或尺码
	 * @return 返回符合条件的货物信息
	 */
	@RequestMapping("/findBillgoods")
	public PageInfo<Map<String,Object>> findBillgoods(
			@RequestParam(required = true, defaultValue = "1", value = "currentPage") int currentPage,
			@RequestParam(defaultValue="",value="blId")String blId,
			@RequestParam(defaultValue="",value="MarksAndNos")String MarksAndNos,
			@RequestParam(defaultValue="",value="NoAndKindOfPackages")String NoAndKindOfPackages,
			@RequestParam(defaultValue="",value="DescriptionOfgoods")String DescriptionOfgoods,
			@RequestParam(defaultValue="",value="GrossWeight")String GrossWeight,
			@RequestParam(defaultValue="",value="Measurement")String Measurement){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blId", blId);
		map.put("Measurement", Measurement);
		map.put("NoAndKindOfPackages", NoAndKindOfPackages);
		map.put("DescriptionOfgoods", DescriptionOfgoods);
		map.put("GrossWeight", GrossWeight);
		map.put("Measurement", Measurement);
		int rowNum = 5;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> list = billgoodsServiceImpl.findBillgoods(map);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return pageInfo;			
		
	}
	
}