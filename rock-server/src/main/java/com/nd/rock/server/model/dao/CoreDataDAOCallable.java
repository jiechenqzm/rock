package com.nd.rock.server.model.dao;

import com.nd.rock.common.exception.runtime.CallableExecuteException;
import com.nd.rock.server.model.instance.CoreDataIn;

public interface CoreDataDAOCallable {
	
	public void accept(CoreDataIn coreDataIn) throws CallableExecuteException;

}
