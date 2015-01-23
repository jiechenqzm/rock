package com.nd.rock.server.model.dao;

import java.util.List;

import com.nd.rock.server.model.instance.CoreDataIn;
import com.nd.rock.server.view.page.impl.DefaultPageItem;


public interface CoreDataDAO {
	
	public int insert(CoreDataIn dataIn);
	
	public int update(String group, String dataId, long oriVersion, String newValue, String summary);
	
	public int delete(String group, String dataId);
	
	public CoreDataIn query(String group, String dataId);
	
	public List<CoreDataIn> queryAll(int batchGetNumn, CoreDataDAOCallable callable);
	
	public DefaultPageItem<CoreDataIn> pageFuzzyQueryData(String group, String dataId, int pageNo, int pageSize);

}
