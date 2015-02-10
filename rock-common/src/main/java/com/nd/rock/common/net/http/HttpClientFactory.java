package com.nd.rock.common.net.http;

import org.apache.commons.httpclient.HttpClient;

public interface HttpClientFactory {
	
	public HttpClient getHttpClient(String host, int port);

}
