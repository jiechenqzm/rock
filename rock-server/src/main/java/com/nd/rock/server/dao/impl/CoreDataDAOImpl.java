package com.nd.rock.server.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.nd.rock.server.dao.CoreDataDAO;
import com.nd.rock.server.dao.CoreDataDAOCallable;
import com.nd.rock.server.instance.CoreDataIn;

public class CoreDataDAOImpl implements CoreDataDAO {
	
	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate = null;

	@Override
	public int insert(CoreDataIn dataIn) {
//		jdbcTemplate.update("insert into ", args)
		return 0;
	}

	@Override
	public int update(String group, String dataId, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String group, String dataId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CoreDataIn query(String group, String dataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CoreDataIn> queryAll(int batchGetNumn, CoreDataDAOCallable callable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
