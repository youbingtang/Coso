package com.sjdl.cslcp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.BillgoodsMapper;
import com.sjdl.cslcp.service.BillgoodsService;

/** 
* @author 作者 褚国庚: 
* @version 创建时间：2021年6月29日 上午8:56:12 
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class BillgoodsServiceImpl implements BillgoodsService{

	@Autowired
	private BillgoodsMapper billgoodsMapper;
	
 //根据提单id查询全部信息	
	@Override
	public List<Map<String,Object>> findBillgoodsByblid(Integer blId){
		return billgoodsMapper.findBillgoodsByblid(blId);
	}
	
 //查询全部信息	
	@Override
	public List<Map<String,Object>> findBillgoods(Map<String,Object> map){
		return billgoodsMapper.findBillgoods(map);
	}
	
 //删除
	@Override
	public int deleteBillgoods(Integer blId) {
		return billgoodsMapper.deleteBillgoods(blId);
	}
	
 //修改
	@Override
	public int updateBillgoods(Map<String,Object> map) {
		return billgoodsMapper.updateBillgoods(map);
	}
	
 //增加
	@Override
	public int insertBillgoods(Map<String,Object> map) {
		return billgoodsMapper.insertBillgoods(map);
	}
}
