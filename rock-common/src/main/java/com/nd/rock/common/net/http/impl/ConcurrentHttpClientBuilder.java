package com.nd.rock.common.net.http.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

import com.nd.rock.common.net.http.HttpClientBuilder;

public class ConcurrentHttpClientBuilder implements HttpClientBuilder{
	
	@Override
	public HttpClient build() {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpClient httpClient = new HttpClient(connectionManager);
		return httpClient;
	}

}
