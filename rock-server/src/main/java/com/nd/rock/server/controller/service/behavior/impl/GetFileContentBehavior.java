package com.nd.rock.server.controller.service.behavior.impl;

import java.io.IOException;

import com.nd.rock.common.model.container.Container;
import com.nd.rock.server.controller.service.behavior.GetContentBehavior;
import com.nd.rock.server.exception.ServiceBehaviorFailException;

public class GetFileContentBehavior implements GetContentBehavior {

	private Container container = null;

	@Override
	public String get(String group, String dataId) {
		try {
			return container.get(group, dataId);
		} catch (IOException e) {
			throw new ServiceBehaviorFailException("GetDataContent Failed.", e);
		}
	}

	public void setContainer(Container container) {
		this.container = container;
	}

}
