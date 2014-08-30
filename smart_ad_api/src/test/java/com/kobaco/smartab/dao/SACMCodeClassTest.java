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
import com.kobaco.smartad.model.data.SACMCodeClass;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SACMCodeClassTest {

	@Autowired
	CommonDao<SACMCodeClass> cmccDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SACMCodeClass cmcc = insertCMCodeClass();
		cmcc = insertCMCodeClass();
		
		try {
			List<SACMCodeClass> entList = cmccDao.list(cmcc);
			assertTrue(entList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SACMCodeClass cmcc = insertCMCodeClass();		
		
		try {
			SACMCodeClass cmccSelected = cmccDao.info(cmcc);
			assertTrue(cmccSelected.getCMCD_CLS()+"=="+cmcc.getCMCD_CLS(), cmccSelected.getCMCD_CLS().equals(cmcc.getCMCD_CLS()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertCMCodeClass();
	}

	@Test
	public void testUpdate() {
		SACMCodeClass cmcc = insertCMCodeClass();
			
		cmcc.setCMCD_CLS_EXPL("change exp");
		cmcc.setCMCD_CLS_NM("changeName");		
		cmcc.setUPD_ID("updaterId");			

		try {
			int cnt =cmccDao.update(cmcc);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}

	}

	@Test
	public void testDelete() {
		SACMCodeClass cmcc = insertCMCodeClass();		

		try {
			int cnt = cmccDao.delete(cmcc);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}

	}
	
	private SACMCodeClass insertCMCodeClass() {
		SACMCodeClass cmcc = TestUtils.initCMCodeClass();
		
		try {
			cmccDao.insert(cmcc);
			assertTrue(cmcc.getCMCD_CLS()!=null);
			return cmcc;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
