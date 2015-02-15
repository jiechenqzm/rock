package com.nd.rock.client.obverser;

public interface InnerObverser {

	public void update(String newContent);

	public String getSummary();
	
	public String getGroup();
	
	public String getDataId();

	public String getContent();

}
