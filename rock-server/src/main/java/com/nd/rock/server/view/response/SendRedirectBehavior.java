package com.nd.rock.server.view.response;

import java.io.IOException;

public interface SendRedirectBehavior {
	
	public void sendRedirect(String location) throws IOException;

}
