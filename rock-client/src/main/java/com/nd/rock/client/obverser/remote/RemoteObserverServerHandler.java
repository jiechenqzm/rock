package com.nd.rock.client.obverser.remote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;

import com.nd.rock.client.factory.DefaultHttpClientProxy;
import com.nd.rock.client.obverser.CallObserverExecutor;
import com.nd.rock.client.obverser.InnerObverser;
import com.nd.rock.client.obverser.ObserverManager;
import com.nd.rock.client.obverser.impl.CallObserverExecutorImpl;
import com.nd.rock.client.operation.OperationConstants;
import com.nd.rock.common.exception.ServerUnAvailableException;
import com.nd.rock.common.net.bean.request.CheckSummaryParam;
import com.nd.rock.common.net.bean.response.FinalCheckSummaryResponse;
import com.nd.rock.common.net.bean.response.base.SummaryResponse;
import com.nd.rock.common.net.http.HttpExecutor;
import com.nd.rock.common.net.http.impl.DefaultHttpExecutor;
import com.nd.rock.common.net.http.impl.EncodingHttpExecutorDecorator;
import com.nd.rock.common.thread.NamedThreadFactory;

public class RemoteObserverServerHandler implements RemoteObserverHandler,
		OperationConstants {

	private ObserverManager observerManager = null;

	private DefaultHttpClientProxy clientStaticHostFactory = null;

	private ScheduledExecutorService scheduledExecutorService = null;

	private HttpExecutor httpExecutor = null;

	private CallObserverExecutor callObserverExecutor = null;

	public RemoteObserverServerHandler(ObserverManager observerManager) {
		this.observerManager = observerManager;
		this.callObserverExecutor = new CallObserverExecutorImpl(observerManager);
		this.scheduledExecutorService = Executors.newScheduledThreadPool(1,
				new NamedThreadFactory("Rock-Client-To-Server-Observer "));
		this.httpExecutor = new EncodingHttpExecutorDecorator(
				new DefaultHttpExecutor());
	}

	@Override
	public void start() throws ServerUnAvailableException {
		this.clientStaticHostFactory = DefaultHttpClientProxy.getInstance();
		this.scheduledExecutorService.scheduleWithFixedDelay(
				new ServerObserverTask(), 4, 4, TimeUnit.SECONDS);
	}

	public class ServerObserverTask implements Runnable {
		@Override
		public void run() {
			CheckSummaryParam checkSummaryParam = new CheckSummaryParam(
					getCheckSummaryParamMap());
			HttpClient httpClient = clientStaticHostFactory.getHttpClient();

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put(PARAM, checkSummaryParam.toJSONString());

			String result = null;
			try {
				result = httpExecutor.executePost(httpClient,
						CHECK_SUMMARY_URI, paramMap);
			} catch (Exception e) {
				// 错误处理
				e.printStackTrace();
			}
			// 错误处理
			FinalCheckSummaryResponse response = FinalCheckSummaryResponse
					.fromJsonString(result);
			if (response.isOk()) {
				SummaryResponse summaryResponse = response.getResponseBody();
				Map<String, List<String>> changeMap = summaryResponse
						.getContent();
				if (!changeMap.isEmpty()) {
					callObserverExecutor.update(changeMap);;
				}
			}
		}

		private Map<String, Map<String, String>> getCheckSummaryParamMap() {
			Map<String, Map<String, String>> result = new HashMap<>();
			Map<String, Map<String, List<InnerObverser>>> map = observerManager
					.iterator();
			for (Map.Entry<String, Map<String, List<InnerObverser>>> entry : map
					.entrySet()) {
				Map<String, String> dataIdSummaryMap = new HashMap<>();
				for (Map.Entry<String, List<InnerObverser>> entry1 : entry
						.getValue().entrySet()) {
					for (InnerObverser innerObverser : entry1.getValue()) {
						dataIdSummaryMap.put(entry1.getKey(),
								innerObverser.getSummary());
						break;
					}
				}
				if (!dataIdSummaryMap.isEmpty())
					result.put(entry.getKey(), dataIdSummaryMap);
			}
			return result;
		}
	}
}
