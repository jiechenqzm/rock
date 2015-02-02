package com.nd.rock.server.model.container.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.nd.rock.server.model.container.DataContainer;
import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;


public abstract class AbstractFileContainer implements DataContainer {
		
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
			return null;

        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()]; 
        
        try(FileInputStream in = new FileInputStream(file)) {
        	in.read(filecontent);	
        }
        return new String(filecontent, CHARACTER_ENCODING_DEFAULT); 
	}

	@Override
	public boolean update(String group, String dataId, String content)
			throws IOException {
		String filePath = getFilePath(group, dataId);
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();

		try (OutputStreamWriter osw = new OutputStreamWriter(
				new FileOutputStream(file), CHARACTER_ENCODING_DEFAULT)) {
			osw.write(content);
			osw.flush();
		}

		return true;
	}

	protected abstract String getFilePath(String group, String dataId);

}
