package com.nd.rock.common.file;

import java.io.File;

public interface DataPathFactory {
	
	public static final String PROJECT_HOME_PATH = System.getProperties().getProperty("user.home") + File.separator + "middleware"
			+ File.separator + "rock";
	
	public static final String SERVER = "server";
	
	public static final String CLIENT = "client";
	
	public static final String DATA = "data";
	
	public static final String SNAPSHOT = "snapshot";
	
	public static final String STATIC = "static";
	
	
	
	public String getDataPath();
	
}
