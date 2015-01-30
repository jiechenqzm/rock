package com.nd.rock.common.net.bean.impl;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class CopyOfGetContentParam extends AbstractJSONStringAble {

	private Map<String, SummaryContent> paramMap = null;

	public Map<String, SummaryContent> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, SummaryContent> paramMap) {
		this.paramMap = paramMap;
	}

	public static CopyOfGetContentParam fromJsonStr(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return (CopyOfGetContentParam) JSONObject.toBean(jsonObject,
				CopyOfGetContentParam.class);
	}
}
