package com.nd.rock.common.json;

import java.util.List;
import java.util.Map;

public class BeanClass {
	private String string;
	private int intValue;
	private List<String> list;
	private Map<String, String> map;
	private Map<String, List<String>> listMap;
	private BeanClass innerClass = null;

	public Map<String, List<String>> getListMap() {
		return listMap;
	}

	public void setListMap(Map<String, List<String>> listMap) {
		this.listMap = listMap;
	}

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

	public BeanClass getInnerClass() {
		return innerClass;
	}

	public void setInnerClass(BeanClass innerClass) {
		this.innerClass = innerClass;
	}
}