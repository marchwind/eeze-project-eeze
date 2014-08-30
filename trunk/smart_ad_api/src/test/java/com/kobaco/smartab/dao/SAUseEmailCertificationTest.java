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
import com.kobaco.smartad.model.data.SAUserEmailCertification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/META-INF/root-context.xml"
})
public class SAUseEmailCertificationTest {

	@Autowired
	CommonDao<SAUser> userDao;	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	@Autowired
	CommonDao<SAUserEmailCertification> useremailcrtfDao;	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAUserEmailCertification useremailCrtf = insertUserEmailCrtf();
		useremailCrtf = insertUserEmailCrtf();
		
		try {
			List<SAUserEmailCertification> list = useremailcrtfDao.list(useremailCrtf);
			assertTrue(list.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAUserEmailCertification useremailCrtf = insertUserEmailCrtf();
		
		try {
			SAUserEmailCertification usermailcrtfSelected = useremailcrtfDao.info(useremailCrtf);
			assertTrue(usermailcrtfSelected.getEML_CRTK()+"=="+useremailCrtf.getEML_CRTK(), usermailcrtfSelected.getEML_CRTK().equals(useremailCrtf.getEML_CRTK()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testInsertUserEmailCrtf() {
		insertUserEmailCrtf();
	}
	
	@Test
	public void testUpdate() {
		SAUserEmailCertification useremailCrtf = insertUserEmailCrtf();
		

		
		try {
			int cnt = useremailcrtfDao.update(useremailCrtf);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testDelete() {
		SAUserEmailCertification useremailCrtf = insertUserEmailCrtf();
		
		try {
			int cnt = useremailcrtfDao.delete(useremailCrtf);
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
		SAUser user = TestUtils.initUser(ent.getENTP_NO());
		try {
			userDao.insert(user);
			assertTrue(user.getENTP_NO()!=null);
			return user;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
		}
	}
	
	
	public SAUserEmailCertification insertUserEmailCrtf() {
	
		SAUser user = insertUser();
		
		if( user == null){
			return null;
		}
		
		SAUserEmailCertification useremailCrtf = TestUtils.initUserEmailCrtf(user.getUSR_NO());
		
		try {
			useremailcrtfDao.insert(useremailCrtf);
			assertTrue(useremailCrtf.getEML_CRTK()!=null);
			return useremailCrtf;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
		}
	}
	
}
