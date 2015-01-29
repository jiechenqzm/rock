package com.nd.rock.server.model.container.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;

import com.nd.rock.common.exception.CallableExecuteException;
import com.nd.rock.common.file.DataPathFactory;
import com.nd.rock.common.file.server.ServerSnapshotFactory;
import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.instance.CoreDataIn;

public class SnapshotFileContainer extends AbstractFileContainer {
	
	private static Logger logger = LoggerFactory.getLogger(SnapshotFileContainer.class);

	private DataPathFactory dataPathFactory = new ServerSnapshotFactory();
	
	protected CoreDataDAO coreDataDAO = null;

	public void init() {
		CoreDataDAOCallable fileContainerCallable = new CoreDataDAOCallable() {
			@Override
			public void accept(CoreDataIn coreDataIn) {
				try {
					update(coreDataIn.getGroup(), coreDataIn.getDataId(), coreDataIn.getContent());
				} catch (IOException e) {
					String messgage = "Update File Content Error.";
					logger.error(messgage, e);
					throw new CallableExecuteException(messgage, e);
				}
			}
		};
		try {
			this.coreDataDAO.logicQueryAll(fileContainerCallable);
		} catch (SQLException e) {
			String messgage = "Init File Container Failed.";
			logger.error(messgage, e);
			throw new FatalBeanException(messgage, e);
		}
	}

	protected String getFilePath(String group, String dataId) {
		String groupPath = this.dataPathFactory.getDataPath() + File.separator + group;
		
		File file = new File(groupPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		return groupPath + File.separator + dataId;
	}
	
	public void setCoreDataDAO(CoreDataDAO coreDataDAO) {
		this.coreDataDAO = coreDataDAO;
	}

}
