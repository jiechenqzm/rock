package com.nd.rock.server.controller.service.behavior.impl;

import com.nd.rock.server.controller.service.behavior.CheckContentBehavior;
import com.nd.rock.server.model.container.Container;

public class CheckSummaryBehavior implements CheckContentBehavior {

	private Container fileContainer = null;

	@Override
	public boolean check(String group, String dataId, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFileContainer(Container fileContainer) {
		this.fileContainer = fileContainer;
	}


}
