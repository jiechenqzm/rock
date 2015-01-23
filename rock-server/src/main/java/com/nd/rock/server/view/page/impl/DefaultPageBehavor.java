package com.nd.rock.server.view.page.impl;

import java.util.Iterator;
import java.util.List;

import com.nd.rock.server.view.page.PageBehavor;
import com.nd.rock.server.view.page.PageNo;

/**
 * @author QiuZongming
 *
 *         [上一页][1]...[4][5][6]...[11][下一页]
 * 
 *         负责以上分页模式的逻辑生成部分
 * 
 *         每个api返回值为0则表示不可用 比如当前页码为1，调用getPreviousPageNo的返回值会是0
 */
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
	public PageNo getFirstPageNo() {
		PageNo previousPageNo = getPreviousPageNo();
		return previousPageNo.isAvailable() && previousPageNo.getValue() > 1 ? new DefaultPageNo(
				1) : NullPageNo.getInstance();
	}

	@Override
	public PageNo getPreviousEllipsis() {
		PageNo firstPageNo = getFirstPageNo();
		PageNo previousPageNo = getPreviousPageNo();
		return firstPageNo.isAvailable() && previousPageNo.isAvailable()
				&& previousPageNo.getValue() - firstPageNo.getValue() > 2 ? new EllipsisPageNo()
				: NullPageNo.getInstance();
	}

	@Override
	public PageNo getPreviousPageNo() {
		return currentPageNo > 1 ? new DefaultPageNo(currentPageNo - 1)
				: NullPageNo.getInstance();
	}

	@Override
	public PageNo getCurrentPageNo() {
		return new DefaultPageNo(currentPageNo, true);
	}

	@Override
	public PageNo getNextPageNo() {
		return currentPageNo < calculateLastPageNo() ? new DefaultPageNo(
				currentPageNo + 1) : NullPageNo.getInstance();
	}

	@Override
	public PageNo getNextEllipsis() {
		PageNo nextPageNo = getNextPageNo();
		PageNo lastPageNo = getLastPageNo();
		return nextPageNo.isAvailable() && lastPageNo.isAvailable()
				&& lastPageNo.getValue() - nextPageNo.getValue() > 2 ? new EllipsisPageNo()
				: NullPageNo.getInstance();
	}

	@Override
	public PageNo getLastPageNo() {
		PageNo nextPageNo = getNextPageNo();
		return nextPageNo.isAvailable()
				&& nextPageNo.getValue() < calculateLastPageNo() ? new DefaultPageNo(
				calculateLastPageNo()) : NullPageNo.getInstance();

	}

	@Override
	public Iterator<PageNo> iterator() {
		PageNoIterator.Builder builder = new PageNoIterator.Builder();

		fillBuilder(builder,
				new PageNoNameDecorator(getPreviousPageNo(), "上一页"));
		fillBuilder(builder, this.getFirstPageNo());
		fillBuilder(builder, this.getPreviousEllipsis());
		fillBuilder(builder, this.getPreviousPageNo());
		fillBuilder(builder, this.getCurrentPageNo());
		fillBuilder(builder, this.getNextPageNo());
		fillBuilder(builder, this.getNextEllipsis());
		fillBuilder(builder, this.getLastPageNo());
		fillBuilder(builder, new PageNoNameDecorator(getNextPageNo(), "下一页"));
		return builder.build();
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getTotalCount() {
		return totalCount;
	}

	private int calculateLastPageNo() {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount
				/ pageSize + 1;
	}

	private void fillBuilder(PageNoIterator.Builder builder, PageNo pageNo) {
		if (pageNo.isAvailable())
			builder.addElement(pageNo);
	}

}
