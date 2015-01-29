package com.nd.rock.server.model.container.impl;

import java.util.HashMap;
import java.util.Map;

import com.nd.rock.server.model.container.DataContainer;

public class MemoryContainerImpl implements DataContainer {

	private Map<String, HashMap<String, String>> map = null;

	@Override
	public boolean delete(String group, String dataId) {
		this.map.get(group).remove(dataId);
		return false;
	}

	@Override
	public String get(String group, String dataId) {
		this.map.get(group).get(dataId);
		return null;
	}

	@Override
	public boolean update(String group, String dataId, String value) {
		this.map.get(group).put(dataId, null);
		return false;	
	}

}
