package com.kobaco.smartab.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.SAPMCManager;
import com.kobaco.smartad.model.data.SAPMCManager;
import com.kobaco.smartad.model.data.SAPMCPermission;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAPMCManagerTest {

	@Autowired
	CommonDao<SAPMCPermission> permissionDao;
	@Autowired
	CommonDao<SAPMCManager> managerDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {		
		SAPMCManager manager = insertManager();
		manager = insertManager();		
		
		try{
			List<SAPMCManager> managerList = managerDao.list(manager);
			assertTrue(managerList.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testInfo() {
		SAPMCManager manager = insertManager();

		try {
			SAPMCManager managerSelect =  managerDao.info(manager);		
			assertTrue(managerSelect.getMNGR_NO()+"=="+manager.getMNGR_NO(), managerSelect.getMNGR_NO().equals(manager.getMNGR_NO()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testinsertManager() {
		insertManager();
	}

	@Test
	public void testUpdate() {		
		SAPMCManager manager = insertManager();
				
		manager.setBLT("cbLT");
		manager.setCNFM_DTT(new Date());
		manager.setCNFM_YN("N");
		manager.setCNFR_ID("cNFR_ID");
		manager.setCPHN("cPHN");
		manager.setMNGR_EML("cmNGR_EML");
		manager.setMNGR_ID("mNGR_ID");
		manager.setMNGR_NM("mNGR_NM");
		manager.setPHN("cpHN");
		manager.setPST_CD("pSTCD");
		manager.setPWD("pWD");				
		manager.setUPD_ID("updaterId");
		
		try {
			int cnt = managerDao.update(manager);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}


	}

	@Test
	public void testDelete() {		
		SAPMCManager manager = insertManager();

		try {
			int cnt = managerDao.delete(manager);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}

	}
	
	private SAPMCPermission insertPermission() {		
		SAPMCPermission common = TestUtils.initPMCPermission();
		
		try {
			permissionDao.insert(common);
			assertTrue(common.getMNGR_PRMS_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	private SAPMCManager insertManager() {	
		SAPMCPermission permission =insertPermission();
		
		if ( permission == null) {
			return null;
		}
		SAPMCManager manager = TestUtils.initPMCManager(permission.getMNGR_PRMS_NO());
		
		try {
			managerDao.insert(manager);
			assertTrue(manager.getMNGR_NO()!=null);
			return manager;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
