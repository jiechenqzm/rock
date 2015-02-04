package com.nd.rock.client.facade.decorator;

import java.util.List;
import java.util.Map;

import com.nd.rock.client.facade.Client;
import com.nd.rock.client.facade.Observer;

/**
 * Client的装饰者抽象类
 * 
 * @author QiuZongming
 *
 */
public abstract class ClientDecorator implements Client {

	protected Client client = null;

	public ClientDecorator(Client client) {
		this.client = client;
	}

	public abstract String getContent(String group, String dataId);

	public abstract Map<String, String> getContentBatch(String group,
			List<String> dataIds);

	public abstract void addObserver(String group, String dataId,
			String content, Observer observer);

}
