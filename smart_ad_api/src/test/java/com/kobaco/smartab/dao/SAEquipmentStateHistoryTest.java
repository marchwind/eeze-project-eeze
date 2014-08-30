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
import com.kobaco.smartad.model.data.SAEquipement;
import com.kobaco.smartad.model.data.SAEquipementConstitute;
import com.kobaco.smartad.model.data.SAEquipementStateHistory;
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SANfcTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAEquipmentStateHistoryTest {
	
	@Autowired
	CommonDao<SAEquipementStateHistory> dao;
	@Autowired
	CommonDao<SAEquipementConstitute> eqcnstDao;
	@Autowired
	CommonDao<SAEquipement> equipmentDao;
	@Autowired
	CommonDao<SAFacility> facilityDao;
	@Autowired
	CommonDao<SANfcTag> nfctagDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAEquipementStateHistory common = insertStateHistory();
		common = insertStateHistory();
		
		try {
			List<SAEquipementStateHistory> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}		
	}

	@Test
	public void testInfo() {
		SAEquipementStateHistory common = insertStateHistory();		
		
		try {
			SAEquipementStateHistory commonSelected = dao.info(common);
			assertTrue(commonSelected.getEQPM_CNST_NO()+"=="+common.getEQPM_CNST_NO(), commonSelected.getEQPM_CNST_NO().equals(common.getEQPM_CNST_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertStateHistory();
	}

	@Test
	public void testUpdate() {
		SAEquipementStateHistory common = insertStateHistory();
	
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
		SAEquipementStateHistory common = insertStateHistory();		

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
			nfctagDao.insert(common);
			assertTrue(common.getNFC_TAG_ID()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	private SAFacility insertFacilities() {
		
		SANfcTag tag = insertTag();
		SAFacility fac = TestUtils.initFacility(tag.getNFC_TAG_ID());
		
		try {
			facilityDao.insert(fac);
			assertTrue(fac.getFCLT_MGMT_NO()!=null);
			return fac;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	private SAEquipement insertEquipment() {
		SAFacility fac = insertFacilities();
		
		if(fac==null)
		return null;
		
		SAEquipement equ = TestUtils.initEquipmen(fac.getFCLT_MGMT_NO(),fac.getNFC_TAG_ID());
		
		try {
			equipmentDao.insert(equ);
			assertTrue(equ.getEQPM_NO()!=null);
			return equ;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	private SAEquipementConstitute insertConstitute() {	
		SAEquipement equ = insertEquipment();		
		SAEquipementConstitute common = TestUtils.initEConstitute(equ.getEQPM_NO());
		
		try {
			eqcnstDao.insert(common);
			assertTrue(common.getEQPM_CNST_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

	private SAEquipementStateHistory insertStateHistory() {
		
		SAEquipementConstitute cons = insertConstitute();
		
		SAEquipementStateHistory common = TestUtils.initESHistory(cons.getEQPM_NO(), cons.getEQPM_CNST_NO());
		
		try {
			dao.insert(common);
			assertTrue(common.getEQPM_CNST_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
