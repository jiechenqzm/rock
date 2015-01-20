package com.nd.rock.server.controller.manager;

import java.util.Arrays;
import java.util.List;

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
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
    private CoreDataDAO coreDataDAO;

	/**
	 * 模糊查询APP
	 */
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public String searchData(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			
			@RequestParam(value = "group", required = false, defaultValue = "DEFAULT_GROUP") String group,
			@RequestParam(value = "dataId", required = false, defaultValue = "%") String dataId,
			ModelMap modelMap) {

		List<CoreDataIn> data = null;
		if(dataId.startsWith("%") || dataId.endsWith("%")){
			data = coreDataDAO.fuzzyQuery(group, dataId);
		}
		else {
			CoreDataIn coreDataIn = coreDataDAO.query(group, dataId);
			data = Arrays.asList(coreDataIn);
		}

		modelMap.addAttribute("pageNo", pageNo);
		modelMap.addAttribute("pageSize", pageSize);
		modelMap.addAttribute("group", group);
		modelMap.addAttribute("dataId", dataId);
		modelMap.addAttribute("data", data);
		
		return "search";
	}
}
