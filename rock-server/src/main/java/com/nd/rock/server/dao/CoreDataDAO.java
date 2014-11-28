package com.nd.rock.server.dao;

import java.util.List;

import com.nd.rock.server.instance.CoreDataIn;


public interface CoreDataDAO {
	
	public int insert(CoreDataIn dataIn);
	
	public int update(String group, String dataId, String oriVersion, String newValue);
	
	public int delete(String group, String dataId);
	
	public CoreDataIn query(String group, String dataId);
	
	public List<CoreDataIn> queryAll(int batchGetNumn, CoreDataDAOCallable callable);
	
}
