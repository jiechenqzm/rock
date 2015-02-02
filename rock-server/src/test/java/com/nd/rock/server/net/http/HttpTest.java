package com.nd.rock.server.net.http;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Test;

import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.ContentRes;
import com.nd.rock.common.net.bean.response.ReturnContentRes;

public class HttpTest {
	
	@Test
	public void httpGetTest(){
		Map<String,List<String>> param = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("mingchaonaxieshier");
		list.add("com.nd.bigdata.distribute.mysql");
		param.put("DEFAULT_GROUP", list);
		
		list = new ArrayList<>();
		list.add("abc_key");
		param.put("TEST_GROUP", list);

		GetContentParam getContentParam = new GetContentParam(param);
		
		HttpClient httpClient = createHttpClient("localhost", 80, 3*1000, 5*60*1000);
		PostMethod postMethod = new PostMethod("/rock/api/getContent.do");
		postMethod.setParameter("param", getContentParam.toJSONString());
		
		try {
			String result = URLDecoder.decode(realRequest(httpClient, postMethod), "UTF-8");

			ReturnContentRes response = ReturnContentRes.fromJsonString(result);			
			
			ContentRes returnContentParam = response.getResponseBody();
			
			
			Map<String, Map<String, String>> map = returnContentParam.getContent();
			for(Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
				for(Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
					System.out.println(entry.getKey() + "\t" + entry2.getKey() + "\t" + entry2.getValue());
				}
			}
			
			System.out.println(returnContentParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String realRequest(HttpClient httpClient, PostMethod postMethod) throws Exception {
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
	
	private HttpClient createHttpClient(String host, int port,
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
