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
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SANfcTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAFacilityTest {

	@Autowired
	CommonDao<SAFacility> facilityDao;
	@Autowired
	CommonDao<SANfcTag> nfctagDao;
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAFacility fac = insertFacilities();
		fac = insertFacilities();
		
		try {
			List<SAFacility> entList = facilityDao.list(fac);
			assertTrue(entList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAFacility fac = insertFacilities();		
		
		try {
			SAFacility facSelected = facilityDao.info(fac);
			assertTrue(facSelected.getFCLT_MGMT_NO()+"=="+fac.getFCLT_MGMT_NO(), facSelected.getFCLT_MGMT_NO().equals(fac.getFCLT_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertFacilities();
	}

	@Test
	public void testUpdate() {
		SAFacility fac = insertFacilities();
		fac.setFCLT_EXPL("LT_EXPL");
		fac.setFCLT_LOC("fT_LOC");
		fac.setFCLT_NM("fC_NM");
		fac.setFCLT_STTS_CD("CD");
		fac.setSTTN("12");				
		fac.setUPD_ID("uerId");

		try {
			int cnt =facilityDao.update(fac);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}

	}

	@Test
	public void testDelete() {
		SAFacility fac = insertFacilities();		

		try {
			int cnt = facilityDao.delete(fac);
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

}
