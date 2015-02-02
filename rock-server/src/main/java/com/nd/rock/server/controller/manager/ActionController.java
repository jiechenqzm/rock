package com.nd.rock.server.controller.manager;

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

import com.nd.rock.server.model.container.CoreDataObservable;
import com.nd.rock.server.model.instance.CoreDataIn;

/**
 * @author QiuZongming
 *
 */
@Controller
@RequestMapping("/action")
public class ActionController extends AbstractController {

	@Autowired
	private CoreDataObservable coreDataObservable;
	
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
		argMap.put("数据分组(GROUP)", group);
		argMap.put("数据名称(DATA_ID)", dataId);
		argMap.put("数据内容(CONTENT)", newContent);
		StringBuilder messageBuilder = new StringBuilder();
		if(super.isArgsEmpty(messageBuilder, argMap)) {
			super.directToError(response, messageBuilder.toString());
			return null;
		}
		
		/***** 开始业务逻辑操作 *****/
		if (this.coreDataDAO.logicUpdate(group, dataId, oriVersion, newContent,
				CoreDataIn.calculateSummary(newContent)) == 0) {
			directToError(response, "更新数据失败, 数据不存在或者被更新！");
			return null;
		}

		/***** 通知观察者 *****/
		this.coreDataObservable.notifyObservers(group, dataId);
		
		/***** 页面跳转 *****/
		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", dataId);
		return directTo(response, "view", "detail.html", directToArgs, "更新数据成功。");
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
		argMap.put("数据分组(GROUP)", group);
		argMap.put("数据名称(DATA_ID)", dataId);
		StringBuilder messageBuilder = new StringBuilder();
		if(super.isArgsEmpty(messageBuilder, argMap)) {
			super.directToError(response, messageBuilder.toString());
			return null;
		}
		
		/***** 开始业务逻辑操作 *****/
		if (coreDataDAO.logicDelete(group, dataId, version) == 0) {
			directToError(response, "删除数据失败, 数据不存在或者被更新！");
			return null;
		}

		/***** 通知观察者 *****/
		this.coreDataObservable.notifyObservers(group, dataId);
		
		/***** 页面跳转 *****/
		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", "%");
		return directTo(response, "view", "search.html", directToArgs, "删除数据成功。");
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
		argMap.put("数据分组(GROUP)", group);
		argMap.put("数据名称(DATA_ID)", dataId);
		argMap.put("数据内容(CONTENT)", content);
		StringBuilder messageBuilder = new StringBuilder();
		if(super.isArgsEmpty(messageBuilder, argMap)) {
			super.directToError(response, messageBuilder.toString());
			return null;
		}
		
		
		/***** 开始业务逻辑操作 *****/
		CoreDataIn.CoreDataBuilder builder = new CoreDataIn.CoreDataBuilder();
		builder.setGroup(group);
		builder.setDataId(dataId);
		builder.setSummary(CoreDataIn.calculateSummary(content));
		builder.setContent(content);
		if(coreDataDAO.logicInsert(builder.build()) == 0){
			directToError(response, "新增数据失败, 数据已存在或者被更新！");
			return null;
		}
		
		/***** 通知观察者 *****/
		this.coreDataObservable.notifyObservers(group, dataId);
		
		/***** 页面跳转 *****/
		Map<String, String> directToArgs = new HashMap<>();
		directToArgs.put("group", group);
		directToArgs.put("dataId", dataId);
		return directTo(response, "view", "detail.html", directToArgs, "新增数据公共。");
	}

}
