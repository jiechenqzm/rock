package com.nd.rock.common.net.bean.impl;

public class DataKeyOb {
	
	private String group;
	
	private String dataId;

	public DataKeyOb(String group, String dataId) {
		this.group = group;
		this.dataId = dataId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}
