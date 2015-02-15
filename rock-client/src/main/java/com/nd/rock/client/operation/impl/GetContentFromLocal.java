package com.nd.rock.client.operation.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.client.factory.DefaultHttpClientProxy;
import com.nd.rock.client.operation.ContentOperation;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;
import com.nd.rock.common.net.http.HttpExecutor;

public abstract class GetContentFromLocal implements ContentOperation {

	private HttpExecutor httpExecutor = null;
	
	private DefaultHttpClientProxy clientStaticHostFactory = null;

	public GetContentFromLocal(HttpExecutor httpExecutor, DefaultHttpClientProxy clientStaticHostFactory) {
		this.httpExecutor = httpExecutor;
		this.clientStaticHostFactory = clientStaticHostFactory;
	}

	@Override
	public FinalGetContentResponse getContent(GetContentParam getContentParam) {
		HttpClient httpClient = clientStaticHostFactory.getHttpClient();

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(PARAM, getContentParam.toJSONString());

		String result = httpExecutor.executePost(httpClient, GET_CONTENT_URI,
				paramMap);
		return FinalGetContentResponse.fromJsonString(result);
	}

}
