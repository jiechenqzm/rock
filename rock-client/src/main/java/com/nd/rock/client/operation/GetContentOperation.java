package com.nd.rock.client.operation;

import com.nd.rock.common.net.bean.request.GetContentParam;
import com.nd.rock.common.net.bean.response.FinalGetContentResponse;

public interface GetContentOperation extends OperationConstants {
	
	public FinalGetContentResponse getContent(GetContentParam getContentParam);

}
