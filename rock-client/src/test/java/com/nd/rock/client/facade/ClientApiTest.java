package com.nd.rock.client.facade;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClientApiTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Client client = ClientFactory.getClient();
		String result = client.getContent("DEFAULT_GROUP", "com.nd.bigdata.test");
		System.out.println(result);
	}

}
