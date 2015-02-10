package com.nd.rock.common.file;

import java.io.File;

public interface DataPathFactory {
	
	public String getDataPath();
	

	/********** 下面部分是静态常量 **********/
	
	public static final String PROJECT_HOME_PATH = System.getProperties().getProperty("user.home") + File.separator + "middleware"
			+ File.separator + "rock";
	
	public static final String SERVER = "server";
	
	public static final String CLIENT = "client";
	
	public static final String DATA = "data";
	
	public static final String SNAPSHOT = "snapshot";
	
	public static final String STATIC = "static";
	
	public static final String HOST = "host";
	
	public static final String CONFIG = "config";
	
}
