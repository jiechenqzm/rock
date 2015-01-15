package com.nd.rock.server.model.dao;

import java.util.List;

import com.nd.rock.server.model.instance.CoreDataIn;

public interface CoreDataDAOCallable {
	
	public void accept(List<CoreDataIn> dataInList);

}
