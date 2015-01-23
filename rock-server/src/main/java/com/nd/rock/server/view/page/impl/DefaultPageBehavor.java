package com.nd.rock.server.view.page.impl;

import com.nd.rock.server.view.page.PageBehavor;

public class DefaultPageBehavor implements PageBehavor {

	private int totalCount;

	private int currentPageNo;

	private int pageSize;

	public DefaultPageBehavor(int totalCount, int pageSize, int currentPageNo) {
		this.totalCount = totalCount;
		this.currentPageNo = currentPageNo;
		this.pageSize = pageSize;
	}

	@Override
	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	@Override
	public int getPreviousPageNo() {
		return getFirstPageNo() < currentPageNo ? currentPageNo -1 : 0;
	}

	@Override
	public int getNextPageNo() {
		return this.getLastPageNo() > currentPageNo ? currentPageNo + 1 : 0;
	}

	@Override
	public int getFirstPageNo() {
		return 1;
	}

	@Override
	public int getLastPageNo() {
		return totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize + 1;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int getTotalCount() {
		return this.totalCount;
	}

}
