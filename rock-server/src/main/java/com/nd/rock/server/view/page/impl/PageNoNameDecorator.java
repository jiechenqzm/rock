package com.nd.rock.server.view.page.impl;

import com.nd.rock.server.view.page.PageNo;

public class PageNoNameDecorator implements PageNo {
	
	private PageNo pageNo = null;
	
	private String newName = null;
	
	public PageNoNameDecorator(PageNo pageNo, String newName) {
		this.pageNo = pageNo;
		this.newName = newName;
	}

	@Override
	public boolean isAvailable() {
		return pageNo.isAvailable();
	}

	@Override
	public boolean isEllipsis() {
		return pageNo.isEllipsis();
	}
	
	@Override
	public boolean isCurrentPage() {
		return pageNo.isCurrentPage();
	}

	@Override
	public int getValue() {
		return pageNo.getValue();
	}

	@Override
	public String getName() {
		return newName;
	}

}
