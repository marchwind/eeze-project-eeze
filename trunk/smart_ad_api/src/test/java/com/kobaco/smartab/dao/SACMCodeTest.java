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
import com.kobaco.smartad.model.data.SACMCode;
import com.kobaco.smartad.model.data.SACMCodeClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SACMCodeTest {
	@Autowired
	CommonDao<SACMCodeClass> cmccDao;
	@Autowired
	CommonDao<SACMCode> cmcDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SACMCode cmc = insertCMCode();
		cmc = insertCMCode();
		
		try {
			List<SACMCode> cmcList = cmcDao.list(cmc);
			assertTrue(cmcList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}		
	}

	@Test
	public void testInfo() {
		SACMCode cmc = insertCMCode();		
		
		try {
			SACMCode cmcSelected = cmcDao.info(cmc);
			assertTrue(cmcSelected.getCMCD()+"=="+cmc.getCMCD(), cmcSelected.getCMCD().equals(cmc.getCMCD()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertCMCode();
	}

	@Test
	public void testUpdate() {
		SACMCode cmc = insertCMCode();
			
		cmc.setCMCD_EXPL("change ex");
		cmc.setCMCD_NM("change name");
		cmc.setUPD_ID("updaterId");			

		try {
			int cnt =cmcDao.update(cmc);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testDelete() {
		SACMCode cmc = insertCMCode();		

		try {
			int cnt = cmcDao.delete(cmc);
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
	
	
	private SACMCode insertCMCode() {		
		SACMCodeClass cmcc = insertCMCodeClass();				
		if(cmcc==null) {
			return null;
		}		
		SACMCode cmc = TestUtils.initCMCode(cmcc.getCMCD_CLS());
		
		try {
			cmcDao.insert(cmc);
			assertTrue(cmc.getCMCD()!=null);
			return cmc;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
