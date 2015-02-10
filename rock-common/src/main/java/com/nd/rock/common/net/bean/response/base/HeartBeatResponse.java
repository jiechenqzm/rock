package com.nd.rock.common.net.bean.response.base;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.impl.AbstractJSONStringAble;

public class HeartBeatResponse extends AbstractJSONStringAble {

	private boolean alive = true;

	// JSON-LIB构造bean对象需要午参构造函数
	public HeartBeatResponse() {
	}

	public HeartBeatResponse(boolean alive) {
		this.alive = alive;
	}

	public static HeartBeatResponse fromJsonString(String jsonStr)
			throws JSONException {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return fromJsonObject(jsonObject);
	}

	public static HeartBeatResponse fromJsonObject(JSONObject jsonObject)
			throws JSONException {
		return (HeartBeatResponse) JSONObject.toBean(jsonObject,
				HeartBeatResponse.class);
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}

}
