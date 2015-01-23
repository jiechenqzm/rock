package com.nd.rock.server.view.page.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.nd.rock.server.view.page.PageNo;

public class PageNoIterator implements Iterator<PageNo> {
	
	private List<PageNo> pageNoList = new ArrayList<PageNo>();
	
	private int index = 0;
	
	private PageNoIterator(List<PageNo> pageNoList) {
		this.pageNoList = pageNoList;
	}

	@Override
	public boolean hasNext() {
		return pageNoList.size() > index;
	}

	@Override
	public PageNo next() {
		return pageNoList.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("PageNoIterator Can't Remove Element!");
	}
	
	public static class Builder {
		private List<PageNo> builderList = new ArrayList<PageNo>();
		public void addElement(PageNo pageNo){
			builderList.add(pageNo);
		}
		public PageNoIterator build(){
			return new PageNoIterator(builderList);
		}
	}

}
