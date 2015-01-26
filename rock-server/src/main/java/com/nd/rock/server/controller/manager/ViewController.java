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

import com.nd.rock.common.util.QStringUtil;
import com.nd.rock.server.model.instance.CoreDataIn;
import com.nd.rock.server.view.page.PageItems;

@Controller
@RequestMapping("/view")
public class ViewController extends AbstractController {

	/**
	 * 首页
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String viewIndex(
			HttpServletRequest request,ModelMap modelMap) {
		return "index";
	}
	
	private static final String DEFAULT_GROUP = "DEFAULT_GROUP";
	private static final String DEFAULT_DATA_ID = "%";
	
	/**
	 * 模糊查询数据
	 */
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public String viewSearch(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			@RequestParam(value = "group", required = false) String group,
			@RequestParam(value = "dataId", required = false) String dataId,
			ModelMap modelMap) {
		
		super.urlPreHandler.handle(request, response, modelMap);
		super.baseParamPreHandler.handle(request, response, modelMap);
		super.messagePreHandler.handle(request, response, modelMap);

		String realGroup = defaultValueIfNull(group, DEFAULT_GROUP);
		String realDataId = defaultValueIfNull(dataId, DEFAULT_DATA_ID);
		PageItems<CoreDataIn> page = coreDataDAO.pageFuzzyQueryData(realGroup, realDataId, pageNo, pageSize);

		modelMap.addAttribute("group", group);
		modelMap.addAttribute("dataId", dataId);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("data", page.getItems());
		
		return "search";
	}
	
	private String defaultValueIfNull(String value, String defaultValue){
		return QStringUtil.nullOrEmpty(value)? defaultValue : value;
	}

	/**
	 * 精确查询数据
	 */
	@RequestMapping(value = "/detail.html", method = RequestMethod.GET)
	public String viewDetail(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
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
		
		this.messagePreHandler.handle(request, response, modelMap);
		CoreDataIn data = coreDataDAO.query(group, dataId);
		modelMap.addAttribute("data", data);
		return "detail";
	}
	
	/**
	 * 查询数据更新
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String viewUpdate(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
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
		
		this.messagePreHandler.handle(request, response, modelMap);
		CoreDataIn data = coreDataDAO.query(group, dataId);
		modelMap.addAttribute("data", data);
		return "update";
	}
	
	/**
	 * 新增数据
	 */
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String viewAdd(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) {
		return "add";
	}

	/**
	 * 错误信息展示
	 */
	@RequestMapping(value = "/error.html", method = RequestMethod.GET)
	public String viewError(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) {
		super.errorMessagePreHandler.handle(request, response, modelMap);
		return "error";
	}
	
}
