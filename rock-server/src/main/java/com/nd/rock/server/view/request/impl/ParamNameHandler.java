package com.nd.rock.server.view.request.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.nd.rock.server.view.request.RequestPreHandler;


/**
 * 
 * 
 * @author QiuZongming
 *
 */
public class ParamNameHandler implements RequestPreHandler {
	
	private String paramName = null;
	
	public ParamNameHandler(String paramName) {
		this.paramName = paramName;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			if(entry.getKey().equals(this.paramName)) {
				modelMap.addAttribute(this.paramName, entry.getValue()[0]);
				break;
			}
		}
	}

}
