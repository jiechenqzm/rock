package com.nd.rock.server.controller.service;

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

import com.nd.rock.common.net.bean.ResponseBody;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.CommonResBody;
import com.nd.rock.common.net.bean.response.ContentRes;
import com.nd.rock.server.model.container.DataContainer;

@Controller
@RequestMapping("/api")
public class ApiService extends AbstractApiController {

	@Autowired
	private DataContainer snapshotFileContainer = null;

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
					.fromJsonStr(param);
			for (Map.Entry<String, List<String>> entry : getContentParam
					.getParamMap().entrySet()) {
				Map<String, String> groupResultMap = new HashMap<String, String>();
				for (String dataId : entry.getValue()) {
					groupResultMap.put(dataId,
							this.snapshotFileContainer.get(entry.getKey(), dataId));
				}
				resultMap.put(entry.getKey(), groupResultMap);
			}

			ResponseBody<ContentRes> responseBody = new CommonResBody<>(
					new ContentRes(resultMap));
			doResponse(response, responseBody);
		} catch (Exception e) {
			super.doErrorResponse(response, "DoGetContent Error.", e);
		}
	}

	public void setSnapshotFileContainer(DataContainer snapshotFileContainer) {
		this.snapshotFileContainer = snapshotFileContainer;
	}

}
