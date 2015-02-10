package com.nd.rock.common.net.bean.response;

import com.nd.rock.common.net.bean.response.base.HeartBeatResponse;

import net.sf.json.JSONObject;


public class FinalHeartBeatResponse extends CommonResponseBody<HeartBeatResponse> {
	
	public FinalHeartBeatResponse() {
	}
	
	public FinalHeartBeatResponse(HeartBeatResponse beatHeartResponse) {
		super(beatHeartResponse);
	}
	
	public static FinalHeartBeatResponse fromJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return fromJsonObject(jsonObject);
	}
	
	public static FinalHeartBeatResponse fromJsonObject(JSONObject jsonObject){
		FinalHeartBeatResponse result = new FinalHeartBeatResponse();
		result.fromJsonObject2Common(jsonObject);
		JSONObject responseBodyJson = jsonObject.getJSONObject("responseBody");
		HeartBeatResponse content = HeartBeatResponse.fromJsonObject(responseBodyJson);
		return new FinalHeartBeatResponse(content);
	}

}
