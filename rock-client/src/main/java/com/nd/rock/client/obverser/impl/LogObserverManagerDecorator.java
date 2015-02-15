package com.nd.rock.client.obverser.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.client.obverser.InnerObverser;
import com.nd.rock.client.obverser.ObserverManager;

public class LogObserverManagerDecorator implements ObserverManager {
	
    private static final Logger logger = LoggerFactory.getLogger(LogObserverManagerDecorator.class);
	
	private ObserverManager observerManager = null;
	
	public LogObserverManagerDecorator(ObserverManager observerManager) {
		this.observerManager = observerManager;
	}

	@Override
	public boolean registerObserver(InnerObverser innerObverser) {
		boolean result = this.observerManager.registerObserver(innerObverser);
		logger.info("DoRegisterObserver >> " + result);
		return result;
	}

	@Override
	public boolean removeObserver(InnerObverser innerObverser) {
		boolean result = this.observerManager.removeObserver(innerObverser);
		logger.info("DoRemoveObserver >> " + result);
		return result;
	}
	
	@Override
	public Map<String, Map<String, List<InnerObverser>>> iterator() {
		return this.observerManager.iterator();
	}
	
	@Override
	public void update(String group, String dataId, String content) {
		this.observerManager.update(group, dataId, content);
	}
}
