package com.nd.rock.client.factory;

import java.util.List;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.client.host.impl.HostManagerWrapper;
import com.nd.rock.common.file.client.config.ClientHostFactory;
import com.nd.rock.common.net.host.HostHolder;
import com.nd.rock.common.net.host.impl.ConstantHostManager;
import com.nd.rock.common.net.host.impl.HeartBeatCommand;
import com.nd.rock.common.net.host.impl.HostInLocal;
import com.nd.rock.common.net.host.impl.HostInServer;
import com.nd.rock.common.net.http.HttpClientFactory;
import com.nd.rock.common.net.http.impl.CachedHttpClientFactoryDecorator;
import com.nd.rock.common.net.http.impl.ConcurrentHttpClientBuilder;
import com.nd.rock.common.net.http.impl.DefaultHttpClientFactory;

public class ClientStaticHostFactory {

	private static HttpClientFactory httpClientFactory = null;

	private static HostManagerWrapper hostManagerWrapper = null;
	
	private static String host = null;

	static {
		HostInLocal hostInLocal = initHostInLocal();
		HostInServer hostInServer = initHostInServer();
		
		hostManagerWrapper = initManager(hostInLocal, hostInServer);
		host = initHost(hostManagerWrapper);
		
		updateFlushHost(host, hostInLocal, hostInServer);
		
		httpClientFactory = initClientFactory();

	}

	private static void updateFlushHost(String host, HostInLocal hostInLocal,
			HostInServer hostInServer) {
		ConstantHostManager constantHostManager = new ConstantHostManager(host);
		hostInServer.setHostManager(constantHostManager);
		List<String> hostList = hostInServer.getHostList();
		hostInLocal.saveHostList(hostList);
	}

	private static HostInLocal initHostInLocal() {
		return new HostInLocal(new ClientHostFactory());
	}

	private static HostInServer initHostInServer() {
		return new HostInServer();
	}
	
	private static String initHost(HostManagerWrapper hostManagerWrapper){
		return hostManagerWrapper.getHost(new HeartBeatCommand());
	}

	private static HostManagerWrapper initManager(HostHolder hostInLocal,
			HostHolder hostInServer) {
		// hostInLocal和hostInServer采用责任链模式
		HostManagerWrapper localWrapper = new HostManagerWrapper(hostInLocal);
		HostManagerWrapper serverWrapper = new HostManagerWrapper(hostInServer);
		localWrapper.setNextHostManager(serverWrapper);
		return localWrapper;
	}

	private static HttpClientFactory initClientFactory() {
		return new CachedHttpClientFactoryDecorator(
				new DefaultHttpClientFactory(new ConcurrentHttpClientBuilder()));
	}
	
	private static synchronized void reBuildHost(){
		host = initHost(hostManagerWrapper);
	}

	/********** 分割线，以上是内部方法，以下是API **********/

	public static HttpClient getHttpClient() {
		return httpClientFactory.getHttpClient(host, 80);
	}

	public static HttpClient rebuildHttpClient(HttpClient httpClient) {
		if(httpClient != httpClientFactory.getHttpClient(host, 80))
			return httpClientFactory.getHttpClient(host, 80);
		reBuildHost();
		return getHttpClient();
		
	}


}
