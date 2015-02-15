package com.nd.rock.client.factory;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.common.exception.ServerUnAvailableException;

public interface HttpClientProxy {

	public HttpClient getHttpClient();

	public HttpClient rebuildHttpClient(HttpClient httpClient) throws ServerUnAvailableException;
	

}
