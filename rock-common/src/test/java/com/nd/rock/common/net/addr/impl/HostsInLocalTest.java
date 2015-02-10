package com.nd.rock.common.net.addr.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nd.rock.common.file.client.config.ClientHostFactory;
import com.nd.rock.common.net.host.HostHolder;
import com.nd.rock.common.net.host.impl.HostInLocal;

public class HostsInLocalTest {
	
	private static HostHolder hostsManager = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hostsManager = new HostInLocal(new ClientHostFactory());
	}

	@Test
	public void saveHostsList() {
		List<String> list = new ArrayList<>();
		list.add("127.0.0.1");
		list.add("127.0.0.2");
		list.add("127.0.0.3");
		Assert.assertTrue(hostsManager.saveHostList(list));	
	}
	
	@Test
	public void getHostsList() {
		List<String> list = hostsManager.getHostList();
		Assert.assertEquals(3, list.size());
		Assert.assertTrue(list.contains("127.0.0.1"));
		Assert.assertTrue(list.contains("127.0.0.2"));
		Assert.assertTrue(list.contains("127.0.0.3"));
	}
	
	@Test
	public void addHosts() {
		
		Assert.assertTrue(hostsManager.addHost("127.0.0.4"));
	}
}
