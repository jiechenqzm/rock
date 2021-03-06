package com.nd.rock.server.controller.service.behavior.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.model.container.Container;
import com.nd.rock.common.util.QStringUtil;
import com.nd.rock.server.controller.service.behavior.CheckContentBehavior;

public class CheckSummaryBehavior implements CheckContentBehavior {
	
    private static final Logger logger = LoggerFactory.getLogger(CheckSummaryBehavior.class);

	private Container container = null;

	@Override
	public boolean check(String group, String dataId, String content) {
		String containerValue = null;
		try {
			containerValue = container.get(group, dataId);
		} catch (IOException e) {
			//不会走到这里来！！！！
			logger.error("GetSummary Error.", e);
		}
		return QStringUtil.equal(containerValue, content);
	}

	public void setContainer(Container container) {
		this.container = container;
	}


}
