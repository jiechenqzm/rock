package com.nd.rock.server.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nd.rock.common.net.bean.request.CheckSummaryParam;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalCheckSummaryResponse;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;
import com.nd.rock.common.net.bean.response.base.ContentResponse;
import com.nd.rock.common.net.bean.response.base.SummaryResponse;
import com.nd.rock.server.controller.service.behavior.CheckContentBehavior;
import com.nd.rock.server.controller.service.behavior.GetContentBehavior;

/**
 * 
 * CLIENT发送过来的关于数据内容的请求的处理类
 * @author QiuZongming
 *
 */
@Controller
@RequestMapping("/client")
public class ContentService extends AbstractService {

	@Autowired
	private GetContentBehavior getContentBehavior = null;

	@Autowired
	private CheckContentBehavior checkContentBehavior = null;

	/**
	 * 获取数据
	 */
	@RequestMapping(value = "/getContent.do", method = RequestMethod.POST)
	public void doGetContent(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "param", required = true) String param,
			ModelMap modelMap) {
		Map<String, Map<String, String>> resultMap = new HashMap<>();
		try {
			GetContentParam getContentParam = GetContentParam
					.fromJsonString(param);
			for (Map.Entry<String, List<String>> entry : getContentParam
					.getParamMap().entrySet()) {
				Map<String, String> groupResultMap = new HashMap<String, String>();
				for (String dataId : entry.getValue()) {
					String content = this.getContentBehavior.get(
							entry.getKey(), dataId);
					if (content != null)
						groupResultMap.put(dataId, content);
				}
				resultMap.put(entry.getKey(), groupResultMap);
			}
			FinalGetContentResponse responseBody = new FinalGetContentResponse(
					new ContentResponse(resultMap));
			doResponse(response, responseBody);
		} catch (Exception e) {
			super.doErrorResponse(response, "DoGetContent Error.", e);
		}
	}

	/**
	 * 轮询变更
	 */
	@RequestMapping(value = "/checkSummary.do", method = RequestMethod.POST)
	public void doCheckSummary(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "param", required = true) String param,
			ModelMap modelMap) {
		Map<String, List<String>> resultMap = new HashMap<>();
		try {
			CheckSummaryParam checkSummaryParam = CheckSummaryParam
					.fromJsonString(param);

			for (Map.Entry<String, Map<String, String>> entry : checkSummaryParam
					.getParamMap().entrySet()) {
				List<String> list = new ArrayList<>();
				for (Map.Entry<String, String> entry2 : entry.getValue()
						.entrySet()) {
					if (!this.checkContentBehavior.check(entry.getKey(),
							entry2.getKey(), entry2.getValue())) {
						list.add(entry2.getKey());
					}
				}
				resultMap.put(entry.getKey(), list);
			}

			FinalCheckSummaryResponse responseBody = new FinalCheckSummaryResponse(
					new SummaryResponse(resultMap));
			doResponse(response, responseBody);
		} catch (Exception e) {
			super.doErrorResponse(response, "DoGetContent Error.", e);
		}
	}

}
