package com.nd.rock.common.net.host.impl;

import static com.nd.rock.common.constants.CommonConstants.CHARACTER_ENCODING_DEFAULT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.exception.FileAccessException;
import com.nd.rock.common.file.DataPathFactory;
import com.nd.rock.common.net.host.HostHolder;
import com.nd.rock.common.util.QFileUtil;
import com.nd.rock.common.util.QListUtil;
import com.nd.rock.common.util.QStringUtil;

public class HostInLocal implements HostHolder {

	private static final Logger logger = LoggerFactory.getLogger(HostInLocal.class);

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private List<String> hostList = null;
	
	private DataPathFactory dataPathFactory = null;
	
	public HostInLocal(DataPathFactory dataPathFactory) {
		this.dataPathFactory = dataPathFactory;
	}

	@Override
	public List<String> getHostList() {
		String filePath = dataPathFactory.getDataPath();
		String content = null;
		try {
			content = QFileUtil.getFileContent(filePath,
					CHARACTER_ENCODING_DEFAULT);
		} catch (Exception e) {
			String message = "GetFileContent Error." + e.getMessage();
			logger.error(message, e);
			throw new FileAccessException(message);
		}
		if (QStringUtil.notEmpty(content))
			return new ArrayList<String>();

		return Arrays.asList(content.split(LINE_SEPARATOR));
	}

	@Override
	public synchronized boolean saveHostList(List<String> hostList) {
		if(QListUtil.equal(this.hostList, hostList))
			return true;
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : hostList)
			stringBuilder.append(string).append(LINE_SEPARATOR);
		try {
			QFileUtil.createOrUpdate(dataPathFactory.getDataPath(),
					stringBuilder.toString(), CHARACTER_ENCODING_DEFAULT);
			this.hostList = hostList;
		} catch (Exception e) {
			String message = "CreateOrUpdateFile Error." + e.getMessage();
			logger.error(message, e);
			throw new FileAccessException(message);
		}
		return true;
	}

	@Override
	public boolean addHost(String host) {
		throw new UnsupportedOperationException(
				"'HostInLocal.class' Can't Support Method:'addHosts'");
	}

}
