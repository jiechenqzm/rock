package com.nd.rock.server.controller.service.behavior.impl;

import java.io.IOException;

import com.nd.rock.server.controller.service.behavior.GetContentBehavior;
import com.nd.rock.server.exception.ServiceBehaviorFailException;
import com.nd.rock.server.model.container.Container;

public class GetFileContentBehavior implements GetContentBehavior {

	private Container fileContainer = null;

	@Override
	public String get(String group, String dataId) {
		try {
			return fileContainer.get(group, dataId);
		} catch (IOException e) {
			throw new ServiceBehaviorFailException("GetDataContent Failed.", e);
		}
	}

	public void setFileContainer(Container fileContainer) {
		this.fileContainer = fileContainer;
	}

}
