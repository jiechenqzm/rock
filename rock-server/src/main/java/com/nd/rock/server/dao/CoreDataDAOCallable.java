package com.nd.rock.server.dao;

import java.util.List;

import com.nd.rock.server.instance.CoreDataIn;

public interface CoreDataDAOCallable {
	
	public void accept(List<CoreDataIn> dataInList);

}
