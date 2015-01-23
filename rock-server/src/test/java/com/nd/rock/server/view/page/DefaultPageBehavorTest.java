package com.nd.rock.server.view.page;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nd.rock.server.view.page.impl.DefaultPageBehavor;

public class DefaultPageBehavorTest {

	private static PageBehavor pageBehavor1 = null;

	private static PageBehavor pageBehavor2 = null;

	private static PageBehavor pageBehavor3 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		pageBehavor1 = new DefaultPageBehavor(5, 10, 1);
		pageBehavor2 = new DefaultPageBehavor(55, 10, 3);
		pageBehavor3 = new DefaultPageBehavor(80, 10, 8);
	}

	@Test
	public void getCurrentPageNo() {
		Assert.assertEquals(1, pageBehavor1.getCurrentPageNo());
		Assert.assertEquals(3, pageBehavor2.getCurrentPageNo());
		Assert.assertEquals(8, pageBehavor3.getCurrentPageNo());
	}

	@Test
	public void getPreviousPageNo() {
		Assert.assertEquals(0, pageBehavor1.getPreviousPageNo());
		Assert.assertEquals(2, pageBehavor2.getPreviousPageNo());
		Assert.assertEquals(7, pageBehavor3.getPreviousPageNo());
	}

	@Test
	public void getNextPageNo() {
		Assert.assertEquals(0, pageBehavor1.getNextPageNo());
		Assert.assertEquals(4, pageBehavor2.getNextPageNo());
		Assert.assertEquals(0, pageBehavor3.getNextPageNo());
	}

	@Test
	public void getFirstPageNo() {
		Assert.assertEquals(1, pageBehavor1.getFirstPageNo());
		Assert.assertEquals(1, pageBehavor2.getFirstPageNo());
		Assert.assertEquals(1, pageBehavor3.getFirstPageNo());
	}

	@Test
	public void getLastPageNo() {
		Assert.assertEquals(1, pageBehavor1.getLastPageNo());
		Assert.assertEquals(6, pageBehavor2.getLastPageNo());
		Assert.assertEquals(8, pageBehavor3.getLastPageNo());
	}

	@Test
	public void getPageSize() {
		Assert.assertEquals(10, pageBehavor1.getPageSize());
		Assert.assertEquals(10, pageBehavor2.getPageSize());
		Assert.assertEquals(10, pageBehavor3.getPageSize());
	}

	@Test
	public void getTotalCount() {
		Assert.assertEquals(5, pageBehavor1.getTotalCount());
		Assert.assertEquals(55, pageBehavor2.getTotalCount());
		Assert.assertEquals(80, pageBehavor3.getTotalCount());
	}

}
