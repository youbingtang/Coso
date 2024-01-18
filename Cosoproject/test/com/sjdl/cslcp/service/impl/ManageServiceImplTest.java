package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.ManageService;

/**
 * @author 作者：管文斌
 * @version 时间：2021年7月1日13:38:12
 */
public class ManageServiceImplTest extends BaseTest {

	@Autowired
	private ManageService manageServiceImpl;

	// @Test
	public void testFindAllStatesIsTwo() {
		Assert.assertEquals(33, manageServiceImpl.findAllStatesIsTwo().size());
	}

	// @Test
	public void testPingTaiApply() {
		String blNo = "B/L-No.COSU2107010001";

		Assert.assertEquals(1, manageServiceImpl.pingTaiApply(blNo));
	}

	// @Test
	public void testPingTaiUnapply() {
		String blNo = "B/L-No.COSU2107010001";

		Assert.assertEquals(1, manageServiceImpl.pingTaiUnapply(blNo));
	}

	// @Test
	public void testFindAllstatesisfour() {
		Assert.assertEquals(56, manageServiceImpl.findAllstatesisfour().size());
	}

	// @Test
	public void testPainting() {
		String blNo = "B/L-No.COSU2107010001";

		Assert.assertEquals(1, manageServiceImpl.painting(blNo));
	}

	// @Test
	public void testFindAllstatesissix() {
		Assert.assertEquals(280, manageServiceImpl.findAllstatesissix().size());
	}

	// @Test
	public void testFindAllMallingcompany() {
		Assert.assertEquals(8, manageServiceImpl.findAllMallingcompany().size());
	}

	// @Test
	public void testMalling() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("blNoid", "4");
		map.put("recordUserAccount", "17309401554");
		map.put("orderNo", "12121212");
		map.put("expressCompanyId", 1);

		Assert.assertEquals(1, manageServiceImpl.malling(map));
	}
	
	//@Test
	public void testUpdateCompanyStatusToOne() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("cid", 3);
		map.put("account", "17309401554");
		
		Assert.assertEquals(1, manageServiceImpl.updateCompanyStatusToOne(map));
	}
	
		//@Test
		public void testUpdateCompanyStatusToTwo() {
			Map<String, Object> map = new HashMap<>();
			
			map.put("cid", 3);
			map.put("account", "17309401554");
			
			Assert.assertEquals(1, manageServiceImpl.updateCompanyStatusToTwo(map));
		}
		
		//@Test
		public void testSelectAllServiceApplay() {
			Assert.assertEquals(3, manageServiceImpl.selectAllServiceApplay().size());
		}
		
		//@Test
		public void testApplyService() {
			String cid = "3";
			
			Assert.assertEquals(1, manageServiceImpl.applyService(cid));
		}
		
		//@Test
		public void testUnapplyService() {
			String cid = "3";
			
			Assert.assertEquals(1, manageServiceImpl.unapplyService(cid));
		}
		
		//@Test
		public void testSelectCompanyLogo() {
			String id = "3";
			
			Assert.assertEquals(4, manageServiceImpl.selectCompanyLogo(id).size());
		}
		
		//@Test
				public void testSelectCompanySeal() {
					String id = "3";
					
					Assert.assertEquals(5, manageServiceImpl.selectCompanySeal(id).size());
				}
		
		//@Test
		public void testSelectAllCompanyInfo() {
			Assert.assertEquals(24, manageServiceImpl.selectAllCompanyInfo().size());
		}

}
