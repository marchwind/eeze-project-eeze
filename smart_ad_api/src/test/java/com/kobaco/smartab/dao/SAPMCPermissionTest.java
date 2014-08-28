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
import com.kobaco.smartad.model.data.SAPMCPermission;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAPMCPermissionTest {

	@Autowired
	CommonDao<SAPMCPermission> dao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {		
		SAPMCPermission common = insertPermission();
		common = insertPermission();		
		
		try{
			List<SAPMCPermission> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testInfo() {
		SAPMCPermission common = insertPermission();

		try {
			SAPMCPermission commonSelect =  dao.info(common);		
			assertTrue(commonSelect.getMNGR_PRMS_NO()+"=="+common.getMNGR_PRMS_NO(), commonSelect.getMNGR_PRMS_NO().equals(common.getMNGR_PRMS_NO()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testinsertPermission() {
		insertPermission();
	}

	@Test
	public void testUpdate() {		
		SAPMCPermission common = insertPermission();
		common.setMNGR_PRMS_NM("ccmNGR_PRMS_NM");				
		common.setUPD_ID("ccupdaterId");
		try {
			int cnt = dao.update(common);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testDelete() {		
		SAPMCPermission common = insertPermission();

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}

	}
	
	private SAPMCPermission insertPermission() {		
		SAPMCPermission common = TestUtils.initPMCPermission();
		
		try {
			dao.insert(common);
			assertTrue(common.getMNGR_PRMS_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
