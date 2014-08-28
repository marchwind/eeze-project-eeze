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
import com.kobaco.smartad.model.data.SAEquipementSoftware;
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SANfcTag;
import com.kobaco.smartad.model.data.SASoftware;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAEquipmentSoftwareTest {
	
	@Autowired
	CommonDao<SAEquipementSoftware> dao;
	@Autowired
	CommonDao<SASoftware> softwareDao;
	@Autowired
	CommonDao<SAFacility> facilityDao;
	@Autowired
	CommonDao<SANfcTag> nfctagDao;
	@Autowired
	CommonDao<SAEquipement> equipmentDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAEquipementSoftware common = insertESoftware();
		common = insertESoftware();
		
		try {
			List<SAEquipementSoftware> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}	
	}

	@Test
	public void testInfo() {
		SAEquipementSoftware common = insertESoftware();		
		
		try {
			SAEquipementSoftware commonSelected = dao.info(common);
			assertTrue(commonSelected.getEQPM_NO()+"=="+common.getEQPM_NO(), commonSelected.getEQPM_NO().equals(common.getEQPM_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertESoftware();
	}

	@Test
	public void testUpdate() {
		SAEquipementSoftware common = insertESoftware();		
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
		SAEquipementSoftware common = insertESoftware();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	private SASoftware insertSotfware() {
		
		SASoftware common = TestUtils.initSoftware();
		
		try {
			softwareDao.insert(common);
			assertTrue(common.getSW_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
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


	private SAEquipementSoftware insertESoftware() {
		SASoftware soft = insertSotfware();
		SAEquipement equ = insertEquipment();
		SAEquipementSoftware common = TestUtils.initESoft(equ.getEQPM_NO(),soft.getSW_MGMT_NO());
		
		try {
			dao.insert(common);
			assertTrue(common.getEQPM_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
