package com.nd.rock.server.model.container;

import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.nd.rock.common.util.QFileUtil;

public abstract class AbstractFileContainer implements Container {

	@Override
	public boolean delete(String group, String dataId) throws IOException {
		String filePath = getFilePath(group, dataId);
		return QFileUtil.deleteFile(filePath);
	}

	@Override
	public String get(String group, String dataId)
			throws FileNotFoundException, IOException {
		String filePath = getFilePath(group, dataId);
		byte[] filecontent = QFileUtil.getFileContent(filePath);
		return filecontent == null ? null : new String(filecontent,
				CHARACTER_ENCODING_DEFAULT);
	}

	@Override
	public boolean update(String group, String dataId, String content)
			throws IOException {
		String filePath = getFilePath(group, dataId);
		return QFileUtil.createOrUpdate(filePath, content,
				CHARACTER_ENCODING_DEFAULT);
	}

	@Override
	public boolean clear() throws IOException {
		return QFileUtil.delete(new File(getDataHomePath()), false);
	}

	/**
	 * 获取文件容器的第一级目录
	 * 
	 * @return
	 */
	protected abstract String getDataHomePath();

	/**
	 * 获取指定文件的路径
	 * 
	 * @param group
	 * @param dataId
	 * @return
	 */
	protected abstract String getFilePath(String group, String dataId);

}
