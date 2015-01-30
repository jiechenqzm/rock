package com.nd.rock.common.net.bean.response;

import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.ResponseBody;
import com.nd.rock.common.net.bean.impl.AbstractJSONStringAble;

public class CommonResBody<T> extends AbstractJSONStringAble implements
		ResponseBody<T> {

	private boolean ok = false;

	private String message = null;

	private T responseBody = null;

	// JSON-LIB构造bean对象需要午参构造函数
	public CommonResBody() {
	}

	public CommonResBody(T responseBody) {
		this(true, null, responseBody);
	}

	public CommonResBody(String messge) {
		this(false, messge, null);
	}

	public CommonResBody(boolean ok, String message, T responseBody) {
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

	public static CommonResBody<Object> fail(String message) {
		return new CommonResBody<Object>(message);
	}
	
	protected void fromJsonObject2Common(JSONObject jsonObject){
		this.setMessage(jsonObject.getString("message"));
		this.setOk(jsonObject.getBoolean("ok"));
	}

}
