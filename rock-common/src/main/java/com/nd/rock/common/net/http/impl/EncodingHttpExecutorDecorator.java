package com.nd.rock.common.net.http.impl;

import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.common.exception.runtime.CharacterEncodingException;
import com.nd.rock.common.net.http.HttpExecutor;

public class EncodingHttpExecutorDecorator implements HttpExecutor {
	
	private String characterEncoding = CHARACTER_ENCODING_DEFAULT;
	
	private HttpExecutor httpExecutor = null;
	
	public EncodingHttpExecutorDecorator(HttpExecutor httpExecutor) {
		this.httpExecutor = httpExecutor;
	}

	@Override
	public String executePost(HttpClient httpClient, String uri,
			Map<String, String> paramMap) {

		String result = this.httpExecutor.executePost(httpClient, uri, paramMap);
		try {
			return URLDecoder.decode(result, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new CharacterEncodingException(e);
		}
	}

}
