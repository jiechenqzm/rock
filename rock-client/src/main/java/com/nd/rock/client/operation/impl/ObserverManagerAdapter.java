package com.nd.rock.client.operation.impl;

import com.nd.rock.client.facade.ContentObserver;
import com.nd.rock.client.obverser.InnerObverser;
import com.nd.rock.client.obverser.ObserverManager;
import com.nd.rock.client.obverser.impl.ContentObverserAdapter;
import com.nd.rock.client.obverser.impl.LogObserverManagerDecorator;
import com.nd.rock.client.obverser.impl.ObserverManagerImpl;
import com.nd.rock.client.operation.ObserverOperation;

public class ObserverManagerAdapter implements ObserverOperation {

	private ObserverManager obverserManager = null;

	public ObserverManagerAdapter() {
		this.obverserManager = new LogObserverManagerDecorator(
				new ObserverManagerImpl());
	}
	
	@Override
	public boolean registerObserver(String group, String dataId,
			String initContent, ContentObserver contentObserver) {
		InnerObverser InnerObverser = new ContentObverserAdapter(group, dataId,
				initContent, contentObserver);
		return this.obverserManager.registerObserver(InnerObverser);
	}

	@Override
	public boolean removeObserver(String group, String dataId,
			ContentObserver contentObserver) {
		InnerObverser innerObverser = new ContentObverserAdapter(group, dataId,
				null, contentObserver);
		return this.obverserManager.removeObserver(innerObverser);
	}
}