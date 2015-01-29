package com.nd.rock.common.net.bean.impl;

import net.sf.json.JSONObject;

import com.nd.rock.common.net.bean.JSONStringAble;

public abstract class AbstractJSONStringAble implements JSONStringAble{
	
	@Override
	public String toJSONString() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}

}
