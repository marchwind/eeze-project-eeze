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
import com.kobaco.smartad.model.data.SANotification;
import com.kobaco.smartad.model.data.SANotification;
import com.kobaco.smartad.model.data.SANotification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SANotificationTest {
	
	@Autowired
	CommonDao<SANotification> dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SANotification common = insertNtfc();
		common = insertNtfc();
		
		try {
			List<SANotification> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SANotification common = insertNtfc();		
		
		try {
			SANotification commonSelected = dao.info(common);
			assertTrue(commonSelected.getNTFC_NO()+"=="+common.getNTFC_NO(), commonSelected.getNTFC_NO().equals(common.getNTFC_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertNtfc();
	}

	@Test
	public void testUpdate() {
		SANotification common = insertNtfc();
	
		common.setNTFC_CNTT("cntt");
		common.setNTFC_SBJT("sbjt");					
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
		SANotification common = insertNtfc();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	

	private SANotification insertNtfc() {
		
		SANotification common = TestUtils.initNtfc();
		
		try {
			dao.insert(common);
			assertTrue(common.getNTFC_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
