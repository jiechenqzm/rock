package com.nd.rock.server.model.container;

import java.io.IOException;


public interface DataContainer {
	
	public String get(String group, String dataId) throws IOException;
	
	public boolean update(String group, String dataId, String content) throws IOException;
	
	public boolean delete(String group, String dataId) throws IOException;

}
