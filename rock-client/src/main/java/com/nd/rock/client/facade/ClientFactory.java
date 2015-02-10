package com.nd.rock.client.facade;

import com.nd.rock.client.facade.decorator.LogClientDecorator;
import com.nd.rock.client.facade.impl.DefaultClient;

public class ClientFactory {
	
	public static Client getClient(){
		return new LogClientDecorator(new DefaultClient());

	}

}
