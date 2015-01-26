package com.nd.rock.server.controller.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.model.instance.CoreDataIn;

@Controller
@RequestMapping("/action")
public class ActionController {

	@Autowired
	private CoreDataDAO coreDataDAO;

	/**
	 * 精确查询数据
	 */
	@RequestMapping(value = "/doUpdate.html", method = RequestMethod.POST)
	public String doUpdate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
			@RequestParam(value = "version", required = true) long oriVersion,
			@RequestParam(value = "content", required = true) String newContent,
			ModelMap modelMap) {

		int result = coreDataDAO.update(group, dataId, oriVersion, newContent,
				CoreDataIn.calculateSummary(newContent));
		if (result == 0) {
			// 转向错误页面 directTo
			return "error";
		}

		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", dataId);
		return directTo(response, "view", "detail.html", directToArgs);
	}
	
	@RequestMapping(value = "/doDelete.html", method = RequestMethod.POST)
	public String doDelete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
			@RequestParam(value = "version", required = true) long version,
			ModelMap modelMap) {

		int result = coreDataDAO.delete(group, dataId, version);
		if (result == 0) {
			// 转向错误页面 directTo
			return "error";
		}

		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", "%");
		return directTo(response, "view", "search.html", directToArgs);
	}
	

	private String directTo(HttpServletResponse response, String module,
			String target, Map<String, String> args) {
		StringBuilder url = new StringBuilder("/rock");
		url.append("/");
		url.append(module);
		url.append("/");
		url.append(target);
		url.append("?");
		for (Map.Entry<String, String> entry : args.entrySet()) {
			url.append("&");
			url.append(entry.getKey());
			url.append("=");
			url.append(entry.getValue());
		}

		String directTo = "detail";
		try {
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			directTo = "error";
		}
		return directTo;
	}
}
