package com.nd.rock.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

public class QJSONUtil {
	
	@SuppressWarnings("rawtypes")
	public static Map<String, String> parse2StringValue(JSONObject jsonObject) {
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
	public static Map<String, JSONObject>  parse2JSONValue(JSONObject jsonObject) {
		if(jsonObject == null)
			return null;
		Map<String, JSONObject> result = new HashMap<>();
		for(Iterator iterator = jsonObject.keys() ; iterator.hasNext(); ) {
			String key = (String)iterator.next();
			result.put(key, jsonObject.getJSONObject(key));
		}
		return result;
	}
	
	public static Map<String, Map<String, String>> parse2MapMap(JSONObject jsonObject) {
		Map<String, Map<String, String>> result = new HashMap<>();
		Map<String, JSONObject> jsonMap = parse2JSONValue(jsonObject);
		for(Map.Entry<String, JSONObject> entry : jsonMap.entrySet()) {
			result.put(entry.getKey(), parse2StringValue(entry.getValue()));
		}
		return result;
	}

}