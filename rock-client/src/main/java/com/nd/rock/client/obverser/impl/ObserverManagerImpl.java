package com.nd.rock.client.obverser.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.client.obverser.InnerObverser;
import com.nd.rock.client.obverser.ObserverManager;
import com.nd.rock.client.obverser.remote.RemoteObserverHandler;
import com.nd.rock.client.obverser.remote.RemoteObserverServerHandler;
import com.nd.rock.common.exception.ServerUnAvailableException;
import com.nd.rock.common.log.LogSample;

public class ObserverManagerImpl implements ObserverManager {

	private static final Logger logger = LoggerFactory
			.getLogger(LogSample.class);

	private ConcurrentHashMap<String, ConcurrentHashMap<String, Vector<InnerObverser>>> obverserMap = new ConcurrentHashMap<>();

	private RemoteObserverHandler remoteObserverServerHandler = null;

	public ObserverManagerImpl() {
		this.remoteObserverServerHandler = new RemoteObserverServerHandler(this);
		try {
			this.remoteObserverServerHandler.start();
		} catch (ServerUnAvailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean registerObserver(InnerObverser innerObverser) {
		Vector<InnerObverser> vector = getDataIdVector(
				innerObverser.getGroup(), innerObverser.getDataId());
		return vector.contains(innerObverser) ? false : vector
				.add(innerObverser);
	}

	@Override
	public synchronized boolean removeObserver(InnerObverser innerObverser) {
		Vector<InnerObverser> vector = getDataIdVector(
				innerObverser.getGroup(), innerObverser.getDataId());
		return vector.remove(innerObverser);
	}

	@Override
	public Map<String, Map<String, List<InnerObverser>>> iterator() {
		Map<String, Map<String, List<InnerObverser>>> result = new HashMap<>();
		for (Map.Entry<String, ConcurrentHashMap<String, Vector<InnerObverser>>> entry : obverserMap
				.entrySet()) {
			Map<String, List<InnerObverser>> groupMap = new HashMap<>();
			for (Map.Entry<String, Vector<InnerObverser>> entry2 : entry
					.getValue().entrySet()) {
				groupMap.put(entry2.getKey(), entry2.getValue());
			}
			result.put(entry.getKey(), groupMap);
		}
		return result;
	}

	@Override
	public void update(String group, String dataId, String newContent) {
		Vector<InnerObverser> list = getDataIdVector(group, dataId);
		for (InnerObverser innerObverser : list) {
			try {
				innerObverser.update(newContent);
			} catch (Exception e) {
				logger.error("DoCallObserver Error. group >> " + group
						+ " ## dataId >> " + dataId + " ## newContent >> "
						+ newContent);
			}
		}
	}

	/********** 以下是内部辅助方法 **********/

	private Vector<InnerObverser> getDataIdVector(String group, String dataId) {
		ConcurrentHashMap<String, Vector<InnerObverser>> groupMap = getGroupMap(group);
		return getObverserVector(groupMap, dataId);
	}

	private ConcurrentHashMap<String, Vector<InnerObverser>> getGroupMap(
			String group) {
		ConcurrentHashMap<String, Vector<InnerObverser>> groupMap = this.obverserMap
				.get(group);
		if (groupMap == null) {
			groupMap = new ConcurrentHashMap<>();
			ConcurrentHashMap<String, Vector<InnerObverser>> getGroupMap = this.obverserMap
					.putIfAbsent(group, groupMap);
			groupMap = getGroupMap == null ? groupMap : getGroupMap;
		}
		return groupMap;
	}

	private Vector<InnerObverser> getObverserVector(
			ConcurrentHashMap<String, Vector<InnerObverser>> groupMap,
			String dataId) {
		Vector<InnerObverser> obverserVector = groupMap.get(dataId);
		if (obverserVector == null) {
			obverserVector = new Vector<>();
			Vector<InnerObverser> getObverserVector = groupMap.putIfAbsent(
					dataId, obverserVector);
			obverserVector = getObverserVector == null ? obverserVector
					: getObverserVector;
		}
		return obverserVector;
	}
}
