package com.nd.rock.common.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanClass {
	
	private boolean ok;
	private String string;
	private int intValue;
	private List<String> list;
	private Map<String, String> map;
	private Map<String, List<String>> listMap;
	private BeanClass innerClass = null;
	private Map<String, HashMap<String, HashMap<String, String>>> mapMapMap;
	private Map<String, List<BeanClass>> beanMap;

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

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Map<String, HashMap<String, HashMap<String, String>>> getMapMapMap() {
		return mapMapMap;
	}

	public void setMapMapMap(Map<String, HashMap<String, HashMap<String, String>>> mapMapMap) {
		this.mapMapMap = mapMapMap;
	}

	public Map<String, List<BeanClass>> getBeanMap() {
		return beanMap;
	}

	public void setBeanMap(Map<String, List<BeanClass>> beanMap) {
		this.beanMap = beanMap;
	}
	
	public static BeanClass getBeanClass() {
		BeanClass beanClass = new BeanClass();
		beanClass.setOk(true);
		beanClass.setIntValue(100);
		beanClass.setString("string_value");
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "aa");
		map.put("2", "b");
		beanClass.setList(list);
		beanClass.setMap(map);
		Map<String, List<String>> listMap = new HashMap<>();
		listMap.put("100", list);
		listMap.put("200", list);
		listMap.put("300", list);
		beanClass.setListMap(listMap);

		HashMap<String, HashMap<String, String>> map1 = new HashMap<>();
		map1.put("aaa", map);
		map1.put("bbb", map);

		Map<String, HashMap<String, HashMap<String, String>>> map2 = new HashMap<>();
		map2.put("111", map1);
		map2.put("222", map1);
		beanClass.setMapMapMap(map2);

		BeanClass innerClass = new BeanClass();
		innerClass.setIntValue(101);
		innerClass.setString("abc");

		beanClass.setInnerClass(innerClass);
		
		Map<String, List<BeanClass>> beanMap = new HashMap<>();
		List<BeanClass> list1 = new ArrayList<>();
		list1.add(innerClass);
		list1.add(innerClass);
		
		beanMap.put("!!!", list1);
		beanMap.put("@@@", list1);
		
		beanClass.setBeanMap(beanMap);

		return beanClass;
	}

	public static String getStringValue() {
		return "{\"beanMap\":{\"!!!\":[{\"beanMap\":null,\"innerClass\":null,\"intValue\":101,\"list\":[],\"listMap\":null,\"map\":null,\"mapMapMap\":null,\"ok\":false,\"string\":\"abc\"},{\"beanMap\":null,\"innerClass\":null,\"intValue\":101,\"list\":[],\"listMap\":null,\"map\":null,\"mapMapMap\":null,\"ok\":false,\"string\":\"abc\"}],\"@@@\":[{\"beanMap\":null,\"innerClass\":null,\"intValue\":101,\"list\":[],\"listMap\":null,\"map\":null,\"mapMapMap\":null,\"ok\":false,\"string\":\"abc\"},{\"beanMap\":null,\"innerClass\":null,\"intValue\":101,\"list\":[],\"listMap\":null,\"map\":null,\"mapMapMap\":null,\"ok\":false,\"string\":\"abc\"}]},\"innerClass\":{\"beanMap\":null,\"innerClass\":null,\"intValue\":101,\"list\":[],\"listMap\":null,\"map\":null,\"mapMapMap\":null,\"ok\":false,\"string\":\"abc\"},\"intValue\":100,\"list\":[\"a\",\"b\"],\"listMap\":{\"300\":[\"a\",\"b\"],\"200\":[\"a\",\"b\"],\"100\":[\"a\",\"b\"]},\"map\":{\"2\":\"b\",\"1\":\"aa\"},\"mapMapMap\":{\"222\":{\"aaa\":{\"2\":\"b\",\"1\":\"aa\"},\"bbb\":{\"2\":\"b\",\"1\":\"aa\"}},\"111\":{\"aaa\":{\"2\":\"b\",\"1\":\"aa\"},\"bbb\":{\"2\":\"b\",\"1\":\"aa\"}}},\"ok\":true,\"string\":\"string_value\"}";
	}
}