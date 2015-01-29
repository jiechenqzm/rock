package com.nd.rock.common.net.bean.impl;

import com.nd.rock.common.net.bean.ResponseBody;

public class DefaultResponseBody<T> extends AbstractJSONStringAble implements ResponseBody<T> {
	
	private boolean ok = false;
	
	private String message = null;
	
	private T responseBody = null;
	
	public DefaultResponseBody(T responseBody) {
		this(true, null, responseBody);
	}
	
	public DefaultResponseBody(String messge) {
		this(false, messge, null);
	}
	
	public DefaultResponseBody(boolean ok, String message, T responseBody) {
		this.ok = ok;
		this.message = message;
		this.responseBody = responseBody;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public T getResponseOb() {
		return responseBody; 
	}
	
	@Override
	public boolean ok() {
		return ok;
	}
	
	public static DefaultResponseBody<Object> fail(String message) {
		return new DefaultResponseBody<Object>(message);
	}
	
//	public static GetContentRequestIn fromJsonStr(String jsonStr)
//			throws JSONException {
//		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//		return (GetContentRequestIn) JSONObject.toBean(jsonObject,
//				GetContentRequestIn.class);
//	}

}
