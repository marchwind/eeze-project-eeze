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
import com.kobaco.smartad.model.data.SANfcTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SANfcTagTest {
	
	@Autowired
	CommonDao<SANfcTag> dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SANfcTag common = insertTag();
		common = insertTag();
		
		try {
			List<SANfcTag> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SANfcTag common = insertTag();		
		
		try {
			SANfcTag commonSelected = dao.info(common);
			assertTrue(commonSelected.getNFC_TAG_ID()+"=="+common.getNFC_TAG_ID(), commonSelected.getNFC_TAG_ID().equals(common.getNFC_TAG_ID()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertTag();
	}

	@Test
	public void testUpdate() {
		SANfcTag common = insertTag();
		
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
		SANfcTag common = insertTag();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	private SANfcTag insertTag() {
		
		SANfcTag common = TestUtils.initNfcTag();
		
		try {
			dao.insert(common);
			assertTrue(common.getNFC_TAG_ID()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
