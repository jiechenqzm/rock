package com.nd.rock.server.model.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nd.rock.server.model.instance.CoreDataIn;

public class CoreDataDAOImplTest {

	private static final String GROUP = "TEST_GROUP";
	private static final String DATA_ID = "junit_test_for_write_function";
	private static final Long VERSION = 0l;
	private static final String SUMMARY = "summary";
	private static final String VALUE = "content";

	public static CoreDataDAO coreDataDAO = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:application-context.xml");
		coreDataDAO = (CoreDataDAO) applicationContext.getBean("coreDataDAO");
		System.out.println(coreDataDAO);
	}

	@Test
	public void delete() {
		long effectRow = coreDataDAO.delete(GROUP, DATA_ID);
		Assert.assertEquals(1, effectRow);
	}

	@Test
	public void insert() {
		CoreDataIn coreDataIn = buildCoreDataIn();
		long effectRow = coreDataDAO.insert(coreDataIn);
		Assert.assertEquals(1, effectRow);
	}

	@Test
	public void query() {
		CoreDataIn coreDataIn = coreDataDAO.query(GROUP, DATA_ID);
		Assert.assertNotNull(coreDataIn);
		Assert.assertEquals(GROUP, coreDataIn.getGroup());
		Assert.assertEquals(DATA_ID, coreDataIn.getDataId());
		Assert.assertEquals(VERSION.doubleValue(), coreDataIn.getVersion(), 0l);
		Assert.assertEquals(SUMMARY, coreDataIn.getSummary());
		Assert.assertEquals(VALUE, coreDataIn.getValue());
	}

	@Test
	public void update() {
		int effectRow = coreDataDAO.update(GROUP, DATA_ID, 0L, "UPDATE_VALUE",
				"NEW_SUMMARY");
		Assert.assertEquals(1, effectRow);
	}

	private CoreDataIn buildCoreDataIn() {
		CoreDataIn.CoreDataBuilder builder = new CoreDataIn.CoreDataBuilder();
		builder.setId(0);
		builder.setGroup(GROUP);
		builder.setDataId(DATA_ID);
		builder.setSummary(SUMMARY);
		builder.setVersion(VERSION);
		builder.setValue(VALUE);
		builder.setGmtCreate(new Date());
		builder.setGmtModified(new Date());
		return builder.build();
	}

}
