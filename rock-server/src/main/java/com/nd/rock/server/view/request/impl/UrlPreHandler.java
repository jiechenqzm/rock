package com.nd.rock.server.view.request.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.nd.rock.server.view.request.RequestPreHandler;

/**
 * 
 * 将当前请求的url信息放到map中
 * @author QiuZongming
 *
 */
public class UrlPreHandler implements RequestPreHandler {
	
	private static final String REQUEST_URL = "requestURL";

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute(REQUEST_URL, request.getRequestURL().toString());
	}
	
}
