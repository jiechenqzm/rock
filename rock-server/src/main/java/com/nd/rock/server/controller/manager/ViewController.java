package com.nd.rock.server.controller.manager;

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
import com.nd.rock.server.view.page.PageItems;
import com.nd.rock.server.view.request.RequestPreHandler;
import com.nd.rock.server.view.request.impl.ParamPreHandler;
import com.nd.rock.server.view.request.impl.UrlPreHandler;

@Controller
@RequestMapping("/view")
public class ViewController {

	@Autowired
    private CoreDataDAO coreDataDAO;
	
	private RequestPreHandler urlPreHandler = new UrlPreHandler();
	private RequestPreHandler paramPreHandler = new ParamPreHandler();
	
	/**
	 * 首页
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String viewIndex(
			HttpServletRequest request,ModelMap modelMap) {
		return "index";
	}

	/**
	 * 模糊查询数据
	 */
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public String viewSearch(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			
			@RequestParam(value = "group", required = false, defaultValue = "DEFAULT_GROUP") String group,
			@RequestParam(value = "dataId", required = false, defaultValue = "%") String dataId,
			ModelMap modelMap) {
		
		this.urlPreHandler.handle(request, response, modelMap);
		this.paramPreHandler.handle(request, response, modelMap);

		PageItems<CoreDataIn> page = coreDataDAO.pageFuzzyQueryData(group, dataId, pageNo, pageSize);

		modelMap.addAttribute("group", group);
		modelMap.addAttribute("dataId", dataId);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("data", page.getItems());
		
		return "search";
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
		
		CoreDataIn data = coreDataDAO.query(group, dataId);
		
		modelMap.addAttribute("group", group);
		modelMap.addAttribute("dataId", dataId);
		modelMap.addAttribute("data", data);
		
		return "detail";
	}
	
	/**
	 * 精确查询数据
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String viewUpdate(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "group", required = true) String group,
			@RequestParam(value = "dataId", required = true) String dataId,
			ModelMap modelMap) {
		
		CoreDataIn data = coreDataDAO.query(group, dataId);
		
		modelMap.addAttribute("group", group);
		modelMap.addAttribute("dataId", dataId);
		modelMap.addAttribute("data", data);
		
		return "update";
	}
}
