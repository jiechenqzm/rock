package com.nd.rock.common.net.http.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.common.net.http.HttpClientFactory;

public class CachedHttpClientFactoryDecorator implements HttpClientFactory {

	private HttpClientFactory httpClientFactory = null;

	private ConcurrentHashMap<String, HttpClient> httpClientMap = new ConcurrentHashMap<>();

	public CachedHttpClientFactoryDecorator(HttpClientFactory httpClientFactory) {
		this.httpClientFactory = httpClientFactory;
	}

	@Override
	public HttpClient getHttpClient(String host, int port) {
		String key = this.generateCacheKey(host, port);
		HttpClient httpClient = this.httpClientMap.get(key);
		return httpClient != null ? httpClient : generateHttpClient(key, host,
				port);
	}

	private HttpClient generateHttpClient(String key, String host, int port) {
		HttpClient httpClient = this.httpClientFactory
				.getHttpClient(host, port);
		HttpClient innerHttpClient = this.httpClientMap.putIfAbsent(key,
				httpClient);
		return innerHttpClient != null ? innerHttpClient : httpClient;
	}

	private String generateCacheKey(String host, int port) {
		return host + "##" + String.valueOf(port);
	}

}
