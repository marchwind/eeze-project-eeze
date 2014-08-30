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
import com.kobaco.smartad.model.data.SADevice;
import com.kobaco.smartad.model.data.SADeviceRental;
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SAFacilityReserve;
import com.kobaco.smartad.model.data.SAFacilityReserveDate;
import com.kobaco.smartad.model.data.SAFacilityVisitHistory;
import com.kobaco.smartad.model.data.SAFacilityVisitor;
import com.kobaco.smartad.model.data.SANfcTag;
import com.kobaco.smartad.model.data.SAUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SADeviceRentalTest {
	
	@Autowired
	CommonDao<SADeviceRental> dao;
	@Autowired
	CommonDao<SADevice> deviceDao;
	@Autowired
	CommonDao<SAFacilityVisitor> visitorDao;
	@Autowired
	CommonDao<SAFacilityReserve> reservationDao;
	@Autowired
	CommonDao<SAUser> userDao;	
	@Autowired
	CommonDao<SAEnterprise> enterpriseDao;
	@Autowired
	CommonDao<SAFacility> facilityDao;
	@Autowired
	CommonDao<SANfcTag> nfctagDao;
	@Autowired
	CommonDao<SAFacilityVisitHistory> historyDao;
	@Autowired
	CommonDao<SAFacilityReserveDate> reservedatedao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SADeviceRental common = insertDeviceRental();
		common = insertDeviceRental();
		
		try {
			List<SADeviceRental> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}		
	}

	@Test
	public void testInfo() {
		SADeviceRental common = insertDeviceRental();		
		
		try {
			SADeviceRental commonSelected = dao.info(common);
//			assertTrue(commonSelected.getDEV_MGMT_NO()+"=="+common.getDEV_MGMT_NO(), commonSelected.getDEV_MGMT_NO().equals(common.getDEV_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertDeviceRental();
	}

	@Test
	public void testUpdate() {
		SADeviceRental common = insertDeviceRental();
		common.setRNT_DTT(new Date());
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
		SADeviceRental common = insertDeviceRental();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	private SADevice insertDevice() {
		SADevice common = TestUtils.initDevice();
		
		try {
			deviceDao.insert(common);
			assertTrue(common.getDEV_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	private SAEnterprise insertEnterprise() {
		SAEnterprise ent = TestUtils.initEnterprise();
		try {
			enterpriseDao.insert(ent);
			assertTrue(ent.getENTP_NO()!=null);
			return ent;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}
	
	public SAUser insertUser() {
		
		SAEnterprise ent = insertEnterprise();
		
		if (ent == null) {
			return null;
		}
		SAUser common = TestUtils.initUser(ent.getENTP_NO());
		try {
			userDao.insert(common);
			assertTrue(common.getUSR_NO()!=null);
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
	
	private SAFacilityReserve insertReservation() {
		
		SAUser user = insertUser();
		SAFacility fac = insertFacilities();
		
		if ( user == null || fac == null) {
			return null;
		}
		SAFacilityReserve rev = TestUtils.initReservation(fac.getFCLT_MGMT_NO(),user.getUSR_NO());
		try {
			reservationDao.insert(rev);
			assertTrue(rev.getRSRV_NO()!=null);
			return rev;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}
	private SAFacilityReserveDate insertReserveDate() {	
		
		SAFacilityReserve rev = insertReservation();
		SAFacilityReserveDate common = TestUtils.initReserveDate(rev.getRSRV_NO());
		
		try {
			reservedatedao.insert(common);
			assertTrue(common.getRSRV_DTL_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	
	private SAFacilityVisitHistory insertHisotry() {
		
		SAFacilityReserveDate rev =insertReserveDate();
		
		if ( rev == null) {
			return null;
		}
		SAFacilityVisitHistory common = TestUtils.initVisitorHistory(rev.getRSRV_NO(),rev.getRSRV_DTL_NO());
		try {
			historyDao.insert(common);
			assertTrue(common.getVST_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}
	
	private SAFacilityVisitor insertVisitor() {
		
		SAFacilityVisitHistory his =insertHisotry();		
		SAFacilityVisitor common = TestUtils.initVisitor(his.getVST_NO());
		
		try {
			visitorDao.insert(common);
			assertTrue(common.getFCLT_VSTR_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
	private SADeviceRental insertDeviceRental() {
		SADevice dev = insertDevice();
		SAFacilityVisitor fac = insertVisitor();
		SADeviceRental common = TestUtils.initDeviceRental(fac.getFCLT_VSTR_NO(), dev.getDEV_MGMT_NO(),fac.getVST_NO());
		
		try {
			dao.insert(common);
//			assertTrue(common.getDEV_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
