package com.nd.rock.client.obverser.impl;

import java.util.List;
import java.util.Map;

import com.nd.rock.client.obverser.CallObserverExecutor;
import com.nd.rock.client.obverser.ObserverManager;
import com.nd.rock.client.operation.ContentOperation;
import com.nd.rock.client.operation.impl.GetContentFromServer;
import com.nd.rock.common.exception.ServerUnAvailableException;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;
import com.nd.rock.common.net.bean.response.base.ContentResponse;

public class CallObserverExecutorImpl implements CallObserverExecutor {

	private ContentOperation getContentOperation = null;

	private ObserverManager observerManager = null;

	public CallObserverExecutorImpl(ObserverManager observerManager) {
		try {
			this.getContentOperation = new GetContentFromServer();
		} catch (ServerUnAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.observerManager = observerManager;
	}

	@Override
	public void update(Map<String, List<String>> changeMap) {
		GetContentParam getContentParam = new GetContentParam(changeMap);
		FinalGetContentResponse response = this.getContentOperation
				.getContent(getContentParam);
		ContentResponse contentResponse = response.getResponseBody();
		this.doCallObserver(contentResponse.getContent());
		;
	}

	private void doCallObserver(Map<String, Map<String, String>> contentMap) {
		for (Map.Entry<String, Map<String, String>> groupEnrty : contentMap
				.entrySet()) {
			for (Map.Entry<String, String> dataIdEntry : groupEnrty.getValue()
					.entrySet()) {
				this.observerManager.update(groupEnrty.getKey(),
						dataIdEntry.getKey(), dataIdEntry.getValue());
			}
		}
	}

}
