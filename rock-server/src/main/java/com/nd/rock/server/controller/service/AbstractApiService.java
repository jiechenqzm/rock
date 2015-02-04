package com.nd.rock.server.controller.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.net.bean.JSONStringAble;
import com.nd.rock.common.net.bean.response.CommonResponseBody;

import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;

public class AbstractApiService {

	private static final Logger logger = LoggerFactory.getLogger("apiLog");

	protected void doErrorResponse(ServletResponse response, String message,
			Exception e) {
		logger.error(message, e);
		doResponse(response, CommonResponseBody.fail(message + e.getMessage()));
	}

	protected void doResponse(ServletResponse response,
			JSONStringAble responseBody) {
		try {
			PrintWriter writer = response.getWriter();
			writer.write(URLEncoder.encode(responseBody.toJSONString(),
					CHARACTER_ENCODING_DEFAULT));
//			writer.write(responseBody.toJSONString());
		} catch (IOException e) {
			this.recordErrorInLog("Response Writer Error.", e);
		}
	}

	private void recordErrorInLog(String message, Exception e) {
		logger.error(message, e);
	}
}
