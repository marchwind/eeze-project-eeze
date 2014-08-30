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
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/META-INF/root-context.xml"
})
public class SAUserTest {

	@Autowired
	CommonDao<SAUser> dao;	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAUser common = insertUser();
		common = insertUser();
		
		try {
			List<SAUser> list = dao.list(common);
			assertTrue(list.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAUser common = insertUser();
		
		try {
			SAUser commonSelected = dao.info(common);
			assertTrue(commonSelected.getUSR_NO()+"=="+common.getUSR_NO(), commonSelected.getUSR_NO().equals(common.getUSR_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testinsertUser() {
		insertUser();
	}
	
	@Test
	public void testUpdate() {
		SAUser common = insertUser();
		
		common.setCPHN("ccPHN");
		common.setJOB_CD("cOBCD");
		common.setPHN("pHNc");
		common.setPST_CD("CD");
		common.setUPD_ID("cuPDID");
		common.setUSR_EML("_EML");
		common.setUSR_ID("cuSRD");
		common.setUSR_NM("cuSR_");
		common.setUSR_PWD("cuSR_D");
		common.setUSR_STTS_CD("cSCD");
		
		try {
			int cnt = dao.update(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testDelete() {
		SAUser common = insertUser();
		
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
			dao.insert(common);
			assertTrue(common.getUSR_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
		}
	}
}
