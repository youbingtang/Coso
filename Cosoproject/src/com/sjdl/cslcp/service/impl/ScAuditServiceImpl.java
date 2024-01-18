package com.sjdl.cslcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.ManageMapper;
import com.sjdl.cslcp.mapper.ScAuditMapper;
import com.sjdl.cslcp.mapper.SendMapper;
import com.sjdl.cslcp.service.ScAuditService;
import com.sjdl.cslcp.utils.Constants;
import com.sjdl.cslcp.utils.PDFAndQRProduce;


/** 
* @author 作者 陈梁: 
* @version 创建时间：2021年7月1日 下午1:41:26 
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class ScAuditServiceImpl implements ScAuditService{
	
	@Autowired 
	private ScAuditMapper scAuditMapper;
	@Autowired
	private ManageMapper manageMapper;
	@Autowired
	private SendMapper sendMapper;
	
	@Override
	public Map<String, Object> findrecordingbyblNo(String blNo) {
		return scAuditMapper.findrecordingbyblNo(blNo);
	}
	
	@Override
	public int scapply(Map<String,Object> map) {
			String blNo=(String) map.get("blNo");
			String account=(String) map.get("account");
		Map<String,Object> personinfomap=scAuditMapper.findpersoninfobyacccount(account);
			String name=(String) personinfomap.get("name");
		Map<String,Object> scmap=new HashMap<String, Object>();
			scmap.put("modifierUserAccount",account);
			scmap.put("blNo",blNo);
			scmap.put("modifierUserName",name);
			
			return scAuditMapper.scapply(scmap);
	}
	
	@Override
	public int scunapply(Map<String, Object> map) {
		String blNo=(String) map.get("blNo");
		String account=(String) map.get("account");
		String scfAuditOpinion=(String) map.get("scfAuditOpinion");
		Map<String,Object> personinfomap=scAuditMapper.findpersoninfobyacccount(account);
		String name=(String) personinfomap.get("name");
	Map<String,Object> scmap=new HashMap<String, Object>();
		scmap.put("modifierUserAccount",account);
		scmap.put("blNo",blNo);
		scmap.put("modifierUserName",name);
		scmap.put("scfAuditOpinion", scfAuditOpinion);
		
		return scAuditMapper.scunapply(scmap);
	}
	
	@Override
	public List<Map<String,Object>> findAllStatesIsOne(int sendCompanyId){
		scAuditMapper.update1(sendCompanyId);
		return scAuditMapper.findAllStatesIsOne(sendCompanyId);
	}
	
	@Override
	public int sendrecordingtopingtai(String blNo) {
		Map<String,Object> map =sendMapper.findrecordingbyblNo(blNo);
		String sendCompanyId=map.get("sendCompanyId").toString();
		String signatureCompanyId=map.get("signatureCompanyId").toString();
		String blid=map.get("id").toString();
		String sendseal="";
		String signatureseal="";
		String logo="";
		if(sendCompanyId!=null) {
			Map<String,Object> logomap=manageMapper.selectCompanyLogo(sendCompanyId);
			logo=(String) logomap.get("logofile");
			List<Map<String,Object>> list=manageMapper.selectCompanySeal(sendCompanyId);
			for(Map<String,Object> sendsealmap:list) {
				String sealType=sendsealmap.get("sealType").toString();
				System.out.println("**************************");
				System.out.println(sealType);
				System.out.println("**************************");
				if(sealType.equals("1")) {
					sendseal=sendsealmap.get("seafile").toString();
				}
			}
		}
		if(signatureCompanyId!=null) {
			List<Map<String,Object>> list=
					manageMapper.selectCompanySeal(signatureCompanyId);
			for(Map<String,Object> signaturesealmap:list) {
					signatureseal=(String) signaturesealmap.get("seafile");
			}
		}
		Constants constans=new Constants();
		String pdfoutpath=constans.pdfoutpath;
		String pdfinpath=constans.pdfinpath;
		String imagePath=constans.imagePath;
		String sealPath=constans.sealPath;
		
		String sendSealPath=sealPath+sendseal;
		String signaturePath=sealPath+signatureseal;
		String imagePath1=imagePath+logo;
		String outPath=pdfoutpath+blid+".pdf";
		
		PDFAndQRProduce pDFAndQRProduce =new PDFAndQRProduce();
		pDFAndQRProduce.paqrprod(map, pdfinpath, outPath,imagePath1,signaturePath,sendSealPath);
		return scAuditMapper.sendrecordingtopingtai(blNo);
	}
	
	@Override
	public List<Map<String, Object>> findAllstatesidthree(int sendCompanyId) {
		return scAuditMapper.findAllstatesidthree(sendCompanyId);
	}
	
	@Override
	public Map<String,Object> selectSendPriceByblNo(String blNo) {
		return scAuditMapper.selectSendPriceByblNo(blNo);
	}

	@Override
	public int updatetype(Map<String,Object> map) {
	//准备数据
		int flag=-1;
		int paystatus=(int) map.get("paystatus");
		String blNo=(String) map.get("blNo");
		String account=(String) map.get("account");
		//根据提单号把提单详情中的信息拿到
		Map<String,Object> billinfomap=scAuditMapper.findrecordingbyblNo(blNo);
		//提单id
		int id=(int) billinfomap.get("id");
		Map<String,Object> personinfomap=scAuditMapper.findpersoninfobyacccount(account);
		//申请人姓名
		String name=(String) personinfomap.get("name");
		//申请人地址
		String sendaddress=(String) personinfomap.get("sendaddress");
		Map<String,Object> paymap=scAuditMapper.selectSendPriceByblNo(blNo);
		//支付金额
		int sendPrice=(int) paymap.get("sendPrice");
		double payMoney=Double.valueOf(sendPrice);
		System.out.println(payMoney);
	//业务开始
		if(paystatus==1) {
			Map<String,Object> printmap=new HashMap<String, Object>();
			printmap.put("blId",id);
			printmap.put("applyUserAccount",account);
			printmap.put("applyUserName",name);
			printmap.put("blNo",blNo);
			printmap.put("receiverName", name);
			printmap.put("receiverPhone", account);
			printmap.put("receiverAddress",sendaddress);
			printmap.put("payMoney",payMoney);
		//操作打印申请表
		//获取打印申请表的id
		scAuditMapper.insertBillInfoByblNo(printmap);
		String billfileid=printmap.get("id").toString();
		Map<String,Object> billfilemap=new HashMap<String,Object>();
			billfilemap.put("printApplyId", billfileid);
			billfilemap.put("blId", id);
		//操作提单文件表
		scAuditMapper.updatebillfile(billfilemap);
		scAuditMapper.printingapply(blNo);
		return flag=0;
		}else{
			return flag;
		}
	}

	@Override
	public List<Map<String,Object>> findsccompanyrecording(String sendCompanyId) {
		return scAuditMapper.findsccompanyrecording(sendCompanyId);
	}

	@Override
	public int updateBillStatus(String blNO) {
		return scAuditMapper.updateBillStatus(blNO);
	}
	
	@Override
	public List<Map<String, Object>> findblNoZuoFeiByAll() {
		return scAuditMapper.findblNoZuoFeiByAll();
	}
	
	@Override
	public int updateBlNoByJujue(Map<String, Object> map) {
		int state = -1;
		if(scAuditMapper.updateBlNoByJujue(map) == 1) {
			state = 1;
		}else {
			state = 0;
		}
		return state;
	}

	@Override
	public int zuofeiapply(Map<String, Object> map) {
		return scAuditMapper.zuofeiapply(map);
	}

	@Override
	public int zuofei(Map<String, Object> map) {
		return scAuditMapper.zuofei(map);
	}

}
