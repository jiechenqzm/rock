package com.nd.rock.server.view.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

public interface RequestPreHandler {
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap);

}
