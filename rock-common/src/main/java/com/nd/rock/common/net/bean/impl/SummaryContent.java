package com.nd.rock.common.net.bean.impl;

public class SummaryContent {
	
	private String dataId;
	
	private String summary;
	
	public SummaryContent(String dataId, String summary) {
		this.dataId = dataId;
		this.summary = summary;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
