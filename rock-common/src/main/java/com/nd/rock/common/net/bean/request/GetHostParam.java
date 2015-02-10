package com.nd.rock.common.net.bean.request;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.impl.AbstractJSONStringAble;

public class GetHostParam extends AbstractJSONStringAble {
	
	// JSON-LIB构造bean对象需要午参构造函数
	public GetHostParam() {}
	
	public static GetHostParam fromJsonString(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return fromJsonObject(jsonObject);
	}

	public static GetHostParam fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		return (GetHostParam) JSONObject.toBean(jsonObject,
				GetHostParam.class);
	}
}
