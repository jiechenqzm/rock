package com.nd.rock.server.view.page.impl;

public class PageArgs {
	
	private int pageNo;
	
	private int pageSize;
	
	public PageArgs(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

}
