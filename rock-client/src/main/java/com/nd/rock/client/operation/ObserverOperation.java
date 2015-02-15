package com.nd.rock.client.operation;

import com.nd.rock.client.facade.ContentObserver;

public interface ObserverOperation extends OperationConstants {

	public boolean registerObserver(String group, String dataId,
			String initContent, ContentObserver contentObserver);

	public boolean removeObserver(String group, String dataId,
			ContentObserver contentObserver);

}
