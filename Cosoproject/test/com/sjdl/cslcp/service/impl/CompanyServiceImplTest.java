package com.sjdl.cslcp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjdl.cslcp.service.CompanyService;

/**
 * @author 作者 管文斌:
 * @version 创建时间：2021年6月30日10:00:47
 */
public class CompanyServiceImplTest extends BaseTest {
	@Autowired
	private CompanyService companyServiceImpl;

	// @Test
	public void testFindProvince() {
		Assert.assertEquals(33, companyServiceImpl.findProvince().size());
	}

	// @Test
	public void testFindCity() {
		String id = "10";

		Assert.assertEquals(14, companyServiceImpl.findCity(id).size());
	}

	// @Test
	public void testFindAllCompanyType() {
		Assert.assertEquals(0, companyServiceImpl.findAllCompanyType().size());
	}

	// @Test
	public void testInsertCompany() {
		Map<String, Object> parammap = new HashMap<String, Object>();

		parammap.put("name", "有限责任公司");
		parammap.put("provinceId", 1);
		parammap.put("cityId", 1);
		parammap.put("cerificateAttachment", "32423553346dgd");
		parammap.put("creditCode", "1312321421");
		parammap.put("account", "1212121212");
		parammap.put("email", "fjadslf@fls.com");
		parammap.put("companyTypeld", 1);

		Assert.assertEquals(1, companyServiceImpl.insertCompany(parammap));
	}

	// @Test
	public void testFindAllUndifindCompany() {
		Assert.assertEquals(9, companyServiceImpl.findAllUndifindCompany().size());
	}

	// @Test
	public void testFindAllCompanyInfor() {
		int cid = 1;
		Assert.assertEquals(21, companyServiceImpl.findAllCompanyInfor(cid).size());
	}

	// @Test
	public void testUpdateCompanyInfor() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "企业七月七");
		map.put("companyTypeId", 2);
		map.put("email", "email");
		map.put("createUserName", "李四");
		map.put("account", "122232222");
		Assert.assertEquals(1, companyServiceImpl.updateCompanyInfor(map));
	}

	// @Test
	public void testFindCompany() {
		Assert.assertEquals(2, companyServiceImpl.findCompany());
	}

	// @Test
	public void testConfim() {
		int id = 4;

		Assert.assertEquals(1, companyServiceImpl.confim(id));
	}

	// @Test
	public void testUnconfim() {
		int id = 4;

		Assert.assertEquals(1, companyServiceImpl.unconfim(id));
	}

	// @Test
	public void testCompanyapplyA() {
		String account = "122232222";
		Map<String, Object> map = new HashMap<>();

		map.put("applyAccount", account);

		Assert.assertEquals(1, companyServiceImpl.companyapplyA(map));
	}

	// @Test
	public void testCompanyapplyC() {
		String account = "122232222";
		Map<String, Object> map = new HashMap<>();

		map.put("applyAccount", account);

		Assert.assertEquals(1, companyServiceImpl.companyapplyC(map));
	}

	// @Test
	public void testCompanyapplyB() {
		String account = "122232222";
		Map<String, Object> map = new HashMap<>();

		map.put("applyAccount", account);

		Assert.assertEquals(1, companyServiceImpl.companyapplyB(map));
	}

	// @Test
	public void testInsertSignatureSeal() {
		Map<String, Object> map = new HashMap<>();

		map.put("account", "122232222");
		map.put("name", "name");
		map.put("file", " newFileName");

		Assert.assertEquals(1, companyServiceImpl.insertSignatureSeal(map));
	}

	// @Test
	public void testInsertSendBlSeal() {
		Map<String, Object> map = new HashMap<>();

		map.put("account", "122232222");
		map.put("sealname", "sealname");
		map.put("seal", "seal");

		Assert.assertEquals(1, companyServiceImpl.insertSendBlSeal(map));
	}
	
	// @Test
		public void testInsertSendBlLogo() {
			Map<String, Object> map = new HashMap<>();

			map.put("account", "122232222");
			map.put("logoname", "logoname");
			map.put("logo", "logo");

			Assert.assertEquals(1, companyServiceImpl.insertSendBlSeal(map));
		}
}
