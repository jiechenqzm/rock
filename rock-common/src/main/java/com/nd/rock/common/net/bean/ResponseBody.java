package com.nd.rock.common.net.bean;

public interface ResponseBody<T> extends JSONStringAble {
	
	public boolean isOk();
	
	public String getMessage();
	
	public T getResponseBody();

}
