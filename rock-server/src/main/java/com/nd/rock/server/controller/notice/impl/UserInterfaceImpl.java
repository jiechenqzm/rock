package com.nd.rock.server.controller.notice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nd.rock.server.controller.notice.CheckSummaryBehavior;
import com.nd.rock.server.controller.notice.ReadDataBehavior;
import com.nd.rock.server.controller.notice.UserInterface;

public class UserInterfaceImpl implements UserInterface {
	
	private ReadDataBehavior readDataBehavior = null;
	
	private CheckSummaryBehavior checkSummaryBehavior = null;
	
	@Override
	public Map<String, List<String>> checkSummary(
			Map<String, Map<String, String>> data) {

		Map<String, List<String>> result = new HashMap<String, List<String>>(); 
		for(Map.Entry<String, Map<String, String>> groupEntry : data.entrySet()) {
			for(Map.Entry<String, String> dataIdEntry : groupEntry.getValue().entrySet()) {
				if(!this.checkSummaryBehavior.check(groupEntry.getKey(), dataIdEntry.getKey(), dataIdEntry.getValue())) {
					List<String> dataIds = result.get(groupEntry.getKey());
					if(dataIds == null) {
						dataIds = new ArrayList<>();
						result.put(groupEntry.getKey(), dataIds);
					}
					dataIds.add(dataIdEntry.getKey());
				}
			}
		}
		return result;
	}
	
	@Override
	public Map<String, Map<String, String>> getData(
			Map<String, List<String>> dataIds) {
		
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		for(Map.Entry<String, List<String>> groupEntry : dataIds.entrySet()) {
			Map<String, String> map = new HashMap<String, String>();
			result.put(groupEntry.getKey(), map);
			for(String dataId : groupEntry.getValue()) {
				map.put(dataId, this.readDataBehavior.read(groupEntry.getKey(), dataId));
			}
		}
		return result;
	}
}