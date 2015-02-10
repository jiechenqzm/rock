package com.nd.rock.client.host.impl;

import com.nd.rock.common.net.host.CheckHostCommand;
import com.nd.rock.common.net.host.HostManager;

/**
 * 
 * 使用责任链模式
 * @author QiuZongming
 *
 */
public abstract class AbstractHostManager implements HostManager {

	private AbstractHostManager nextHostManager = null;

	public AbstractHostManager getNextHostManager() {
		return nextHostManager;
	}

	public void setNextHostManager(AbstractHostManager nextHostManager) {
		this.nextHostManager = nextHostManager;
	}

	public String nextHostManagerHandle(CheckHostCommand checkHostCommond) {
		return this.nextHostManager == null ? null : this.nextHostManager
				.getHost(checkHostCommond);
	}

}
