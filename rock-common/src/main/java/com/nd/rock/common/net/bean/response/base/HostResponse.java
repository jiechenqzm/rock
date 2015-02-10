package com.nd.rock.common.net.bean.response.base;

import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.impl.AbstractJSONStringAble;

public class HostResponse extends AbstractJSONStringAble {

	private List<String> hostList = null;
	
	// JSON-LIB构造bean对象需要午参构造函数
	public HostResponse() {}
	
	public HostResponse(List<String> hostList) {
		this.setHostList(hostList);
	}

	public List<String> getHostList() {
		return hostList;
	}

	public void setHostList(List<String> hostList) {
		this.hostList = hostList;
	}

	public static HostResponse fromJsonString(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return fromJsonObject(jsonObject);
	}
	
	public static HostResponse fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		return (HostResponse) JSONObject.toBean(jsonObject,
				HostResponse.class);
	}
}
