package com.nd.rock.server.view.page.impl;

import com.nd.rock.server.view.page.PageNo;

public class DefaultPageNo implements PageNo {
	
	private int value = 0 ;
	
	private boolean isCurrentPage = false;
	
	public DefaultPageNo(int value) {
		this(value, false);
	}
	
	public DefaultPageNo(int value, boolean isCurrentPage) {
		this.value = value;
		this.isCurrentPage = isCurrentPage;
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

	@Override
	public boolean isEllipsis() {
		return false;
	}
	
	@Override
	public boolean isCurrentPage() {
		return isCurrentPage;
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String getName() {
		return String.valueOf(value);
	}

}
