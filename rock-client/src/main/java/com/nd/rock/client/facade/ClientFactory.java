package com.nd.rock.client.facade;

import com.nd.rock.client.facade.decorator.LogClientDecorator;
import com.nd.rock.client.facade.decorator.ParamCheckClientDecorator;
import com.nd.rock.client.facade.impl.DefaultClient;

/**
 * 客户端使用入口类，保证client对象为单例
 * 
 * @author QiuZongming
 *
 */
public class ClientFactory {

	private static Client client = new ParamCheckClientDecorator(
			new LogClientDecorator(new DefaultClient()));

	public static Client getClient() {
		return client;
	}

}
