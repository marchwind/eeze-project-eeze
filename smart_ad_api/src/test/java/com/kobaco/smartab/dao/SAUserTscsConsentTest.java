package com.kobaco.smartab.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAOperationTscs;
import com.kobaco.smartad.model.data.SAUser;
import com.kobaco.smartad.model.data.SAUserTscsConsent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/META-INF/root-context.xml"
})
public class SAUserTscsConsentTest {

	@Autowired
	CommonDao<SAUser> userDao;	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	@Autowired
	CommonDao<SAUserTscsConsent> consentDao;
	@Autowired
	CommonDao<SAOperationTscs> operationdao;
	@Autowired
	@Qualifier("props")
	private Properties props;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAUserTscsConsent consent = insertConsent();
		consent = insertConsent();
		
		try {
			List<SAUserTscsConsent> list = consentDao.list(consent);
			assertTrue(list.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAUserTscsConsent consent = insertConsent();
		
		try {
			SAUserTscsConsent consentSelected = consentDao.info(consent);
			assertTrue(consentSelected.getOPRT_TSCS_MGMT_NO()+"=="+consent.getOPRT_TSCS_MGMT_NO(), consentSelected.getOPRT_TSCS_MGMT_NO().equals(consent.getOPRT_TSCS_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testinsertConsent() {
		insertConsent();
	}
	
	@Test
	public void testUpdate() {
		SAUserTscsConsent consent = insertConsent();
		

		
		try {
			int cnt = consentDao.update(consent);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testDelete() {
		SAUserTscsConsent consent = insertConsent();
		
		try {
			int cnt = consentDao.delete(consent);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	private SAEnterprise insertEnterprise() {
		SAEnterprise ent = TestUtils.initEnterprise();
		try {
			System.out.println(ent.getENTP_NO());
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
	

	private SAOperationTscs insertOperationTscs() {			
		
		SAOperationTscs common = TestUtils.initOperationTscs();
		
		try {
			operationdao.insert(common);
			assertTrue(common.getOPRT_TSCS_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	public SAUserTscsConsent insertConsent() {
		
		SAUser user = insertUser();
		SAOperationTscs operationTscs = insertOperationTscs();
		if( user == null || operationTscs == null){
			return null;
		}
		
		SAUserTscsConsent consent = TestUtils.initTscsConsent(props.getProperty("tscs.tp.tscs"),user.getUSR_NO(),"Y");
		
		try {
			consentDao.insert(consent);
			assertTrue(consent.getOPRT_TSCS_MGMT_NO()!=null);
			return consent;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
		}
	}
}
