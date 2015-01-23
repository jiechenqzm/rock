package com.nd.rock.server.view.page.impl;

import java.util.List;

import com.nd.rock.server.view.page.PageBehavor;
import com.nd.rock.server.view.page.PageItems;

public class DefaultPageItem<E> implements PageItems<E> {
	
	private PageBehavor pageBehavor = null;
	
	private List<E> items = null;
	
	public DefaultPageItem(List<E> items, int totalCount, int pageSize, int currentPageNo) {
		this.items = items;
		
	}

	@Override
	public int getCurrentPageNo() {
		return this.pageBehavor.getCurrentPageNo();
	}

	@Override
	public int getPreviousPageNo() {
		return this.pageBehavor.getPreviousPageNo();
	}

	@Override
	public int getNextPageNo() {
		return this.pageBehavor.getNextPageNo();
	}

	@Override
	public int getFirstPageNo() {
		return this.pageBehavor.getFirstPageNo();
	}

	@Override
	public int getLastPageNo() {
		return this.pageBehavor.getLastPageNo();
	}

	@Override
	public int getPageSize() {
		return this.pageBehavor.getPageSize();
	}

	@Override
	public int getTotalCount() {
		return this.pageBehavor.getTotalCount();
	}

	@Override
	public List<E> getItems() {
		return items;
	}

}
