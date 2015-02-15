package com.nd.rock.server.model.container.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.exception.runtime.CallableExecuteException;
import com.nd.rock.common.model.container.Container;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.instance.CoreDataIn;

public class DefaultContainerCallable implements CoreDataDAOCallable {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultContainerCallable.class);
	
	private Container contentContainer = null;
	private Container summaryContainer = null;
	
	public DefaultContainerCallable(Container contentContainer, Container summaryContainer) {
		this.contentContainer = contentContainer;
		this.summaryContainer = summaryContainer;
	}

	@Override
	public void accept(CoreDataIn coreDataIn) throws CallableExecuteException {
		try {
			this.contentContainer.update(coreDataIn.getGroup(), coreDataIn.getDataId(),
					coreDataIn.getContent());
			this.summaryContainer.update(coreDataIn.getGroup(), coreDataIn.getDataId(), coreDataIn.getSummary());
		} catch (IOException e) {
			String messgage = "Update Content Or Summary Error.";
			logger.error(messgage, e);
			throw new CallableExecuteException(messgage, e);
		}
	}
}
