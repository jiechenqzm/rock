package com.nd.rock.server.view.response.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.server.view.response.SendRedirectBehavior;
import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;
import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_ISO88591;;



public class TranscodingSendRedirect implements SendRedirectBehavior {
	
    private static final Logger logger = LoggerFactory.getLogger(TranscodingSendRedirect.class);
	
	private HttpServletResponse response = null;
	
	private String oriCharset = CHARACTER_ENCODING_DEFAULT;
	
	private String tarCharset = CHARACTER_ENCODING_ISO88591;
	
	public TranscodingSendRedirect(HttpServletResponse response) {
		this.response = response;
	}
	
	public TranscodingSendRedirect(HttpServletResponse response, String oriCharset, String tarCharset) {
		this.response = response;
		this.oriCharset = oriCharset;
		this.tarCharset = tarCharset;
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		try {
			this.response.sendRedirect(new String(location.getBytes(oriCharset), tarCharset));
		} catch (UnsupportedEncodingException e) {
			logger.error("Transcoding Error: From " + oriCharset + " To " + tarCharset, e);
		}
	}

}
