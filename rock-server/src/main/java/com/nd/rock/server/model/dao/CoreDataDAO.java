package com.nd.rock.server.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.nd.rock.server.model.instance.CoreDataIn;
import com.nd.rock.server.view.page.PageItems;


public interface CoreDataDAO {
	
	/***** 基础的update，对应到数据库真实操作 *****/
	
	public int insert(CoreDataIn dataIn);
	
	public int update(String group, String dataId, long version, boolean deleted, String newContent, String newSummary, boolean newDeleted);
	
	public int delete(String group, String dataId, long version);
	
	public CoreDataIn query(String group, String dataId);
	
	public CoreDataIn query(String group, String dataId, boolean deleted);
	
	
	/***** 具有业务含义的操作 *****/
	
	public int logicInsert(CoreDataIn dataIn);
	
	public int logicUpdate(String group, String dataId, long version, String newContent, String newSummary);
	
	public int logicDelete(String group, String dataId, long version);
	
	
	/***** 业务需要的查询逻辑 *****/
	
	public CoreDataIn logicQuery(String group, String dataId);
	
	public void logicQueryAll(CoreDataDAOCallable callable) throws SQLException;
	
	public PageItems<CoreDataIn> pageFuzzyQueryData(String group, String dataId, boolean deleted, int pageNo, int pageSize);

}
