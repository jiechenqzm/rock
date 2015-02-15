package com.nd.rock.client.facade;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClientApiTest {
	
	private static final String GROUP = "TEST_GROUP_HTTP";
	
	private static String DATA_ID = "change.value";
	
	private static Client client = null;
	
	private static ContentObserver contentObserver = new ContentObserver() {
		@Override
		public void update(String content) {
			System.out.println("Observer Is Called ! NewContent >> " + content);
		}
	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		client = ClientFactory.getClient();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getContent() {
		System.out.println(client.getContent(GROUP, DATA_ID));
	}
	
//	@Test
//	public void getContentProfile() {
//		long start = System.currentTimeMillis();
//		for(int i=0;i<10000;i++) {
//			String result = client.getContent(GROUP, DATA_ID);
//		}
//		System.out.println("tps:" + 1000*1000/(System.currentTimeMillis() - start));
//	}
	
	@Test
	public void registerObserver(){
		client.registerObserver(GROUP, DATA_ID, "111", contentObserver);
	}
	
	@Test
	public void removeObverser(){
		client.removeObserver(GROUP, DATA_ID, contentObserver);
	}

}
