package com.nd.rock.server.view.request.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.nd.rock.server.view.request.RequestPreHandler;

public class ParamPreHandler implements RequestPreHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("requestParams", request.getParameterMap());
	}

}
