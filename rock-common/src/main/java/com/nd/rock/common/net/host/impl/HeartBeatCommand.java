package com.nd.rock.common.net.host.impl;

import com.nd.rock.common.net.bean.response.FinalHeartBeatResponse;
import com.nd.rock.common.net.host.CheckHostCommand;
import com.nd.rock.common.util.QHttpUtil;

public class HeartBeatCommand implements CheckHostCommand {

	private static final String uriModule = "/rock/host/heartBeat.do";

	@Override
	public boolean doCheck(String host) {
		String result = null;
		try {
			result = QHttpUtil.doHttpRequest(host, 80, uriModule);
		} catch (Exception e) {
			return false;
		}
		FinalHeartBeatResponse response = FinalHeartBeatResponse
				.fromJsonString(result);
		return response.isOk();
	}
}
