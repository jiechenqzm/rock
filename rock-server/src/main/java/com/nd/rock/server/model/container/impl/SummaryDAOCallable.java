package com.nd.rock.server.model.container.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.exception.CallableExecuteException;
import com.nd.rock.server.model.container.Container;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.instance.CoreDataIn;

public class SummaryDAOCallable implements CoreDataDAOCallable {
	
	private static Logger logger = LoggerFactory.getLogger(SummaryDAOCallable.class);
	
	private CoreDataDAOCallable coreDataDAOCallable = null;
	
	public SummaryDAOCallable(CoreDataDAOCallable coreDataDAOCallable) {
		this.coreDataDAOCallable = coreDataDAOCallable;
	}

	@Override
	public void accept(CoreDataIn coreDataIn) throws CallableExecuteException {
		this.coreDataDAOCallable.accept(coreDataIn);
		
	}
}
