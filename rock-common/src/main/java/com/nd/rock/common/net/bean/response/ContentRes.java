package com.nd.rock.common.net.bean.response;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.util.QJSONUtil;

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
	
	public static ContentRes fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		return new ContentRes(QJSONUtil.parse2MapMap(jsonObject));
	}
	
}