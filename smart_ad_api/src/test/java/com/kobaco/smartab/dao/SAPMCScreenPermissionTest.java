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
import com.kobaco.smartad.model.data.SAPMCPermission;
import com.kobaco.smartad.model.data.SAPMCScreenManagement;
import com.kobaco.smartad.model.data.SAPMCScreenPermission;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAPMCScreenPermissionTest {

	@Autowired
	CommonDao<SAPMCScreenPermission> scanpermissionDao;
	@Autowired
	CommonDao<SAPMCPermission> permissionDao;	
	@Autowired
	CommonDao<SAPMCScreenManagement> smanageDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {		
		SAPMCScreenPermission scanPermission = insertScanPermission();
		scanPermission = insertScanPermission();		
		
		try{
			List<SAPMCScreenPermission> scanPermissionList = scanpermissionDao.list(scanPermission);
			assertTrue(scanPermissionList.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testInfo() {
		SAPMCScreenPermission scanPermission = insertScanPermission();

		try {
			SAPMCScreenPermission scanPermissionSelect =  scanpermissionDao.info(scanPermission);		
			assertTrue(scanPermissionSelect.getPMC_SCRN_NO()+"=="+scanPermission.getPMC_SCRN_NO(), scanPermissionSelect.getPMC_SCRN_NO().equals(scanPermission.getPMC_SCRN_NO()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testInsertScanPermission() {
		insertScanPermission();
	}

	@Test
	public void testUpdate() {		
		SAPMCScreenPermission scanPermission = insertScanPermission();

		try {
			int cnt = scanpermissionDao.update(scanPermission);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}


	}

	@Test
	public void testDelete() {		
		SAPMCScreenPermission scanPermission = insertScanPermission();

		try {
			int cnt = scanpermissionDao.delete(scanPermission);
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
	private SAPMCScreenManagement insertSManagement() {		
		SAPMCScreenManagement common = TestUtils.initPMCScreenManagement();
		
		try {
			smanageDao.insert(common);
			assertTrue(common.getPMC_SCRN_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	private SAPMCScreenPermission insertScanPermission() {	
		
		SAPMCScreenManagement sMnagement = insertSManagement();
		SAPMCPermission permission =insertPermission();
		
		if( sMnagement == null || permission == null){
			return null;
		}		
		
		SAPMCScreenPermission scanPermission = TestUtils.initPMCScreenPermission(sMnagement.getPMC_SCRN_NO(), permission.getMNGR_PRMS_NO());
		
		try {
			scanpermissionDao.insert(scanPermission);
			assertTrue(scanPermission.getPMC_SCRN_NO()!=null);
			return scanPermission;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
