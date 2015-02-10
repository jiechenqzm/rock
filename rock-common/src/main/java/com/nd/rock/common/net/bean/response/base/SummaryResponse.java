package com.nd.rock.common.net.bean.response.base;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.util.QJSONUtil;

public class SummaryResponse {

	private Map<String/*group*/, List<String/*dataId*/>> content = null;
	
	// JSON-LIB构造bean对象需要午参构造函数
	public SummaryResponse() {}
	
	public SummaryResponse(Map<String, List<String>> content) {
		this.content = content;
	}

	public Map<String, List<String>> getContent() {
		return content;
	}

	public void setContent(Map<String, List<String>> content) {
		this.content = content;
	}

	public static SummaryResponse fromJsonString(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return fromJsonObject(jsonObject);
	}
	
	public static SummaryResponse fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		
		Map<String, JSONObject> propertyMap = QJSONUtil.parse2JSONObject(jsonObject);
		return new SummaryResponse(QJSONUtil.parse2MapList(propertyMap.get("content")));
	}
	
}