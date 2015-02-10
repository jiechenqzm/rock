package com.nd.rock.common.net.http.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpClientParams;

import com.nd.rock.common.net.http.HttpClientBuilder;
import com.nd.rock.common.net.http.HttpClientFactory;
import com.nd.rock.common.net.http.HttpParam;

public class DefaultHttpClientFactory implements HttpClientFactory {

	private HttpParam httpParam = new DefaultHttpParam();
	
	private HttpClientBuilder httpClientBuilder = null;
	
	public DefaultHttpClientFactory() {
		this(new SimpleHttpClientBuilder());
	}
	
	public DefaultHttpClientFactory(HttpClientBuilder httpClientBuilder) {
		this.httpClientBuilder = httpClientBuilder;
	}

	@Override
	public HttpClient getHttpClient(String host, int port) {
		HttpClient httpClient = httpClientBuilder.build();

		httpClient.getHostConfiguration().setHost(host, port, "http");
		httpClient
				.getHttpConnectionManager()
				.getParams()
				.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT,
						httpParam.getConnectTimeout());
		httpClient
				.getHttpConnectionManager()
				.getParams()
				.setParameter(HttpClientParams.SO_TIMEOUT,
						httpParam.getSocketTimeOut());
		return httpClient;
	}
	
	public void setHttpParam(HttpParam httpParam) {
		this.httpParam = httpParam;
	}

}
