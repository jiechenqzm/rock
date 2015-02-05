package com.nd.rock.client.facade.decorator;

import com.nd.rock.client.facade.Client;

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

}
