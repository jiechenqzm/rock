package com.nd.rock.client.obverser;

import java.util.List;
import java.util.Map;

public interface CallObserverExecutor {
	
	public void update(Map<String, List<String>> changeMap);

}
