package com.nd.rock.common.net.bean.impl;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GetContentParam extends AbstractJSONStringAble {

	private Map<String, SummaryContent> paramMap = null;

	public Map<String, SummaryContent> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, SummaryContent> paramMap) {
		this.paramMap = paramMap;
	}

	public static GetContentParam fromJsonStr(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return (GetContentParam) JSONObject.toBean(jsonObject,
				GetContentParam.class);
	}
}
