package com.nd.rock.server.view.page.impl;

import com.nd.rock.server.view.page.PageNo;

public class EllipsisPageNo implements PageNo {

	@Override
	public boolean isAvailable() {
		return true;
	}

	@Override
	public boolean isEllipsis() {
		return true;
	}
	
	@Override
	public boolean isCurrentPage() {
		return false;
	}
	
	@Override
	public int getValue() {
		return 0;
	}
	
	@Override
	public String getName() {
		return "...";
	}

}
