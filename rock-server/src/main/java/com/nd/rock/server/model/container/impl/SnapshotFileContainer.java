package com.nd.rock.server.model.container.impl;

import java.io.File;

import com.nd.rock.common.file.DataPathFactory;
import com.nd.rock.common.file.server.data.ServerSnapshotFactory;
import com.nd.rock.common.util.QFileUtil;
import com.nd.rock.server.model.container.AbstractFileContainer;

public class SnapshotFileContainer extends AbstractFileContainer {
	
	private DataPathFactory dataPathFactory = new ServerSnapshotFactory();
	
	@Override
	protected String getFilePath(String group, String dataId) {
		String groupPath = this.dataPathFactory.getDataPath() + File.separator
				+ group;
		QFileUtil.createFileIfNotExist(groupPath, true);
		return groupPath + File.separator + dataId;
	}
	
	@Override
	protected String getDataHomePath() {
		return this.dataPathFactory.getDataPath();
	}
	
}
