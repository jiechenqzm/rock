package com.nd.rock.client.obverser.remote;

import com.nd.rock.common.exception.ServerUnAvailableException;

public interface RemoteObserverHandler {
	
	public void start() throws ServerUnAvailableException;

}
