package com.nd.rock.server.model.container.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;

import com.nd.rock.common.file.DataPathFactory;
import com.nd.rock.common.file.server.ServerSnapshotFactory;
import com.nd.rock.server.model.container.AbstractFileContainer;
import com.nd.rock.server.model.dao.CoreDataDAO;

public class SnapshotFileContainer extends AbstractFileContainer {

	private static Logger logger = LoggerFactory.getLogger(SnapshotFileContainer.class);
	
	private DataPathFactory dataPathFactory = new ServerSnapshotFactory();

	private CoreDataDAO coreDataDAO = null;
	
	@Override
	protected String getFilePath(String group, String dataId) {
		String groupPath = this.dataPathFactory.getDataPath() + File.separator
				+ group;

		File file = new File(groupPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return groupPath + File.separator + dataId;
	}
	
	@Override
	protected String getDataHomePath() {
		return this.dataPathFactory.getDataPath();
	}

	public void setCoreDataDAO(CoreDataDAO coreDataDAO) {
		this.coreDataDAO = coreDataDAO;
	}
	
	public void init() {
		try {
			super.clear();
			this.coreDataDAO.logicQueryAll(new DataContainerCallable(this));
			logger.info("SnapshotFileContainer Init Success.");
		} catch (SQLException|IOException e) {
			String messgage = "SnapshotFileContainer Init Failed.";
			logger.error(messgage, e);
			throw new FatalBeanException(messgage, e);
		}
	}
	
}
