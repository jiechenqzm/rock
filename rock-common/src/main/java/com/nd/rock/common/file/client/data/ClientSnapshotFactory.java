package com.nd.rock.common.file.client.data;

import java.io.File;

import com.nd.rock.common.file.DataPathFactory;

public class ClientSnapshotFactory implements DataPathFactory {

	private String path = null;

	public ClientSnapshotFactory() {
		this.path = PROJECT_HOME_PATH + File.separator + SERVER
				+ File.separator + DATA + File.separator + STATIC;
	}

	@Override
	public String getDataPath() {
		return path;
	}

}
