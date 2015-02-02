package com.nd.rock.server.controller.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.nd.rock.common.util.QStringUtil;
import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.view.request.RequestPreHandler;
import com.nd.rock.server.view.request.impl.BaseParamPreHandler;
import com.nd.rock.server.view.request.impl.ParamNameHandler;
import com.nd.rock.server.view.request.impl.UrlPreHandler;
import com.nd.rock.server.view.response.SendRedirectBehavior;
import com.nd.rock.server.view.response.impl.TranscodingSendRedirect;

public abstract class AbstractController {
	
	public static final String MESSAGE_KEY = "message";
	public static final String ERROR_MESSAGE_KEY = "error_message";

	@Autowired
	protected CoreDataDAO coreDataDAO;

	protected RequestPreHandler urlPreHandler = new UrlPreHandler();
	protected RequestPreHandler baseParamPreHandler = new BaseParamPreHandler();
	protected RequestPreHandler messagePreHandler = new ParamNameHandler(ActionController.MESSAGE_KEY);
	protected RequestPreHandler errorMessagePreHandler = new ParamNameHandler(ActionController.ERROR_MESSAGE_KEY);

	protected boolean isArgsEmpty(StringBuilder messageBuilder, Map<String, String> argMap) {
		for(Map.Entry<String, String> entry : argMap.entrySet()) {
			if(QStringUtil.nullOrEmpty(entry.getValue())) {
				messageBuilder.append("参数 '" + entry.getKey() + "' 不能为空！");
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 执行某个操作失败，使用directTo方法跳转到错误页面
	 * @param response
	 * @param errorMessage
	 * @return
	 */
	protected String directToError(HttpServletResponse response, String errorMessage) {
		StringBuilder url = buildUrl("view", "error.html", new HashMap<String, String>());
		appendMessage(url, ERROR_MESSAGE_KEY, errorMessage);
		SendRedirectBehavior sendRedirectBehavior = new TranscodingSendRedirect(response);
		try {
			sendRedirectBehavior.sendRedirect(url.toString());
		} catch (IOException e) {
			// TODO 
		}
		return null;
	}

	/**
	 * 执行某一项操作成功，使用directTo方法跳转到新的页面
	 * @param response
	 * @param module
	 * @param target
	 * @param args
	 * @param message
	 * @return
	 */
	protected String directTo(HttpServletResponse response, String module,
			String target, Map<String, String> args, String message) {
		StringBuilder url = buildUrl(module, target, args);
		if(!QStringUtil.nullOrEmpty(message)) {
			url = appendMessage(url, MESSAGE_KEY, message);
		}
		SendRedirectBehavior sendRedirectBehavior = new TranscodingSendRedirect(response);
		try {
			sendRedirectBehavior.sendRedirect(url.toString());
		} catch (IOException e) {
			// 转向错误页面 directTo
		}
		return null;
	}
	
	private StringBuilder buildUrl(String module,
			String target, Map<String, String> args) {
		StringBuilder url = new StringBuilder("/rock");
		url.append("/");
		url.append(module);
		url.append("/");
		url.append(target);
		url.append("?");
		for (Map.Entry<String, String> entry : args.entrySet()) {
			appendMessage(url, entry.getKey(), entry.getValue());
		}
		return url;
	}
	
	/**
	 * @param url
	 * @param message
	 * @return
	 */
	private StringBuilder appendMessage(StringBuilder url, String paramName, String paramValue) {
		url.append("&");
		url.append(paramName);
		url.append("=");
		url.append(paramValue);
		return url;
	}
}
