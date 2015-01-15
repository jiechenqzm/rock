package com.nd.rock.common.file.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.nd.rock.common.file.PathFactory;

public class DefaultPathFactory implements PathFactory {
	
	private String rootPath = null;
	
	private String dataPath = null;
	
	private String staticDataPath = null;
	
	private String snapshotDataPath = null;
	
	Map<String, String> pathMap = new HashMap<String, String>();
	
	public DefaultPathFactory() {
		String homePath = System.getProperties().getProperty("user.home");
		
		this.rootPath = homePath + File.separator + "middleware" + File.separator
				+ "rock";
		this.dataPath = this.rootPath + File.separator + "server" + File.separator + "data";
		this.staticDataPath = this.dataPath + File.separator + "static";
		this.snapshotDataPath = this.dataPath + File.separator + "snapshot";
	}

	@Override
	public String getRootPah() {
		return this.rootPath;
	}

	@Override
	public String getStaticDataPah() {
		return this.staticDataPath;
	}

	@Override
	public String getSnapshotDataPath() {
		return this.snapshotDataPath;
	}

}
