package com.nd.rock.common.net.bean.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GetContentParam extends AbstractJSONStringAble {

	private Map<String, List<String>> paramMap = null;

	public Map<String, List<String>> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, List<String>> paramMap) {
		this.paramMap = paramMap;
	}

	public static GetContentParam fromJsonStr(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return (GetContentParam) JSONObject.toBean(jsonObject,
				GetContentParam.class);
	}
}
