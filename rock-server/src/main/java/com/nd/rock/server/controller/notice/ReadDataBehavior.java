package com.nd.rock.server.controller.notice;

import java.util.List;
import java.util.Map;

public interface ReadDataBehavior {
	
	public String read(String group, String dataId);
	
	public Map<String/*group*/, Map<String/*dataId*/, String/*value*/>> read(
			Map<String/*group*/, List<String/*dataId*/>> dataIds);

}
