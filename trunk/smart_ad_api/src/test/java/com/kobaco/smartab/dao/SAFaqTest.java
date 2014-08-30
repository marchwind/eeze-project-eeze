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
import com.kobaco.smartad.model.data.SAFaq;
import com.kobaco.smartad.model.data.SANotification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/root-context.xml" })
public class SAFaqTest {

	@Autowired
	CommonDao<SAFaq> dao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {		
		SAFaq common = insertFaq();
		common = insertFaq();		
		
		try{
			List<SAFaq> commonList = dao.list(common);
			assertTrue(commonList.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testInfo() {
		SAFaq common = insertFaq();

		try {
			SAFaq commonSelect =  dao.info(common);		
			assertTrue(commonSelect.getFAQ_NO()+"=="+common.getFAQ_NO(), commonSelect.getFAQ_NO().equals(common.getFAQ_NO()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}
	}

	@Test
	public void testinsertFaq() {
		insertFaq();
	}

	@Test
	public void testUpdate() {		
		SAFaq common = insertFaq();
		common.setA_CNTT("ca_CNTT");
		common.setA_SBJT("ca_SBJT");
		common.setQ_CNTT("cq_CNTT");
		common.setQ_SBJT("cq_SBJT");
		common.setUPD_ID("cupdaterId");

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
		SAFaq common = insertFaq();

		try {
			int cnt = dao.delete(common);
			assertTrue(cnt==1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("error occur");
		}

	}
	
	private SAFaq insertFaq() {		
		SAFaq common = TestUtils.initFaq();
		
		try {
			dao.insert(common);
			assertTrue(common.getFAQ_NO()!=null);
			return common;
		} catch(Exception e) {
			e.printStackTrace();
			fail("error occur");		
			return null;
		}
	}	

}
