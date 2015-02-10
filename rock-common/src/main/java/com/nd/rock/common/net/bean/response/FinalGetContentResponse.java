package com.nd.rock.common.net.bean.response;

import com.nd.rock.common.net.bean.response.base.ContentResponse;

import net.sf.json.JSONObject;


public class FinalGetContentResponse extends CommonResponseBody<ContentResponse> {
	
	public FinalGetContentResponse() {
	}
	
	public FinalGetContentResponse(ContentResponse contentResponse) {
		super(contentResponse);
	}
	
	public static FinalGetContentResponse fromJsonString(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return fromJsonObject(jsonObject);
	}
	
	public static FinalGetContentResponse fromJsonObject(JSONObject jsonObject){
		FinalGetContentResponse result = new FinalGetContentResponse();
		result.fromJsonObject2Common(jsonObject);
		JSONObject responseBodyJson = jsonObject.getJSONObject("responseBody");
		ContentResponse content = ContentResponse.fromJsonObject(responseBodyJson);
		return new FinalGetContentResponse(content);
	}

}
