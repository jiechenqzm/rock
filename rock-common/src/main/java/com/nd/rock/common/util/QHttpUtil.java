package com.nd.rock.common.util;

import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.common.net.http.HttpExecutor;
import com.nd.rock.common.net.http.impl.DefaultHttpClientFactory;
import com.nd.rock.common.net.http.impl.DefaultHttpExecutor;
import com.nd.rock.common.net.http.impl.EncodingHttpExecutorDecorator;
import com.nd.rock.common.net.http.impl.StrictHttpParam;

public class QHttpUtil {
	
	public static String doHttpRequest(String host, int port, String uri){
		
		HttpExecutor httpExecutor = new EncodingHttpExecutorDecorator(new DefaultHttpExecutor());
		DefaultHttpClientFactory httpClientFactory = new DefaultHttpClientFactory();
		httpClientFactory.setHttpParam(new StrictHttpParam());

		HttpClient httpClient = httpClientFactory.getHttpClient(host, port);
		return httpExecutor.executePost(httpClient, uri,
				new HashMap<String, String>());
	}

}
