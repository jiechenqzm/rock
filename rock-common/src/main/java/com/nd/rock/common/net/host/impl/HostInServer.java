package com.nd.rock.common.net.host.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.net.bean.response.FinalGetHostResponse;
import com.nd.rock.common.net.host.CheckHostCommand;
import com.nd.rock.common.net.host.HostHolder;
import com.nd.rock.common.net.host.HostManager;
import com.nd.rock.common.util.QHttpUtil;

public class HostInServer implements HostHolder {
	
    private static final Logger logger = LoggerFactory.getLogger(HostInServer.class);

	private HostManager hostManager = new ConstantHostManager();

	private CheckHostCommand checkHostCommond = null;
	
	@Override
	public List<String> getHostList() {
		String result = null;
		try {
			result = QHttpUtil.doHttpRequest(hostManager.getHost(checkHostCommond), 80, "/rock/host/getHostList.do");
		} catch (Exception e) {
			logger.error("DoHttpRequest Error. Message >> " + e.getMessage(), e);
			return null;
		}
		FinalGetHostResponse response = FinalGetHostResponse
				.fromJsonString(result);
		if(!response.isOk()) {
			logger.error("GetHostListFromServer Error. Message >> " + response.getMessage());
			return null;
		}
		
		return response.getResponseBody().getHostList();
	}

	@Override
	public boolean saveHostList(List<String> hostList) {
		throw new UnsupportedOperationException(
				"'HostInServer.class' Can't Support Method:'saveHostList'");
	}

	@Override
	public boolean addHost(String host) {
		throw new UnsupportedOperationException(
				"'HostInServer.class' Can't Support Method:'saveHostList'");
	}

	public void setHostManager(HostManager hostManager) {
		this.hostManager = hostManager;
	}

	public void setCheckHostCommond(CheckHostCommand checkHostCommond) {
		this.checkHostCommond = checkHostCommond;
	}


}
