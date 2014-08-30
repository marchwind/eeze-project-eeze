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
import com.kobaco.smartad.model.data.SADevice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SADeviceTest {
	
	@Autowired
	CommonDao<SADevice> dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SADevice common = insertDevice();
		common = insertDevice();
		
		try {
			List<SADevice> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
		
		
	}

	@Test
	public void testInfo() {
		SADevice common = insertDevice();		
		
		try {
			SADevice commonSelected = dao.info(common);
			assertTrue(commonSelected.getDEV_MGMT_NO()+"=="+common.getDEV_MGMT_NO(), commonSelected.getDEV_MGMT_NO().equals(common.getDEV_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertDevice();
	}

	@Test
	public void testUpdate() {
		SADevice common = insertDevice();
	
		common.setDEV_NM("dEV_NM");
		common.setDEV_STTS_CD("dEV_S");				
		common.setHGT_RESO(1);
		common.setMDL_NM("mDL_NM");
		common.setMFTR("mFTR");
		common.setOS("oS");
		common.setWDT_RESO(10);		
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
		SADevice common = insertDevice();		

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
			dao.insert(common);
			assertTrue(common.getDEV_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
