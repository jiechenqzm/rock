package com.nd.rock.common.net.bean.response;

import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.ResponseBody;
import com.nd.rock.common.net.bean.impl.AbstractJSONStringAble;

public class CommonResponseBody<T> extends AbstractJSONStringAble implements
		ResponseBody<T> {

	private boolean ok = false;

	private String message = null;

	private T responseBody = null;

	// JSON-LIB构造bean对象需要午参构造函数
	public CommonResponseBody() {
	}

	public CommonResponseBody(T responseBody) {
		this(true, null, responseBody);
	}

	public CommonResponseBody(String messge) {
		this(false, messge, null);
	}

	public CommonResponseBody(boolean ok, String message, T responseBody) {
		this.ok = ok;
		this.message = message;
		this.responseBody = responseBody;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public T getResponseBody() {
		return responseBody;
	}

	@Override
	public boolean isOk() {
		return ok;
	}

	public void setResponseBody(T responseBody) {
		this.responseBody = responseBody;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	protected void fromJsonObject2Common(JSONObject jsonObject){
		this.setMessage(jsonObject.getString("message"));
		this.setOk(jsonObject.getBoolean("ok"));
	}

	public static CommonResponseBody<Object> fail(String message) {
		return new CommonResponseBody<Object>(message);
	}

}
