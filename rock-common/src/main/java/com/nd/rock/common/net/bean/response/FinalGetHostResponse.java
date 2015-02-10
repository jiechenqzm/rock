package com.nd.rock.common.net.bean.response;

import com.nd.rock.common.net.bean.response.base.HostResponse;

import net.sf.json.JSONObject;


public class FinalGetHostResponse extends CommonResponseBody<HostResponse> {
	
	public FinalGetHostResponse() {
	}
	
	public FinalGetHostResponse(HostResponse hostResponse) {
		super(hostResponse);
	}
	
	public static FinalGetHostResponse fromJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return fromJsonObject(jsonObject);
	}
	
	public static FinalGetHostResponse fromJsonObject(JSONObject jsonObject){
		FinalGetHostResponse result = new FinalGetHostResponse();
		result.fromJsonObject2Common(jsonObject);
		JSONObject responseBodyJson = jsonObject.getJSONObject("responseBody");
		HostResponse content = HostResponse.fromJsonObject(responseBodyJson);
		return new FinalGetHostResponse(content);
	}

}
