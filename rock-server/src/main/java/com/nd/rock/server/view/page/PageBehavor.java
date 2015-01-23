package com.nd.rock.server.view.page;

public interface PageBehavor {

	public int getCurrentPageNo();
	
	public int getPreviousPageNo();
	
	public int getNextPageNo();
	
	public int getFirstPageNo();
	
	public int getLastPageNo();

	public int getPageSize();
	
	public int getTotalCount();

}
