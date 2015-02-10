package com.nd.rock.server.net.http;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.nd.rock.common.net.bean.request.CheckSummaryParam;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalCheckSummaryResponse;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;
import com.nd.rock.common.net.bean.response.base.ContentResponse;
import com.nd.rock.common.net.bean.response.base.SummaryResponse;

public class ServiceHttpTest extends BaseFunction {
	
	private static final String GROUP = "TEST_GROUP_HTTP";
	private static final String GROUP2 = "TEST_GROUP_HTTP2";
	
	@Test
	public void getContent(){
		Map<String,List<String>> param = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("chinese.value");
		list.add("english.value");
		list.add("中文的dataId");
		param.put(GROUP, list);
		
		list = new ArrayList<>();
		list.add("not.exist");
		param.put(GROUP2, list);

		GetContentParam getContentParam = new GetContentParam(param);
		
		HttpClient httpClient = getHttpClient();
		PostMethod postMethod = new PostMethod("/rock/client/getContent.do");
		postMethod.getParams().setContentCharset("UTF-8");
		postMethod.setParameter("param", getContentParam.toJSONString());
		
		FinalGetContentResponse response = null;
		
		try {
			String result = URLDecoder.decode(realRequest(httpClient, postMethod), "UTF-8");
//			String result = realRequest(httpClient, postMethod);
			response = FinalGetContentResponse.fromJsonString(result);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertNotNull(response);
		ContentResponse returnContentParam = response.getResponseBody();
		Map<String, Map<String, String>> resultMap = returnContentParam.getContent();


		Assert.assertEquals(2, resultMap.size());
		Map<String, String> map = resultMap.get(GROUP);
		Assert.assertNotNull(map);
		Assert.assertEquals(3, map.size());
	
		Assert.assertTrue(map.containsKey("chinese.value"));
		Assert.assertTrue(map.containsKey("english.value"));
		Assert.assertTrue(map.containsKey("中文的dataId"));
		
		Assert.assertEquals("这是一个中文的内容。", map.get("chinese.value"));
		Assert.assertEquals("this is english content.", map.get("english.value"));
		Assert.assertEquals("一个普通的值。", map.get("中文的dataId"));
		
		
		map = resultMap.get(GROUP2);
		
		Assert.assertNotNull(map);
		Assert.assertEquals(0, map.size());
	}
	
	
	@Test
	public void checkSummary(){
		Map<String,Map<String, String>> param = new HashMap<>();
		
		Map<String, String> map = new HashMap<>();
		map.put("chinese.value", "e84baa41b47fa91dde4819dcb0692845");
		map.put("english.value", null);
		map.put("error.summary", "sorry,i don't know.");
		param.put(GROUP, map);
		
		map = new HashMap<>();
		map.put("not.exist", "e84baa41b47fa91dde4819dcb0692845");
		param.put(GROUP2, map);
		CheckSummaryParam checkSummaryParam = new CheckSummaryParam(param); 

		HttpClient httpClient = getHttpClient();
		PostMethod postMethod = new PostMethod("/rock/client/checkSummary.do");
		postMethod.getParams().setContentCharset("UTF-8");
		postMethod.setParameter("param", checkSummaryParam.toJSONString());
		
		FinalCheckSummaryResponse response = null;
		try {
			String result = URLDecoder.decode(realRequest(httpClient, postMethod), "UTF-8");
			response = FinalCheckSummaryResponse.fromJsonString(result);	
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(response);
		SummaryResponse summaryResponse = response.getResponseBody();
		Map<String, List<String>> resultMap = summaryResponse.getContent();
		
		Assert.assertEquals(2, resultMap.size());
		List<String> list = resultMap.get(GROUP);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(list.contains("english.value"));
		Assert.assertTrue(list.contains("error.summary"));
		
		list = resultMap.get(GROUP2);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertTrue(list.contains("not.exist"));
	}
	
	private HttpClient getHttpClient(){
		HttpClient httpClient = createHttpClient("localhost", 80, 3*1000, 5*60*1000);
		//		HttpClient httpClient = createHttpClient("http://192.168.181.65", 80, 3*1000, 5*60*1000);
		return httpClient;
	}

}
