package com.nd.rock.client.facade.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nd.rock.client.facade.Client;
import com.nd.rock.client.facade.ContentObserver;
import com.nd.rock.client.operation.ContentOperation;
import com.nd.rock.client.operation.ObserverOperation;
import com.nd.rock.client.operation.impl.DefaultGetContentOperation;
import com.nd.rock.client.operation.impl.ObserverManagerAdapter;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;

public class DefaultClient implements Client {

	private ContentOperation getContentOperation = new DefaultGetContentOperation();

	private ObserverOperation observerOperation = new ObserverManagerAdapter();
	
	@Override
	public String getContent(String group, String dataId) {
		Map<String, String> resultMap = getContentBatch(group,
				Arrays.asList(dataId));
		return resultMap.get(dataId);
	}

	@Override
	public Map<String, String> getContentBatch(String group,
			List<String> dataIds) {
		Map<String, List<String>> keyMap = new HashMap<>();
		keyMap.put(group, dataIds);
		GetContentParam getContentParam = new GetContentParam(keyMap);

		FinalGetContentResponse response = getContentOperation
				.getContent(getContentParam);

		return response.getResponseBody().getContent().get(group);
	}

	@Override
	public boolean registerObserver(String group, String dataId, String initContent,
			ContentObserver contentObserver) {
		return this.observerOperation.registerObserver(group, dataId, initContent, contentObserver);
	}
	
	@Override
	public boolean removeObserver(String group, String dataId,
			ContentObserver contentObserver) {
		return this.observerOperation.removeObserver(group, dataId, contentObserver);
	}

}
