package com.nd.rock.server.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nd.rock.server.model.instance.CoreDataIn;

@Controller
@RequestMapping("/action")
public class ActionController extends AbstractController {

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
		
		/***** 开始>>校验参数合法性的代码*****/
		Map<String, String> argMap = new HashMap<>();
		argMap.put("group", group);
		argMap.put("dataId", dataId);
		argMap.put("newContent", newContent);
		StringBuilder messageBuilder = new StringBuilder();
		if(super.isArgsEmpty(messageBuilder, argMap)) {
			super.directToError(response, messageBuilder.toString());
			return null;
		}
		/***** 结束>>校验参数合法性的代码*****/
		
		int result = coreDataDAO.update(group, dataId, oriVersion, newContent,
				CoreDataIn.calculateSummary(newContent));
		if (result == 0) {
			directToError(response, "更新数据失败，数据不存在或者被更新！");
			return null;
		}

		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", dataId);
		return directTo(response, "view", "detail.html", directToArgs, "修改成功");
	}
	
	@RequestMapping(value = "/doDelete.html", method = RequestMethod.POST)
	public String doDelete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
			@RequestParam(value = "version", required = true) long version,
			ModelMap modelMap) {
		
		/***** 开始>>校验参数合法性的代码*****/
		Map<String, String> argMap = new HashMap<>();
		argMap.put("group", group);
		argMap.put("dataId", dataId);
		StringBuilder messageBuilder = new StringBuilder();
		if(super.isArgsEmpty(messageBuilder, argMap)) {
			super.directToError(response, messageBuilder.toString());
			return null;
		}
		/***** 结束>>校验参数合法性的代码*****/
		
		int result = coreDataDAO.delete(group, dataId, version);
		if (result == 0) {
			directToError(response, "删除数据失败，数据不存在或者被更新！");
			return null;
		}

		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", "%");
		return directTo(response, "view", "search.html", directToArgs, "删除成功");
	}
	
	@RequestMapping(value = "/doAdd.html", method = RequestMethod.POST)
	public String doAdd(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
			@RequestParam(value = "content", required = true) String content,
			ModelMap modelMap) {
		
		/***** 开始>>校验参数合法性的代码*****/
		Map<String, String> argMap = new HashMap<>();
		argMap.put("group", group);
		argMap.put("dataId", dataId);
		argMap.put("content", content);
		StringBuilder messageBuilder = new StringBuilder();
		if(super.isArgsEmpty(messageBuilder, argMap)) {
			super.directToError(response, messageBuilder.toString());
			return null;
		}
		/***** 结束>>校验参数合法性的代码*****/

		CoreDataIn existIn = coreDataDAO.query(group, dataId);
		if (existIn != null) {
			directToError(response, "新增数据失败，数据已存在！");
			return null;
		}
		
		CoreDataIn.CoreDataBuilder builder = new CoreDataIn.CoreDataBuilder();
		builder.setGroup(group);
		builder.setDataId(dataId);
		builder.setSummary(CoreDataIn.calculateSummary(content));
		builder.setContent(content);
		
		int result = coreDataDAO.insert(builder.build());
		if (result == 0) {
			directToError(response, "新增数据失败，插入失败！");
			return null;
		}
		
		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", dataId);
		return directTo(response, "view", "detail.html", directToArgs, "新增成功");
	}
	
}
