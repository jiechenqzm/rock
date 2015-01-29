package com.nd.rock.common.net.bean;

public interface ResponseBody<T> extends JSONStringAble {
	
	public boolean ok();
	
	public String getMessage();
	
	public T getResponseOb();

}
