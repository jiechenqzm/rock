package com.nd.rock.server.model.container.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.net.bean.impl.DataKeyOb;
import com.nd.rock.server.model.container.Container;
import com.nd.rock.server.model.container.CoreDataObserver;
import com.nd.rock.server.model.dao.CoreDataDAO;

public class DefaultContainer implements CoreDataObserver {
	
    private static Logger logger = LoggerFactory.getLogger(DefaultContainer.class);


	private Container fileContainer = null;
	
	private Container summaryContainer = null;
	
	private CoreDataDAO coreDataDAO = null;
	
	private LinkedBlockingQueue<DataKeyOb> changeBuffer = new LinkedBlockingQueue<>(256);

	public DefaultContainer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(String group, String dataId) {
		try {
			if(!this.changeBuffer.offer(new DataKeyOb(group, dataId), 100, TimeUnit.MILLISECONDS)) {
				logger.error("ChangeBuffer Is Full. Drop Data : Group >> " + group + ", dataId >> " + dataId);
			}
		} catch (InterruptedException e) {
			logger.error("Param Is Empty. Group >> " + group + ", dataId >> " + dataId);
		}
	}
	
}
