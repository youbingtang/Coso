package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjdl.cslcp.mapper.ManageMapper;
import com.sjdl.cslcp.mapper.SendMapper;
import com.sjdl.cslcp.service.ManageService;
import com.sjdl.cslcp.utils.Constants;
import com.sjdl.cslcp.utils.PDFAndQRProduce;

/**
 * @author 作者：管文斌
 * @version 创建时间：2021年6月30日15:52:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ManageServiceImpl implements ManageService {

	@Autowired
	private ManageMapper manageMapper;
	@Autowired
	private SendMapper sendMapper;

	// 查询所有出单申请的提单（平台的）提单状态为2
	@Override
	public List<Map<String, Object>> findAllStatesIsTwo() {
		return manageMapper.findAllStatesIsTwo();
	}

	// 平台通过申请（提单状态为变为3）
	@Override
	public int pingTaiApply(String blNo) {
		Map<String, Object> mmap = new HashMap<String, Object>();
		Map<String, Object> originalMap = new HashMap<>();
		String str = "0123456789";
		String fileNo = "";
		Random r = new Random();

		// 根据参数，生成N位随机数
		for (int i = 0; i < 6; i++) {
			// 根据str的长度作为随机数的范围，获取的随机数作为索引下标到
			// str中获取对应的字符。
			fileNo = fileNo + str.charAt(r.nextInt(str.length()));
		}
		Map<String, Object> mapblId = manageMapper.selectBlNoId(blNo);
		String blId = mapblId.get("id").toString();
		
		
		
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
		String pdfName = UUID.randomUUID() + ".pdf";
		String outPath=pdfoutpath+pdfName;
		
		PDFAndQRProduce pDFAndQRProduce =new PDFAndQRProduce();
		pDFAndQRProduce.paqrprod(map, pdfinpath, outPath,imagePath1,signaturePath,sendSealPath);
		
		
		
		originalMap.put("original", pdfName);
		originalMap.put("blNo", blNo);
		manageMapper.updateOriginal(originalMap);
		mmap.put("blId", blId);
		mmap.put("fileNo", fileNo);
		manageMapper.insertBillOfFile(mmap);

		return manageMapper.pingTaiApply(blNo);
	}

	// 平台不通过（置空所有状态位）
	@Override
	public int pingTaiUnapply(String blNo) {
		return manageMapper.pingTaiUnapply(blNo);
	}

	// 找到可以打印的订单，提单状态为4（平台）
	@Override
	public List<Map<String, Object>> findAllstatesisfour() {
		return manageMapper.findAllstatesisfour();
	}

	// 台点击打印以后状态位由4变成5
	@Override
	public int painting(String blNo) {
		String blid = manageMapper.selectblida(blNo).toString();
		manageMapper.updatepaintapply(blid);
		return manageMapper.painting(blNo);
	}

	// 平台显示所有邮寄申请的提单(状态位为6）
	@Override
	public List<Map<String, Object>> findAllstatesissix() {
		return manageMapper.findAllstatesissix();
	}

	// 查找所有邮寄公司信息备用
	@Override
	public List<Map<String, Object>> findAllMallingcompany() {
		return manageMapper.findAllMallingcompany();
	}

	// 邮寄通过选择快递公司开始邮寄
	@Override
	public int malling(Map<String, Object> map) {
		int result = -1;

		String blNoid = map.get("blNoid").toString();
		manageMapper.updatebltoeight(blNoid);
		Map<String, Object> expressIdmap = manageMapper.selectExpressId(blNoid);
		map.put("id", expressIdmap.get("expressId"));
		String recordUserAccount = map.get("recordUserAccount").toString();
		Map<String, Object> accountmap = manageMapper.selectAccountName(recordUserAccount);
		map.put("recordUserName", accountmap.get("name"));
		result = manageMapper.malling(map);

		return result;
	}

	@Override
	public int updateCompanyStatusToOne(Map<String, Object> map) {
		String account = map.get("account").toString();
		
		Map<String,Object> mmap = 
		manageMapper.selectAccountName(account);
		map.put("modifyUserId", mmap.get("id"));
		map.put("modifyUserName", mmap.get("name"));
		
		return manageMapper.updateCompanyStatusToOne(map);
	}

	@Override
	public int updateCompanyStatusToTwo(Map<String, Object> map) {
		String account = map.get("account").toString();
		
		Map<String,Object> mmap = 
		manageMapper.selectAccountName(account);
		map.put("modifyUserId", mmap.get("id"));
		map.put("modifyUserName", mmap.get("name"));
		
		return manageMapper.updateCompanyStatusToTwo(map);
	}

	@Override
	public List<Map<String, Object>> selectAllServiceApplay() {
		return manageMapper.selectAllServiceApplay();
	}

	@Override
	public int applyService(String cid) {
		Map<String,Object> map = new HashMap<>();
		
		String roleid=manageMapper.selectRoleidByCid(cid);
		int type = manageMapper.selectApplyTypeByCid(cid);
		map.put("roleId", roleid);
		
		manageMapper.updateSealStatusByCid(cid);
		if(type == 1) {
			int[] list= {5,19,20,21,22};
			manageMapper.updateCompanyIsSendBl(cid);
			for(int i=0;i<list.length;i++) {
				map.put("authorityId", list[i]);
				manageMapper.insertPermissions(map);
			}
		}else if(type == 0 ) {
			int[] list= {7,24,25,26,27,28};
			manageMapper.updateCompanyIsReceive(cid);
			for(int i=0;i<list.length;i++) {
				map.put("authorityId", list[i]);
				manageMapper.insertPermissions(map);
			}
		}else if(type == 2) {
			int[] list= {6,23};
			manageMapper.updateCompanyIsSignature(cid);
			for(int i=0;i<list.length;i++) {
				map.put("authorityId", list[i]);
				manageMapper.insertPermissions(map);
			}
		}
		
		return manageMapper.updateBusApplyStatusByCid(cid);
	}

	@Override
	public int unapplyService(String cid) {
		int result = -1;
		
		result = manageMapper.unapplyService(cid);
		
		return result;
	}

	@Override
	public Map<String, Object> selectCompanyLogo(String id) {
		return manageMapper.selectCompanyLogo(id);
	}
	
	@Override
	public List<Map<String,Object>> selectCompanySeal(String id) {
		return manageMapper.selectCompanySeal(id);
	}

	@Override
	public List<Map<String, Object>> selectAllCompanyInfo() {
		return manageMapper.selectAllCompanyInfo();
	}

	@Override
	public List<Map<String, Object>> selectUnDefindLogo() {
		return manageMapper.selectUnDefindLogo();
	}

	@Override
	public int updateLogoApply(String cid) {
		int result = -1;
		
		result =  manageMapper.updateLogoApply(cid);
		
		return result;
	}

	@Override
	public int updateLogoUnapply(String cid) {
		int result = -1;
	
		result =  manageMapper.updateLogoUnapply(cid);

		return result;
	}

	@Override
	public int delUserAndCompany(String cid) {
		int result = -1;
		
		manageMapper.delCompany(cid);
		result = manageMapper.delUser(cid);
		return result;
	}

}
