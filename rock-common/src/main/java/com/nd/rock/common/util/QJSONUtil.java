package com.nd.rock.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QJSONUtil {
	
	@SuppressWarnings("rawtypes")
	public static Map<String, String> parse2StringMap(JSONObject jsonObject) {
		if(jsonObject == null)
			return null;
		Map<String, String> result = new HashMap<>();
		for(Iterator iterator = jsonObject.keys() ; iterator.hasNext(); ) {
			String key = (String)iterator.next();
			result.put(key, jsonObject.getString(key));
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<String> parse2StringList(JSONArray jsonArray) {
		if(jsonArray == null)
			return null;
		List<String> result = new ArrayList<>();
		for(Iterator iterator = jsonArray.iterator() ; iterator.hasNext(); ) {
			result.add((String)iterator.next());
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String, JSONObject>  parse2JSONObject(JSONObject jsonObject) {
		if(jsonObject == null)
			return null;
		Map<String, JSONObject> result = new HashMap<>();
		for(Iterator iterator = jsonObject.keys() ; iterator.hasNext(); ) {
			String key = (String)iterator.next();
			result.put(key, jsonObject.getJSONObject(key));
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String, JSONArray> parse2JSONArray(JSONObject jsonObject) {
		if(jsonObject == null)
			return null;
		Map<String, JSONArray> result = new HashMap<>();
		for(Iterator iterator = jsonObject.keys() ; iterator.hasNext(); ) {
			String key = (String)iterator.next();
			result.put(key, jsonObject.getJSONArray(key));
		}
		return result;
	}
	
	public static Map<String, Map<String, String>> parse2MapMap(JSONObject jsonObject) {
		Map<String, Map<String, String>> result = new HashMap<>();
		Map<String, JSONObject> jsonMap = parse2JSONObject(jsonObject);
		for(Map.Entry<String, JSONObject> entry : jsonMap.entrySet()) {
			result.put(entry.getKey(), parse2StringMap(entry.getValue()));
		}
		return result;
	}
	
	public static Map<String, List<String>> parse2MapList(JSONObject jsonObject) {
		Map<String, List<String>> result = new HashMap<>();
		Map<String, JSONArray> jsonMapArray = parse2JSONArray(jsonObject);
		for(Map.Entry<String, JSONArray> entry : jsonMapArray.entrySet()) {
			result.put(entry.getKey(), parse2StringList(entry.getValue()));
		}
		return result;
	}

}