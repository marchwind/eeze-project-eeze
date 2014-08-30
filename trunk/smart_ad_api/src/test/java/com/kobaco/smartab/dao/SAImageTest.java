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
import com.kobaco.smartad.model.data.SAImage;
import com.kobaco.smartad.model.data.SANfcTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAImageTest {

	@Autowired
	CommonDao<SAImage> imgDao;
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
		SAImage img = insertImage();
		img = insertImage();
		
		try {
			List<SAImage> entList = imgDao.list(img);
			assertTrue(entList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}	
	}

	@Test
	public void testInfo() {
		SAImage img = insertImage();		
		
		try {
			SAImage imgSelected = imgDao.info(img);
			assertTrue(imgSelected.getIMG_MGMT_NO()+"=="+img.getIMG_MGMT_NO(), imgSelected.getIMG_MGMT_NO().equals(img.getIMG_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertimg() {
		insertImage();
	}

	@Test
	public void testUpdate() {
		SAImage img = insertImage();	
	
		img.setIMG_NM("_NM");
		img.setIMG_SPTH("_SPTH");
		img.setIMG_URL("_URL");		
		img.setUPD_ID("em");
		try {
			int cnt =imgDao.update(img);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testDelete() {

		SAImage img = insertImage();	

		try {
			int cnt = imgDao.delete(img);
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

	private SAImage insertImage() {
		SAEquipement equ = insertEquipment();
		
		if(equ==null)
			return null;
		
//		SAImage img = TestUtils.initImage(equ.getFCLT_MGMT_NO(),equ.getEQPM_NO());
		
//		try {
//			imgDao.insert(img);
//			assertTrue(img.getIMG_MGMT_NO()!=null);
//			return img;
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("error occur");		
//			return null;
//		}
		return null;
	}	
}
