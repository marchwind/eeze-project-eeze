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
import com.kobaco.smartad.model.data.SAPMCScreenManagement;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAPMCScreenManagementTest {

	@Autowired
	CommonDao<SAPMCScreenManagement> dao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {		
		SAPMCScreenManagement common = insertSManagement();
		common = insertSManagement();		
		
		try{
			List<SAPMCScreenManagement> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testInfo() {
		SAPMCScreenManagement common = insertSManagement();

		try {
			SAPMCScreenManagement commonSelect =  dao.info(common);		
			assertTrue(commonSelect.getPMC_SCRN_NO()+"=="+common.getPMC_SCRN_NO(), commonSelect.getPMC_SCRN_NO().equals(common.getPMC_SCRN_NO()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testinsertSManagement() {
		insertSManagement();
	}

	@Test
	public void testUpdate() {		
		SAPMCScreenManagement common = insertSManagement();
		common.setSCRN_NM("change common name");		
		common.setUPD_ID("Imupdate");
		try {
			int cnt = dao.update(common);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testDelete() {		
		SAPMCScreenManagement common = insertSManagement();

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}

	}
	
	private SAPMCScreenManagement insertSManagement() {		
		SAPMCScreenManagement common = TestUtils.initPMCScreenManagement();
		
		try {
			dao.insert(common);
			assertTrue(common.getPMC_SCRN_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
