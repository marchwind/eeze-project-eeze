package com.kobaco.smartab.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.SASoftware;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SASoftwareTest {
	
	@Autowired
	CommonDao<SASoftware> dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SASoftware common = insertSotfware();
		common = insertSotfware();
		
		try {
			List<SASoftware> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SASoftware common = insertSotfware();		
		
		try {
			SASoftware commonSelected = dao.info(common);
			assertTrue(commonSelected.getSW_MGMT_NO()+"=="+common.getSW_MGMT_NO(), commonSelected.getSW_MGMT_NO().equals(common.getSW_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertSotfware();
	}

	@Test
	public void testUpdate() {
		SASoftware common = insertSotfware();
		common.	setSW_EXPL("common.sW_EXPL");
		common.setSW_MDL_NM("common.sW_MDL_NM");
		common.setSW_MDL_NM("common.sW_MDL_NM");
		common.setSW_NM("common.sW_NM");
		common.setSW_PRCS_NM("common.sW_PRCS_NM");
		common.setSW_TP_CD("cCD");
		common.setUPD_ID("uid");
	
		try {
			int cnt =dao.update(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}

	}

	@Test
	public void testDelete() {
		SASoftware common = insertSotfware();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	

	private SASoftware insertSotfware() {
		
		SASoftware common = TestUtils.initSoftware();
		
		try {
			dao.insert(common);
			assertTrue(common.getSW_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
