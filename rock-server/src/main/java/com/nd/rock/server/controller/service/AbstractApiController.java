package com.nd.rock.server.controller.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.net.bean.JSONStringAble;
import com.nd.rock.common.net.bean.impl.DefaultResponseBody;

public class AbstractApiController {
	
    private static Logger logger = LoggerFactory.getLogger("apiFile");

	
	protected void doErrorResponse(ServletResponse response, String message, Exception e){
		logger.error(message, e);
		doResponse(response, DefaultResponseBody.fail(message));
	}

	protected void doResponse(ServletResponse response,
			JSONStringAble responseBody) {
		try {
			PrintWriter writer = response.getWriter();
			writer.write(responseBody.toJSONString());
		} catch (IOException e) {
			this.recordErrorInLog("Response Writer Error.", e);
		}
	}

	private void recordErrorInLog(String message, Exception e) {
		logger.error(message, e);
	}
}
