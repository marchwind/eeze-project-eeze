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
import com.kobaco.smartad.model.data.SAUserStateHistory;
import com.kobaco.smartad.model.data.SAUserStateHistory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/META-INF/root-context.xml"
})
public class SAUserStateHistoryTest {

	@Autowired
	CommonDao<SAUser> userDao;	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	@Autowired
	CommonDao<SAUserStateHistory> historyDao;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAUserStateHistory history = insertHistory();
		history = insertHistory();
		
		try {
			List<SAUserStateHistory> list = historyDao.list(history);
			assertTrue(list.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAUserStateHistory history = insertHistory();
		
		try {
			SAUserStateHistory historySelected = historyDao.info(history);
			assertTrue(historySelected.getHIST_SEQ()+"=="+history.getHIST_SEQ(), historySelected.getHIST_SEQ().equals(history.getHIST_SEQ()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testinsertHistory() {
		insertHistory();
	}
	
	@Test
	public void testUpdate() {
		SAUserStateHistory history = insertHistory();
		
		history.setMDF_CNTT("cmDF_CNTT");
		history.setMDF_TP_CD("cCD");			
		history.setUPD_ID("cId");	
		
		try {
			int cnt = historyDao.update(history);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testDelete() {
		SAUserStateHistory history = insertHistory();
		
		try {
			int cnt = historyDao.delete(history);
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
	
	
	public SAUserStateHistory insertHistory() {
		
		SAUser user = insertUser();
		
		if( user == null){
			return null;
		}
		
		SAUserStateHistory history = TestUtils.initStateHistory(user.getUSR_NO());
		
		try {
			historyDao.insert(history);
			assertTrue(history.getHIST_SEQ()!=null);
			return history;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");	
			return null;
		}
	}
	
	
	
}
