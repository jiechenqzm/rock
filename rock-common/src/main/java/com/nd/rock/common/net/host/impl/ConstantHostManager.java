package com.nd.rock.common.net.host.impl;

import com.nd.rock.common.net.host.CheckHostCommand;
import com.nd.rock.common.net.host.HostManager;
import com.nd.rock.common.util.QStringUtil;

public class ConstantHostManager implements HostManager {
	
	private static final String ROCK_SERVER_HOST_NAME = "server.rock.middleware.nd.com";
	
	private String host = null;;
	
	public ConstantHostManager() {
		this(ROCK_SERVER_HOST_NAME);
	}
	
	public ConstantHostManager(String host) {
		if(!QStringUtil.nullOrEmpty(host))
			this.host = host;
	}

	@Override
	public String getHost(CheckHostCommand checkHostCommond) {
		return this.host;
	}
	
}
