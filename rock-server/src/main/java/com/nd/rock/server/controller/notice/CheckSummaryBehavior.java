package com.nd.rock.server.controller.notice;

import java.util.List;
import java.util.Map;

public interface CheckSummaryBehavior {
	
	public boolean check(String group, String key, String summary);
	
	public Map<String/*group*/, List<String/*dataId*/>> check(
			Map<String/*group*/, Map<String/*dataId*/, String/*summary*/>> summarys);

}
