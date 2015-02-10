package com.nd.rock.common.net.host;

import java.util.List;

public interface HostHolder {

	public List<String> getHostList();
	
	public boolean saveHostList(List<String> hostList);
	
	public boolean addHost(String host);
	
}
