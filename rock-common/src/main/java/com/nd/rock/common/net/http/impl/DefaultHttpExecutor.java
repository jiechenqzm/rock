package com.nd.rock.common.net.http.impl;

import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import com.nd.rock.common.exception.NetAccessException;
import com.nd.rock.common.net.http.HttpExecutor;

public class DefaultHttpExecutor implements HttpExecutor {

	@Override
	public String executePost(HttpClient httpClient, String uri,
			Map<String, String> paramMap) {

		PostMethod postMethod = buildPostMethod(uri, paramMap);
		String result = null;
		try {
			if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK) {
				result = postMethod.getResponseBodyAsString();
			} else {
				postMethod.abort();
				String errorMsg = "DoPostMethod Failed: "
						+ postMethod.getStatusText() + "! Code >> "
						+ postMethod.getStatusCode();
				throw new NetAccessException(errorMsg);
			}
		} catch (IOException e) {
			String errorMsg = "DoPostMethod Error: " + e.getMessage();
			throw new NetAccessException(errorMsg, e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	private PostMethod buildPostMethod(String uri, Map<String, String> paramMap) {
		PostMethod postMethod = new PostMethod(uri);
		postMethod.getParams().setContentCharset(CHARACTER_ENCODING_DEFAULT);
		for (Map.Entry<String, String> enry : paramMap.entrySet()) {
			postMethod.setParameter(enry.getKey(), enry.getValue());
		}
		return postMethod;
	}

}
