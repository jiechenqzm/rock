package com.nd.rock.common.net.bean.response;

import net.sf.json.JSONObject;


public class FinalCheckSummaryResponse extends CommonResponseBody<SummaryResponse> {
	
	public FinalCheckSummaryResponse() {
	}
	
	public FinalCheckSummaryResponse(SummaryResponse contentResponse) {
		super(contentResponse);
	}
	
	public static FinalCheckSummaryResponse fromJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return fromJsonObject(jsonObject);
	}
	
	public static FinalCheckSummaryResponse fromJsonObject(JSONObject jsonObject){
		FinalCheckSummaryResponse result = new FinalCheckSummaryResponse();
		result.fromJsonObject2Common(jsonObject);
		JSONObject responseBodyJson = jsonObject.getJSONObject("responseBody");
		SummaryResponse content = SummaryResponse.fromJsonObject(responseBodyJson);
		return new FinalCheckSummaryResponse(content);
	}

}
