package com.nd.rock.server.model.container.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.nd.rock.common.file.PathFactory;
import com.nd.rock.common.file.impl.DefaultPathFactory;
import com.nd.rock.server.model.container.DataContainer;

public class FileContainerImpl implements DataContainer {
	
	private static final String CHARACTOR = "UTF-8";

	private PathFactory pathFactory = new DefaultPathFactory();

	@Override
	public boolean delete(String group, String dataId) throws IOException {
		String filePath = getFilePath(group, dataId);
		File file = new File(filePath);
		return file.exists() ? file.delete() : true;
	}

	@Override
	public String get(String group, String dataId) throws FileNotFoundException,IOException {
		String filePath = getFilePath(group, dataId);
		File file = new File(filePath);
		if (!file.exists())
			throw new FileNotFoundException(filePath);

        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()]; 
        
        try(FileInputStream in = new FileInputStream(file)) {
        	in.read(filecontent);	
        }
        return new String(filecontent, CHARACTOR); 
	}

	@Override
	public boolean update(String group, String dataId, String value)
			throws IOException {
		String filePath = getFilePath(group, dataId);
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();

		try (OutputStreamWriter osw = new OutputStreamWriter(
				new FileOutputStream(file), CHARACTOR)) {
			osw.write(value);
			osw.flush();
		}

		return true;
	}

	private String getFilePath(String group, String dataId) {
		String groupPath = this.pathFactory.getSnapshotDataPath() + File.separator + group;
		
		File file = new File(groupPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		return groupPath + File.separator + dataId;
	}

}
