package com.nd.rock.common.net.http;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

public interface HttpExecutor {
	
	public String executePost(HttpClient httpClient, String uri, Map<String, String> paramMap);

}
