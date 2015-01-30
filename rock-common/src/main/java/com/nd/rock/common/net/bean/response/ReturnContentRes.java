package com.nd.rock.common.net.bean.response;

import net.sf.json.JSONObject;


public class ReturnContentRes extends CommonResBody<ContentRes> {
	
	public static ReturnContentRes fromJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return fromJsonObject(jsonObject);
	}
	
	public static ReturnContentRes fromJsonObject(JSONObject jsonObject){
		ReturnContentRes result = new ReturnContentRes();
		result.fromJsonObject2Common(jsonObject);
		JSONObject responseBodyJson = jsonObject.getJSONObject("responseBody");
		ContentRes contentRes = ContentRes.fromJsonObject(responseBodyJson);
		result.setResponseBody(contentRes);
		return result;
	}

}
