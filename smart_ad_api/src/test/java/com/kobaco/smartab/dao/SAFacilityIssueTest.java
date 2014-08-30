package com.kobaco.smartab.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kobaco.smartad.dao.CommonDao;
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SAFacilityIssue;
import com.kobaco.smartad.model.data.SANfcTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAFacilityIssueTest {

	@Autowired
	CommonDao<SAFacilityIssue> facilityissueDao;
	@Autowired
	CommonDao<SAFacility> facilityDao;
	@Autowired
	CommonDao<SANfcTag> nfctagDao;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAFacilityIssue faciss = insertFacilitiesIssue();
		faciss = insertFacilitiesIssue();
		
		try {
			List<SAFacilityIssue> entList = facilityissueDao.list(faciss);
			assertTrue(entList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAFacilityIssue faciss = insertFacilitiesIssue();		
		
		try {
			SAFacilityIssue facissSelected = facilityissueDao.info(faciss);
			assertTrue(facissSelected.getISS_NO()+"=="+faciss.getISS_NO(), facissSelected.getISS_NO().equals(facissSelected.getISS_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertFacilitiesIssue();
	}

	@Test
	public void testUpdate() {
		SAFacilityIssue fac = insertFacilitiesIssue();
			
		fac.setED_DTT(new Date());
		fac.setFCLT_OPRT_YN("N");
		fac.setISS_CNTT("iSS_CNTT");
		fac.setST_DTT(new Date());						
		fac.setUPD_ID("updaterId");	

		try {
			int cnt =facilityissueDao.update(fac);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}

	}

	@Test
	public void testDelete() {
		SAFacilityIssue fac = insertFacilitiesIssue();
		fac.setISS_NO(fac.getISS_NO());		

		try {
			int cnt = facilityissueDao.delete(fac);
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

	
	private SAFacilityIssue insertFacilitiesIssue() {
		
		SAFacility fac = insertFacilities();
		
		if (fac == null) {
			return null;
		}
		
		SAFacilityIssue faciss = TestUtils.initFacilityIssue(fac.getFCLT_MGMT_NO());
		
		try {
			facilityissueDao.insert(faciss);
			assertTrue(faciss.getISS_NO()!=null);
			return faciss;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
