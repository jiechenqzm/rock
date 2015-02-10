package com.nd.rock.common.net.http.impl;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.common.net.http.HttpClientBuilder;

public class SimpleHttpClientBuilder implements HttpClientBuilder{
	
	@Override
	public HttpClient build() {
		HttpClient httpClient = new HttpClient();
		return httpClient;
	}

}
