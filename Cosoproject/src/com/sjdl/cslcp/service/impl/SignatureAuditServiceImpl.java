package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.SignatureAuditMapper;
import com.sjdl.cslcp.service.SignatureAuditService;

/** 
* @author 作者 曲浩焱: 
* @version 创建时间：2021年7月2日 上午8:39:32 
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class SignatureAuditServiceImpl implements SignatureAuditService{
	@Autowired
	private SignatureAuditMapper signatureAuditMapper;
	@Override
	public Map<String, Object> findrecordingbyblNo(String blNo) {
		return signatureAuditMapper.findrecordingbyblNo(blNo);
	}
	@Override
	public int siapply(Map<String, Object> map) {
		String blNo=(String) map.get("blNo");
		String account=(String) map.get("account");
	Map<String,Object> personinfomap=signatureAuditMapper.findpersoninfobyacccount(account);
		String name=(String) personinfomap.get("name");
	Map<String,Object> scmap=new HashMap<String, Object>();
		scmap.put("modifierUserAccount",account);
		scmap.put("blNo",blNo);
		scmap.put("modifierUserName",name);
		return signatureAuditMapper.siapply(scmap);
	}
	@Override
	public int siunapply(Map<String, Object> map) {
		String blNo=(String) map.get("blNo");
		String account=(String) map.get("account");
		String signatureAuditOpinion=(String) map.get("signatureAuditOpinion");
		Map<String,Object> personinfomap=signatureAuditMapper.findpersoninfobyacccount(account);
		String name=(String) personinfomap.get("name");
	Map<String,Object> scmap=new HashMap<String, Object>();
		scmap.put("modifierUserAccount",account);
		scmap.put("blNo",blNo);
		scmap.put("modifierUserName",name);
		scmap.put("signatureAuditOpinion", signatureAuditOpinion);
		return signatureAuditMapper.siunapply(scmap);
	}
	@Override
	public List<Map<String, Object>> findallrecording(Map<String,Object> map) {
		return signatureAuditMapper.findallrecording(map);
	}
	@Override
	public Map<String, Object> findpersoninfobyacccount(String account) {
		return signatureAuditMapper.findpersoninfobyacccount(account);
	}
	
}
