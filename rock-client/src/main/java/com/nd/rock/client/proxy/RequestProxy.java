package com.nd.rock.client.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.client.host.impl.HostManagerWrapper;
import com.nd.rock.common.file.client.config.ClientHostFactory;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;
import com.nd.rock.common.net.host.HostManager;
import com.nd.rock.common.net.host.impl.ConstantHostManager;
import com.nd.rock.common.net.host.impl.HeartBeatCommand;
import com.nd.rock.common.net.host.impl.HostInLocal;
import com.nd.rock.common.net.host.impl.HostInServer;
import com.nd.rock.common.net.http.HttpClientFactory;
import com.nd.rock.common.net.http.HttpExecutor;

public class RequestProxy  {
	
	private HostManager hostManager = null;
	
	private HttpExecutor httpExecutor = null;
	
	private HttpClientFactory httpClientFactory = null;
	
	public RequestProxy() {

		
		

	}

	public Map<String, String> getContentBatch(String group, List<String> dataIds) {
		Map<String, List<String>> keyMap = new HashMap<>();
		keyMap.put(group, dataIds);
		GetContentParam getContentParam = new GetContentParam(keyMap);
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("param", getContentParam.toJSONString());

		

		HttpClient httpClient = httpClientFactory.getHttpClient(this.hostManager.getHost(null), 80);
		String result = httpExecutor.executePost(httpClient, "/rock/api/getContent.do", paramMap);
		
		
		
		
		
		FinalGetContentResponse response = FinalGetContentResponse.fromJsonString(result);
		return response.getResponseBody().getContent().get(group);
	}

}
