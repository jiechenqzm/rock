package com.nd.rock.server.view.page.impl;

import com.nd.rock.server.view.page.PageNo;

public class NullPageNo implements PageNo {
	
	private static final int NULL_VALUE = 0;
	
	private static NullPageNo instance = new NullPageNo();
	
	private NullPageNo() {}

	@Override
	public boolean isAvailable() {
		return false;
	}

	@Override
	public boolean isEllipsis() {
		return false;
	}
	
	@Override
	public boolean isCurrentPage() {
		return false;
	}
	
	@Override
	public int getValue() {
		return NULL_VALUE;
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	public static NullPageNo getInstance(){
		return instance;
	}

}
