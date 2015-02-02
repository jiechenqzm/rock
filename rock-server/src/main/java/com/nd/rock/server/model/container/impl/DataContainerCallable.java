package com.nd.rock.server.model.container.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.exception.CallableExecuteException;
import com.nd.rock.server.model.container.Container;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.instance.CoreDataIn;

public class DataContainerCallable implements CoreDataDAOCallable {
	
	private static Logger logger = LoggerFactory.getLogger(DataContainerCallable.class);
	
	private Container dataContainer = null;
	
	public DataContainerCallable(Container dataContainer) {
		this.dataContainer = dataContainer;
	}

	@Override
	public void accept(CoreDataIn coreDataIn) throws CallableExecuteException {
		try {
			this.dataContainer.update(coreDataIn.getGroup(), coreDataIn.getDataId(),
					coreDataIn.getContent());
		} catch (IOException e) {
			String messgage = "Update Content Error.";
			logger.error(messgage, e);
			throw new CallableExecuteException(messgage, e);
		}
	}
}
