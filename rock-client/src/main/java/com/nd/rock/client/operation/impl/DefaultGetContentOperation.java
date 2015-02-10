package com.nd.rock.client.operation.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.client.factory.ClientStaticHostFactory;
import com.nd.rock.client.operation.GetContentOperation;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;
import com.nd.rock.common.net.http.HttpExecutor;
import com.nd.rock.common.net.http.impl.DefaultHttpExecutor;
import com.nd.rock.common.net.http.impl.EncodingHttpExecutorDecorator;

public class DefaultGetContentOperation implements GetContentOperation {

	private HttpExecutor httpExecutor = null;

	public DefaultGetContentOperation() {
		this.httpExecutor = new EncodingHttpExecutorDecorator(
				new DefaultHttpExecutor());
	}

	@Override
	public FinalGetContentResponse getContent(GetContentParam getContentParam) {
		HttpClient httpClient = ClientStaticHostFactory.getHttpClient();

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(PARAM, getContentParam.toJSONString());

		String result = httpExecutor.executePost(httpClient, GET_CONTENT_URI,
				paramMap);
		return FinalGetContentResponse.fromJsonString(result);
	}

}
