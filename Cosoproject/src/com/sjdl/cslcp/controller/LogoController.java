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
import com.sjdl.cslcp.service.LogoService;

/**
 * @author 陈鹏
 * @version 创建时间：2021年7月01日 
 */
@RestController
public class LogoController {
    @Autowired
    private LogoService logoService;
    
    /**
	 * 分页查询logo信息
	 * @param currentPage 当前页码
	 * @return pageInfo logo表中的分页查询
	 */	
	@RequestMapping( "/logoList" ) 
	public PageInfo<Map<String,Object>> companyListPage(
			@RequestParam(defaultValue="1",value="currentPage")int currentPage
			) {
		int rowNum = 2;
		PageHelper.startPage(currentPage,rowNum);
		List<Map<String,Object>> list = logoService.findAllLogo();
		System.out.println(list);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return pageInfo; 
	}
    
	/**
	 * 上传logo信息
	 * @param file 文件
	 * @param name logo的名称
	 * @param uploadDay 上传时间
	 * @return 上传图片的个数
	 */	
    @RequestMapping("/logoSave")
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
        int result = logoService.save(map);
        return result; 
    }
    
    /**
	  *  修改logo
	 * @param  copanyId 	公司id
	 * @param  name  		logo名称
	 * @param  file  		logo源文件
	 * @param  isEnable 	启用状态
	 * @param  confirmStatus 确认状态
	 * @param  id 			logo表id
	 * @return 1 表示对logo表中的数据修改成功
	 */	
	@RequestMapping("/logoEdit")
	public int companydoEdit(int copanyId,String name,
			String file,int isEnable,int confirmStatus,int id) {
		int result=logoService.edit(copanyId, name, file, isEnable,
				confirmStatus, id);
		return result;
	}
	/**
	 * 删除logo
	 * @param name logo的名称
	 * @return 1 logo删除成功
	 */	
	@RequestMapping("/logodoDel")
	public int companydoDel(String name) {
		int result = logoService.del(name);
		return result;
	}
	@RequestMapping("/findlogobycid")
	public Map<String,Object> findlogobycid(@RequestParam("cid") String copanyId){
		return logoService.findlogobycid(copanyId);
		
	}
}
