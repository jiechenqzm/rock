package com.nd.rock.common.net.http.impl;

import com.nd.rock.common.net.http.HttpParam;

public class DefaultHttpParam implements HttpParam {

	private static final int CONNECT_TIMEOUT_MILLION = 5_000;

	private static final int SOCKET_TIMEOUT_MILLION = 5_000;
	
	@Override
	public int getConnectTimeout() {
		return CONNECT_TIMEOUT_MILLION;
	}
	
	@Override
	public int getSocketTimeOut() {
		return SOCKET_TIMEOUT_MILLION;
	}

}
