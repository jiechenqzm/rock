package com.nd.rock.server.controller.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nd.rock.common.net.bean.impl.GetContentParam;

@Controller
@RequestMapping("/api")
public class ApiService extends AbstractApiController {
	
	/**
	 * 获取数据
	 */
	@RequestMapping(value = "/getContent.do", method = RequestMethod.GET)
	public void doGetContent(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "param", required = true) String param,
			ModelMap modelMap) {
		try {
			GetContentParam getContentParam = GetContentParam.fromJsonStr(param);
			
			//TODO CHECK!

			doResponse(response, null);
		} catch (Exception e) {
			super.doErrorResponse(response, "DoGetContent Error.", e);
		}
	}

	
	
}
