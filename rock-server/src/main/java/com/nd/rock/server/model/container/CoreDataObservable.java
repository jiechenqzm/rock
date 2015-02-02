package com.nd.rock.server.model.container;

public interface CoreDataObservable {
	
	public void addObserver(CoreDataObserver coreDataObserver);
	
	public void removeObserver(CoreDataObserver coreDataObserver);
	
	public void notifyObservers(String group, String dataId);
	
}
