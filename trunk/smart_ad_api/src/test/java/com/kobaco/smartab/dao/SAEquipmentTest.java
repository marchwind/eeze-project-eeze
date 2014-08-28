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
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SANfcTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAEquipmentTest {

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
		SAEquipement equ = insertEquipment();
		equ = insertEquipment();
		
		try {
			List<SAEquipement> entList = equipmentDao.list(equ);
			assertTrue(entList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}		
	}

	@Test
	public void testInfo() {
	
		SAEquipement equ = insertEquipment();		
		
		try {
			SAEquipement equSelected = equipmentDao.info(equ);
			assertTrue(equSelected.getEQPM_NO()+"=="+equ.getEQPM_NO(), equSelected.getEQPM_NO().equals(equ.getEQPM_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}			
	}

	@Test
	public void testInsertequ() {
		insertEquipment();
	}

	@Test
	public void testUpdate() {
		
		SAEquipement equ = insertEquipment();	
		
		equ.setEQPM_EXPL("PM_EXPL");
		equ.setEQPM_IP("eQ_IP");
		equ.setEQPM_NM("eQ_NM");
		equ.setEQPM_STTS_CD("e_CD");
		equ.setMDL_NM("mDL_");
		equ.setMFTR("mF");		
		equ.setUPD_ID("tem");

		try {
			int cnt =equipmentDao.update(equ);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testDelete() {
		
		SAEquipement equ = insertEquipment();		

		try {
			int cnt = equipmentDao.delete(equ);
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

}
