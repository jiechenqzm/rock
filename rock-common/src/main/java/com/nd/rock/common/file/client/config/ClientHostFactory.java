package com.nd.rock.common.file.client.config;

import java.io.File;

import com.nd.rock.common.file.DataPathFactory;
import com.nd.rock.common.util.QFileUtil;

/**
 * 
 * Client 在本地磁盘冗余的SERVER列表路径
 * @author QiuZongming
 *
 */
public class ClientHostFactory implements DataPathFactory {

	private String path = null;

	public ClientHostFactory() {
		String folderPath =  PROJECT_HOME_PATH + File.separator + CLIENT
				+ File.separator + CONFIG;
		QFileUtil.createFileIfNotExist(folderPath, true);
		this.path = folderPath + File.separator + HOST;
	}

	@Override
	public String getDataPath() {
		return path;
	}

}
