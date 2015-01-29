package com.nd.rock.common.file.server;

import java.io.File;

import com.nd.rock.common.file.DataPathFactory;

public class ServerSnapshotFactory implements DataPathFactory {

	private String path = null;

	public ServerSnapshotFactory() {
		this.path = PROJECT_HOME_PATH + File.separator + SERVER
				+ File.separator + DATA + File.separator + SNAPSHOT;
	}

	@Override
	public String getDataPath() {
		return path;
	}

}
