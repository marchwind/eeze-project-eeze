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
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAPMCManager;
import com.kobaco.smartad.model.data.SAPMCPermission;
import com.kobaco.smartad.model.data.SAQna;
import com.kobaco.smartad.model.data.SAUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAQnaTest {
	
	@Autowired
	CommonDao<SAQna> dao;
	@Autowired
	CommonDao<SAUser> userDao;	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	@Autowired
	CommonDao<SAPMCPermission> permissionDao;
	@Autowired
	CommonDao<SAPMCManager> managerDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAQna common = insertQna();
		common = insertQna();
		
		try {
			List<SAQna> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAQna common = insertQna();		
		
		try {
			SAQna commonSelected = dao.info(common);
			assertTrue(commonSelected.getQNA_NO()+"=="+common.getQNA_NO(), commonSelected.getQNA_NO().equals(common.getQNA_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertQna();
	}

	@Test
	public void testUpdate() {
		SAQna common = insertQna();
		common.setA_CNTT("a_CNTT");
		common.setA_DTT(new Date());		
		common.setA_SBJT("a_SBJT");
		common.setQ_ATC_FL_NM("q_ATC_FL_NM");
		common.setQ_ATC_FL_SPTH("q_ATC_FL_SPTH");
		common.setQ_CNTT("q_CNTT");
		common.setQ_DTT(new Date());
		common.setQ_SBJT("q_SBJT");		
		common.setUPD_ID("system");
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
		SAQna common = insertQna();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	private SAEnterprise insertEnterprise() {
		SAEnterprise ent = TestUtils.initEnterprise();
		try {
			enterpriseDao.insert(ent);
			assertTrue(ent.getENTP_NO()!=null);
			return ent;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}
	
	public SAUser insertUser() {
		
		SAEnterprise ent = insertEnterprise();
		
		if (ent == null) {
			return null;
		}
		SAUser common = TestUtils.initUser(ent.getENTP_NO());
		try {
			userDao.insert(common);
			assertTrue(common.getUSR_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
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
	private SAQna insertQna() {
		
		SAUser user = insertUser();
		SAPMCManager manager = insertManager();
		
		SAQna common = TestUtils.initQna(manager.getMNGR_NO(),user.getUSR_NO());
		
		try {
			dao.insert(common);
			assertTrue(common.getQNA_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
