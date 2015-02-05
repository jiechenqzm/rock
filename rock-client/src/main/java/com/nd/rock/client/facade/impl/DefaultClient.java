package com.nd.rock.client.facade.impl;

import java.util.List;
import java.util.Map;

import com.nd.rock.client.facade.Client;
import com.nd.rock.client.facade.Observer;

public class DefaultClient implements Client {

	@Override
	public String getContent(String group, String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getContentBatch(String group, List<String> dataIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerObserver(String group, String dataId, String content,
			Observer observer) {
		// TODO Auto-generated method stub

	}

}
