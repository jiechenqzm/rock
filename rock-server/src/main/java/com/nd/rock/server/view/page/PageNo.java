package com.nd.rock.server.view.page;

public interface PageNo {
	
	public boolean isAvailable();
	
	public boolean isEllipsis();
	
	public boolean isCurrentPage();
	
	public int getValue();
	
	public String getName();

}
