package com.nd.rock.server.view.page;

public interface PageBehavor extends Iterable<PageNo> {

	public PageNo getFirstPageNo();

	public PageNo getPreviousEllipsis();

	public PageNo getPreviousPageNo();

	public PageNo getCurrentPageNo();

	public PageNo getNextPageNo();

	public PageNo getNextEllipsis();

	public PageNo getLastPageNo();

	public int getPageSize();

	public int getTotalCount();

}
