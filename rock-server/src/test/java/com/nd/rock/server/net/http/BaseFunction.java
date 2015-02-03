package com.nd.rock.server.net.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class BaseFunction {
	
	protected String realRequest(HttpClient httpClient, PostMethod postMethod) throws Exception {
		String result = null;
		try {
			int status = httpClient.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {  
				result = postMethod.getResponseBodyAsString(); 
            } else {  
            	postMethod.abort();
            	String errorMsg = "Net Error: " + postMethod.getStatusText() + "! Code >> " + postMethod.getStatusCode();
                throw new IllegalStateException(errorMsg);
            }
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	protected HttpClient createHttpClient(String host, int port,
			int connectionTimeout, int requestTimeout) {
//		HttpClient httpClient = new HttpClient();
		//默认的HttpClient并发场景下会有问题
		MultiThreadedHttpConnectionManager connectionManager =
                new MultiThreadedHttpConnectionManager();
		HttpClient httpClient = new HttpClient(connectionManager);
		
		httpClient.getHostConfiguration().setHost(host, port, "http");
		httpClient
				.getHttpConnectionManager()
				.getParams()
				.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT,
						connectionTimeout);
		httpClient.getHttpConnectionManager().getParams()
				.setParameter(HttpClientParams.SO_TIMEOUT, requestTimeout);
		return httpClient;
	}

}
