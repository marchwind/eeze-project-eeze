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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/META-INF/root-context.xml"
})
public class SAEnterpriseTest {
	
	@Autowired
	CommonDao<SAEnterprise> dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAEnterprise common = insertEnterprise();
		common = insertEnterprise();
		
		try {
			List<SAEnterprise> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		
		SAEnterprise common = insertEnterprise();
		
		try {
			SAEnterprise commonSelected = dao.info(common);
			assertTrue(commonSelected.getENTP_NO()+"=="+common.getENTP_NO(), commonSelected.getENTP_NO().equals(common.getENTP_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	
	@Test
	public void testInsert() {
		insertEnterprise();
	}
	
	@Test
	public void testUpdate() {
		
		SAEnterprise common = insertEnterprise();
		
		common.setBRN("cbRN");
		common.setBSTP_CD("c_CD");
		common.setENTP_ADDR("ceNTP_ADDR");
		common.setENTP_EML("ceNTP_EML");
		common.setENTP_NM("ceNTP_NM");
		common.setENTP_NM("ceNTP_NM");
		common.setFAX("cfAX");
		common.setPHN("cpHN");
		common.setUPD_ID("cupdaterId");		
		
		try {
			int cnt = dao.update(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	@Test
	public void testDelete() {
		
		SAEnterprise common = insertEnterprise();
		
		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	
	private SAEnterprise insertEnterprise() {
		
		SAEnterprise common = TestUtils.initEnterprise();
		
		try {
			dao.insert(common);
			assertTrue(common.getENTP_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	
}
