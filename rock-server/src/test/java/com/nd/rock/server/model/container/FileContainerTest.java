package com.nd.rock.server.model.container;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nd.rock.server.model.container.impl.FileContainerImpl;

public class FileContainerTest {
	
	private static final String GROUP = "TEST_GROUP";
	
	private static DataContainer dataContainer = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataContainer = new FileContainerImpl();
	}
	
	@Test
	public void testUpdate() {
		boolean success = false;
		try {
			success = dataContainer.update(GROUP, "junit_test_for_write_function", "ÖÐÎÄ");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(success);
	}
	
	@Test
	public void testRead() {
		String value = null;
		try {
			value = dataContainer.get(GROUP, "junit_test_for_read_function");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("read", value);
	}
	
	@Test
	public void testDelete() {
		boolean success = false;
		try {
			success = dataContainer.delete(GROUP, "junit_test_for_write_function");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(success);
	}

}
