package com.nd.rock.server.view.page.impl;

import java.util.Iterator;
import java.util.List;

import com.nd.rock.server.view.page.PageBehavor;
import com.nd.rock.server.view.page.PageItems;
import com.nd.rock.server.view.page.PageNo;

public class DefaultPageItem<E> implements PageItems<E> {

	private PageBehavor pageBehavor = null;

	private List<E> items = null;

	public DefaultPageItem(List<E> items, int totalCount, int pageSize,
			int currentPageNo) {
		this.items = items;
		this.pageBehavor = new DefaultPageBehavor(totalCount, pageSize,
				currentPageNo);
	}

	@Override
	public PageNo getFirstPageNo() {
		return pageBehavor.getFirstPageNo();
	}

	@Override
	public PageNo getPreviousEllipsis() {
		return pageBehavor.getPreviousEllipsis();
	}

	@Override
	public PageNo getPreviousPageNo() {
		return pageBehavor.getPreviousPageNo();
	}

	@Override
	public PageNo getCurrentPageNo() {
		return pageBehavor.getCurrentPageNo();
	}

	@Override
	public PageNo getNextPageNo() {
		return pageBehavor.getNextPageNo();
	}

	@Override
	public PageNo getNextEllipsis() {
		return pageBehavor.getNextEllipsis();
	}

	@Override
	public PageNo getLastPageNo() {
		return pageBehavor.getLastPageNo();
	}
	
	@Override
	public Iterator<PageNo> iterator() {
		return pageBehavor.iterator();
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
