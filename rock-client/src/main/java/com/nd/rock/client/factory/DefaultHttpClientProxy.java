package com.nd.rock.client.factory;

import java.util.List;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.client.host.impl.HostManagerWrapper;
import com.nd.rock.common.exception.ServerUnAvailableException;
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
import com.nd.rock.common.util.QStringUtil;

public class DefaultHttpClientProxy implements HttpClientProxy {

	private HttpClientFactory httpClientFactory = null;

	private HostManagerWrapper hostManagerWrapper = null;
	
	private String host = null;
	
	private DefaultHttpClientProxy() {}
	
	private void init() throws ServerUnAvailableException {
		HostInLocal hostInLocal = initHostInLocal();
		HostInServer hostInServer = initHostInServer();
		
		this.hostManagerWrapper = initManager(hostInLocal, hostInServer);
		this.host = initHost(this.hostManagerWrapper);
		
		updateFlushHost(this.host, hostInLocal, hostInServer);
		
		this.httpClientFactory = initClientFactory();
	}

	private void updateFlushHost(String host, HostInLocal hostInLocal,
			HostInServer hostInServer) {
		ConstantHostManager constantHostManager = new ConstantHostManager(host);
		hostInServer.setHostManager(constantHostManager);
		List<String> hostList = hostInServer.getHostList();
		hostInLocal.saveHostList(hostList);
	}

	private HostInLocal initHostInLocal() {
		return new HostInLocal(new ClientHostFactory());
	}

	private HostInServer initHostInServer() {
		return new HostInServer();
	}
	
	private String initHost(HostManagerWrapper hostManagerWrapper) throws ServerUnAvailableException {
		String host = hostManagerWrapper.getHost(new HeartBeatCommand());
		if(QStringUtil.nullOrEmpty(host)) {
			String message = "Server Not Find Error.";
			throw new ServerUnAvailableException(message);
		}
		return host;
	}

	private HostManagerWrapper initManager(HostHolder hostInLocal,
			HostHolder hostInServer) {
		// hostInLocal和hostInServer采用责任链模式
		HostManagerWrapper localWrapper = new HostManagerWrapper(hostInLocal);
		HostManagerWrapper serverWrapper = new HostManagerWrapper(hostInServer);
		localWrapper.setNextHostManager(serverWrapper);
		return localWrapper;
	}

	private HttpClientFactory initClientFactory() {
		return new CachedHttpClientFactoryDecorator(
				new DefaultHttpClientFactory(new ConcurrentHttpClientBuilder()));
	}
	
	private synchronized void reBuildHost() throws ServerUnAvailableException {
		host = initHost(hostManagerWrapper);
	}

	/********** 分割线，以上是内部方法，以下是API **********/

	public HttpClient getHttpClient() {
		return httpClientFactory.getHttpClient(host, 80);
	}

	public HttpClient rebuildHttpClient(HttpClient httpClient) throws ServerUnAvailableException {
		if(httpClient != httpClientFactory.getHttpClient(host, 80))
			return httpClientFactory.getHttpClient(host, 80);
		reBuildHost();
		return getHttpClient();
		
	}
	
	private static DefaultHttpClientProxy intance = null;
	
	public synchronized static DefaultHttpClientProxy getInstance() throws ServerUnAvailableException {
		if(intance == null) {
			intance = new DefaultHttpClientProxy();
			intance.init();
		}
		return intance;
	}

}
