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
import com.kobaco.smartad.model.data.SAOperationTscs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAOperationTscsTest {
	
	@Autowired
	CommonDao<SAOperationTscs> dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		SAOperationTscs common = insertOperationTscs();
		common = insertOperationTscs();
		
		try {
			List<SAOperationTscs> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInfo() {
		SAOperationTscs common = insertOperationTscs();		
		
		try {
			SAOperationTscs commonSelected = dao.info(common);
			assertTrue(commonSelected.getOPRT_TSCS_MGMT_NO()+"=="+common.getOPRT_TSCS_MGMT_NO(), commonSelected.getOPRT_TSCS_MGMT_NO().equals(common.getOPRT_TSCS_MGMT_NO()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}

	@Test
	public void testInsertfac() {
		insertOperationTscs();
	}

	@Test
	public void testUpdate() {
		SAOperationTscs common = insertOperationTscs();

		common.setTSCS_CNTT("ctSCS_CNTT");
		common.setTSCS_TP_CD("ctSC");
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
		SAOperationTscs common = insertOperationTscs();		

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");			
		}
	}
	

	private SAOperationTscs insertOperationTscs() {		
		SAOperationTscs common = TestUtils.initOperationTscs();
		
		try {
			dao.insert(common);
			assertTrue(common.getOPRT_TSCS_MGMT_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
