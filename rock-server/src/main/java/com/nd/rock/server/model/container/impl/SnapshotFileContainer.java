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
import com.nd.rock.common.util.QFileUtil;
import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.model.dao.CoreDataDAOCallable;
import com.nd.rock.server.model.instance.CoreDataIn;

public class SnapshotFileContainer extends AbstractFileContainer {

	private static Logger logger = LoggerFactory
			.getLogger(SnapshotFileContainer.class);

	private DataPathFactory dataPathFactory = new ServerSnapshotFactory();

	protected CoreDataDAO coreDataDAO = null;

	public void init() {
		try {
			this.clearContent();
			this.coreDataDAO.logicQueryAll(new FileFlusher());
			logger.info("SnapshotFileContainer Init Success.");
		} catch (SQLException e) {
			String messgage = "SnapshotFileContainer Init Failed.";
			logger.error(messgage, e);
			throw new FatalBeanException(messgage, e);
		}
	}

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
	
	protected void clearContent(){
		if(!QFileUtil.delete(new File(dataPathFactory.getDataPath()), false))
			logger.warn("ClearContent Failed. May Dirty Data Exist.");
		else
			logger.warn("ClearContent Success.");
	}

	public void setCoreDataDAO(CoreDataDAO coreDataDAO) {
		this.coreDataDAO = coreDataDAO;
	}
	
	/**
	 * 从数据库获取所有
	 * @author QiuZongming
	 *
	 */
	private class FileFlusher implements CoreDataDAOCallable {
		@Override
		public void accept(CoreDataIn coreDataIn)
				throws CallableExecuteException {
			try {
				update(coreDataIn.getGroup(), coreDataIn.getDataId(),
						coreDataIn.getContent());
			} catch (IOException e) {
				String messgage = "Update File Content Error.";
				logger.error(messgage, e);
				throw new CallableExecuteException(messgage, e);
			}
		}
	}

}
