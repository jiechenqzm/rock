package com.nd.rock.common.net.bean.impl;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ReturnContentParam {

	private Map<String/*group*/, Map<String/*dataId*/, String/*content*/>> content = null;
	
	public ReturnContentParam(Map<String, Map<String, String>> content) {
		this.content = content;
	}

	public Map<String, Map<String, String>> getContent() {
		return content;
	}

	public void setContent(Map<String, Map<String, String>> content) {
		this.content = content;
	}

	public static ReturnContentParam fromJsonStr(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return (ReturnContentParam) JSONObject.toBean(jsonObject,
				ReturnContentParam.class);
	}
}
