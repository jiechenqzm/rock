package com.nd.rock.server.controller.notice;

import java.util.List;
import java.util.Map;

public interface UserInterface {
	
	public Map<String, List<String>> checkSummary(Map<String/*group*/, Map<String/*key*/, String/*summary*/>> data);

	public Map<String/*group*/, Map<String/*key*/, String/*value*/>> getData(Map<String/*group*/, List<String/*key*/>> dataIds);

}