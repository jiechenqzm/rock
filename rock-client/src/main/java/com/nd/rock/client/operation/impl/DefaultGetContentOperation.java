package com.nd.rock.client.operation.impl;

import com.nd.rock.client.operation.ContentOperation;
import com.nd.rock.common.exception.ServerUnAvailableException;
import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;

public class DefaultGetContentOperation implements ContentOperation {

	private ContentOperation getContentOperation = null;

	public DefaultGetContentOperation() {
		try {
			this.getContentOperation = new GetContentFromServer();
		} catch (ServerUnAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public FinalGetContentResponse getContent(GetContentParam getContentParam) {
		return this.getContentOperation.getContent(getContentParam);
	}

}
