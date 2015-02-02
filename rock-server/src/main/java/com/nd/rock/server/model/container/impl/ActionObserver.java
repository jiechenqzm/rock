package com.nd.rock.server.model.container.impl;

import java.util.Vector;

import com.nd.rock.common.net.bean.impl.DataKeyOb;
import com.nd.rock.server.model.container.CoreDataObservable;
import com.nd.rock.server.model.container.CoreDataObserver;

public class ActionObserver implements CoreDataObservable {

	private Vector<CoreDataObserver> vector = new Vector<>();

	@Override
	public void addObserver(CoreDataObserver coreDataObserver) {
		this.vector.add(coreDataObserver);
	}
	@Override
	public synchronized void notifyObservers(String group, String dataId) {
		for(CoreDataObserver coreDataObserver : vector) {
			coreDataObserver.update(new DataKeyOb(group, dataId));
		}
	}
	
	@Override
	public void removeObserver(CoreDataObserver coreDataObserver) {
		this.vector.remove(coreDataObserver);
	}

	public void setVector(Vector<CoreDataObserver> vector) {
		this.vector = vector;
	}

}
