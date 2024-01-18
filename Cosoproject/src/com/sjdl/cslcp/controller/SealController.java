package com.sjdl.cslcp.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjdl.cslcp.service.SealService;
/**
 * @author 陈鹏
 * @version 创建时间：2021年7月01日 
 */
@RestController
public class SealController {
	/**
	 * 分页查询印章信息
	 * @param currentPage 当前页码
	 * @param id 签章表的id
	 * @return 印章表的分页查询
	 */	
    @Autowired
    private SealService sealService;
    
	@RequestMapping( "/sealList" ) 
	public PageInfo<Map<String,Object>> companyListPage(
			@RequestParam(defaultValue="1",value="currentPage")int currentPage,
			@RequestParam(defaultValue="",value="id") String id
			) {
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("id", id);
		int rowNum = 2;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> list = sealService.findAllSeal(map);
		System.out.println(list);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return pageInfo; 
	}
	/**
	 * 上传印章信息
	 * @param file 文件
	 * @param name 印章的名称
	 * @param uploadDay 印章上传的日期
	 * @return 1上传成功
	 */
    @RequestMapping("/sealSave")
    public int fileUpload(MultipartFile file,String name,Date uploadDay)  {
        String filePath = "E:/upload";
        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID()+originalFilename;
        File targetFile = new File(filePath,newFileName); 
        try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        Map<String,Object> map = new HashMap<String,Object>();
        Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String sj = sdf.format(d);
        map.put("name", newFileName);
        map.put("uploadDay",sj);
        int result = sealService.save(map);
        return result; 
    }
}
