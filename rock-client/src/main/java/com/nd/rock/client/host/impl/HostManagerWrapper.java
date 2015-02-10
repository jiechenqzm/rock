package com.nd.rock.client.host.impl;

import java.util.List;
import java.util.Random;

import com.nd.rock.common.net.host.CheckHostCommand;
import com.nd.rock.common.net.host.HostHolder;
import com.nd.rock.common.util.QListUtil;

public class HostManagerWrapper extends AbstractHostManager {

	private HostHolder hostHolder = null;
	
	public HostManagerWrapper(HostHolder hostHolder) {
		this.hostHolder = hostHolder;
	}

	@Override
	public String getHost(CheckHostCommand checkHostCommond) {
		String host = selfChooseHost(checkHostCommond);
		return host == null ? super.nextHostManagerHandle(checkHostCommond)
				: host;
	}

	private String selfChooseHost(CheckHostCommand checkHostCommond) {
		List<String> hostList = this.hostHolder.getHostList();
		if (QListUtil.nullOrEmpty(hostList))
			return null;

		Random random = new Random();
		int start = random.nextInt(hostList.size());
		int index = start;
		boolean isOk = false;
		do {
			String host = hostList.get(index);
			if (checkHostCommond != null && checkHostCommond.doCheck(host))
				return host;

			index = (index + 1) % hostList.size();
		} while (!isOk && index != start);

		return null;
	}

}
