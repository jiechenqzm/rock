package com.nd.rock.client.obverser;

import java.util.List;
import java.util.Map;


public interface ObserverManager {

	public boolean registerObserver(InnerObverser innerObverser);

	public boolean removeObserver(InnerObverser innerObverser);
	
	public Map<String, Map<String, List<InnerObverser>>> iterator();
	
	public void update(String group, String dataId, String content);

}
