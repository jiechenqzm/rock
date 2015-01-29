package com.nd.rock.common.json;

import java.util.List;
import java.util.Map;

public class BeanClass {
	private String string;
	private int intValue;
	private List<String> list;
	private Map<String, String> map;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}