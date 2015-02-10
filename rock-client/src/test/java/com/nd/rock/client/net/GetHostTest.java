package com.nd.rock.client.net;

import java.util.List;

import org.junit.Test;

import com.nd.rock.client.host.impl.HostManagerWrapper;
import com.nd.rock.common.file.client.config.ClientHostFactory;
import com.nd.rock.common.net.host.impl.ConstantHostManager;
import com.nd.rock.common.net.host.impl.HeartBeatCommand;
import com.nd.rock.common.net.host.impl.HostInLocal;
import com.nd.rock.common.net.host.impl.HostInServer;

public class GetHostTest {

	@Test
	public void getHost() {
		// hostInLocal和hostInServer采用责任链模式
		HostInLocal hostInLocal = new HostInLocal(new ClientHostFactory());
		HostManagerWrapper localWrapper = new HostManagerWrapper(hostInLocal);

		HostInServer hostInServer = new HostInServer();
		HostManagerWrapper serverWrapper = new HostManagerWrapper(hostInServer);
		localWrapper.setNextHostManager(serverWrapper);

		String host = localWrapper.getHost(new HeartBeatCommand());

		ConstantHostManager constantHostManager = new ConstantHostManager(host);
		hostInServer.setHostManager(constantHostManager);
		List<String> hostList = hostInServer.getHostList();
		hostInLocal.saveHostList(hostList);
	}

}
