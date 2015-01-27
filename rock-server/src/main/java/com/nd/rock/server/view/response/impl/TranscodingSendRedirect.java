package com.nd.rock.server.view.response.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.server.view.response.SendRedirectBehavior;

public class TranscodingSendRedirect implements SendRedirectBehavior {
	
    private static Logger logger = LoggerFactory.getLogger(TranscodingSendRedirect.class);
	
	private HttpServletResponse response = null;
	
	private String oriCharset = "UTF-8";
	
	private String tarCharset = "ISO8859-1";
	
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
