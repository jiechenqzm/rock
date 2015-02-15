package com.nd.rock.client.obverser.impl;

import com.nd.rock.client.facade.ContentObserver;
import com.nd.rock.client.obverser.InnerObverser;
import com.nd.rock.common.model.summary.MD5Summary;
import com.nd.rock.common.model.summary.SummaryBehavior;
import com.nd.rock.common.util.QStringUtil;

public class ContentObverserAdapter implements InnerObverser {

	private static SummaryBehavior summaryBehavior = new MD5Summary();

	private ContentObserver contentObserver = null;

	private String group = null;

	private String dataId = null;

	private String content = null;

	private String summary = null;

	public ContentObverserAdapter(String group, String dataId, String content,
			ContentObserver contentObserver) {
		this.group = group;
		this.dataId = dataId;
		this.contentObserver = contentObserver;
		this.setContent(content);
	}

	@Override
	public void update(String newContent) {
		if(!QStringUtil.equal(this.content, newContent)){
			this.contentObserver.update(newContent);
			this.setContent(newContent);
		}
	}

	@Override
	public String getSummary() {
		return this.summary;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
	public String getDataId() {
		return this.dataId;
	}

	public ContentObserver getContentObserver() {
		return contentObserver;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ContentObverserAdapter))
			return false;

		return ((ContentObverserAdapter) obj).getContentObserver() == this
				.getContentObserver();
	}
	
	private void setContent(String content){
		this.content = content;
		this.summary = summaryBehavior.calculate(content);
	}
}
