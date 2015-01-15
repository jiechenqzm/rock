package com.nd.rock.server.model.container;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface DataContainer {
	
	public String get(String group, String dataId) throws FileNotFoundException,IOException;
	
	public boolean update(String group, String dataId, String value) throws IOException;
	
	public boolean delete(String group, String dataId) throws IOException;

}
