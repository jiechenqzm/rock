package com.nd.rock.common.net.bean.request;

import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.impl.AbstractJSONStringAble;
import com.nd.rock.common.util.QJSONUtil;

public class CheckSummaryParam extends AbstractJSONStringAble {

	private Map<String, Map<String, String>> paramMap = null;
	
	// JSON-LIB构造bean对象需要午参构造函数
	public CheckSummaryParam() {}
	
	public CheckSummaryParam(Map<String, Map<String, String>> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, Map<String, String>> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Map<String, String>> paramMap) {
		this.paramMap = paramMap;
	}

	public static CheckSummaryParam fromJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return fromJsonObject(jsonObject);
	}
	
	public static CheckSummaryParam fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		CheckSummaryParam result = new CheckSummaryParam();
		JSONObject paramMapJson = jsonObject.getJSONObject("paramMap");
		result.setParamMap(QJSONUtil.parse2MapMap(paramMapJson));
		return result;
	}
}
