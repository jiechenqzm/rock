package com.nd.rock.common.net.bean.response.base;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.util.QJSONUtil;

public class ContentResponse {

	private Map<String/*group*/, Map<String/*dataId*/, String/*content*/>> content = null;
	
	// JSON-LIB构造bean对象需要午参构造函数
	public ContentResponse() {}
	
	public ContentResponse(Map<String, Map<String, String>> content) {
		this.content = content;
	}

	public Map<String, Map<String, String>> getContent() {
		return content;
	}

	public void setContent(Map<String, Map<String, String>> content) {
		this.content = content;
	}

	public static ContentResponse fromJsonString(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return fromJsonObject(jsonObject);
	}
	
	public static ContentResponse fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		Map<String, JSONObject> propertyMap = QJSONUtil.parse2JSONObject(jsonObject);
		return new ContentResponse(QJSONUtil.parse2MapMap(propertyMap.get("content")));
	}
	
}