package com.nd.rock.client.facade.decorator;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.client.facade.Client;
import com.nd.rock.client.facade.ContentObserver;

/**
 * Client的日志装饰者
 * @author QiuZongming
 *
 */
public class LogClientDecorator extends AbstractClientDecorator {
	
    private static final Logger logger = LoggerFactory.getLogger(LogClientDecorator.class);
	
	public LogClientDecorator(Client client) {
		super(client);
	}

	@Override
	public String getContent(String group, String dataId) {
		logger.info("Start GetContent, Group >> " + group + " ## dataId >> " + dataId);
		return super.client.getContent(group, dataId);
	}

	@Override
	public Map<String, String> getContentBatch(String group, List<String> dataIds) {
		logger.info("Start GetContentBatch, Group >> " + group + " ## dataIds >> " + dataIds);
		return super.client.getContentBatch(group, dataIds);
	}

	@Override
	public void registerObserver(String group, String dataId, String content,
			ContentObserver contentObserver) {
		logger.info("Start AddObserver, Group >> " + group + " ## dataId >> " + dataId + " ## content >> " + content);
		super.client.registerObserver(group, dataId, content, contentObserver);
	}

}
