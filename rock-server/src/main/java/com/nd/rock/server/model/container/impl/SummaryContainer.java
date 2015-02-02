package com.nd.rock.server.model.container.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.nd.rock.server.model.container.Container;

public class SummaryContainer implements Container {

	private ConcurrentHashMap<String, ConcurrentHashMap<String, String>> summaryMap = new ConcurrentHashMap<>();
	
	@Override
	public String get(String group, String dataId) throws IOException {
		String result = null;
		if(this.summaryMap.containsKey(group)) {
			Map<String, String> dataIdMap = this.summaryMap.get(group);
			if(dataIdMap.containsKey(dataId))
				result = dataIdMap.get(dataId);
		}
		return result;
	}

	@Override
	public boolean update(String group, String dataId, String content)
			throws IOException {
		ConcurrentHashMap<String, String> groupMap = this.summaryMap.get(group);
		if(groupMap == null){
			groupMap = this.summaryMap.putIfAbsent(group, new ConcurrentHashMap<String, String>());
		}
		groupMap.put(dataId, content);
		return true;
	}

	@Override
	public boolean delete(String group, String dataId) throws IOException {
		return update(group, dataId, null);
	}

	@Override
	public boolean clear() throws IOException {
		this.summaryMap.clear();
		return true;
	}

}
