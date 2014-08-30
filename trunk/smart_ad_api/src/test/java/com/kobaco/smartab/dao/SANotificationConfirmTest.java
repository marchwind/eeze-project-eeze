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
import com.kobaco.smartad.model.data.SANotification;
import com.kobaco.smartad.model.data.SANotificationConfirm;
import com.kobaco.smartad.model.data.SAUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SANotificationConfirmTest {

	@Autowired
	CommonDao<SANotificationConfirm> notificationcmfDao;
	@Autowired
	CommonDao<SANotification> notificationDao;
	@Autowired
	CommonDao<SAUser> userDao;
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SANotificationConfirm noticf = insertNotiConfirm();
		noticf = insertNotiConfirm();
		
		try {
			List<SANotificationConfirm> list = notificationcmfDao.list(noticf);
			assertTrue(list.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SANotificationConfirm noticf = insertNotiConfirm();
		
		try {
			SANotificationConfirm noticfSelected = notificationcmfDao.info(noticf);
			assertTrue(noticfSelected.getNTFC_NO()+"=="+noticf.getNTFC_NO(), noticfSelected.getNTFC_NO().equals(noticf.getNTFC_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertNntc() {
		insertNotiConfirm();
	}

	@Test
	public void testUpdate() {
		SANotificationConfirm nntc = insertNotiConfirm();
			
		nntc.setUPD_ID("UP_ID");
			
		try {
			int cnt = notificationcmfDao.update(nntc);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testDelete() {	
		SANotificationConfirm nntc = insertNotiConfirm();			
		
		try {
			int cnt = notificationcmfDao.delete(nntc);
			assertTrue(cnt==1);
		} catch (Exception e) {
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
	private SANotification insertNtfc() {
		
	
		
		SANotification common = TestUtils.initNtfc();
		
		try {
			notificationDao.insert(common);
			assertTrue(common.getNTFC_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	private SANotificationConfirm insertNotiConfirm() {
		
		SANotification noti = insertNtfc();
		SAUser user = insertUser();
		
		if (noti == null || user ==null) {
			return null;
		}
		SANotificationConfirm noticf = TestUtils.initNotiConfirm(user.getUSR_NO(),noti.getNTFC_NO());
		try {
			notificationcmfDao.insert(noticf);
			assertTrue(noti.getNTFC_NO()!=null && user.getUSR_NO()!=null);
			return noticf;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
		}
	}

}
