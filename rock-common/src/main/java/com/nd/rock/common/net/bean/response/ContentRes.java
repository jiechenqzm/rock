package com.nd.rock.common.net.bean.response;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ContentRes {

	private Map<String/*group*/, Map<String/*dataId*/, String/*content*/>> content = null;
	
	// JSON-LIB构造bean对象需要午参构造函数
	public ContentRes() {}
	
	public ContentRes(Map<String, Map<String, String>> content) {
		this.content = content;
	}

	public Map<String, Map<String, String>> getContent() {
		return content;
	}

	public void setContent(Map<String, Map<String, String>> content) {
		this.content = content;
	}

	public static ContentRes fromJsonString(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return fromJsonObject(jsonObject);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ContentRes fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		Map<String, Map<String, String>> content = new HashMap<String, Map<String, String>>();
		JSONObject contentJson = jsonObject.getJSONObject("content");
		for(Iterator iterator = contentJson.keys() ; iterator.hasNext(); ) {
			String key = (String)iterator.next();
			JSONObject groupJson = contentJson.getJSONObject(key);
			Map<String, String> map = (Map<String, String>)JSONObject.toBean(groupJson, Map.class);
			content.put(key, map);
		}
		return new ContentRes(contentJson);
	}
	
}