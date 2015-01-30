package com.nd.rock.common.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * JSON-LIB可以支持二级数据结构类似 Map<String, List<String>>
 * JSON-LIB可以支持对象引用结构类型 classA.classB
 * @author QiuZongming
 *
 */
public class JsonLibTest {

	@Test
	public void fromBean2Json() {
		BeanClass beanClass = getBeanClass();

		JSONObject jsonObject = JSONObject.fromObject(beanClass);
		System.out.println(jsonObject.toString());
		Assert.assertEquals(getString(), jsonObject.toString());

	}
	
	@Test
	public void fromJson2Bean() {
		JSONObject jsonObject = JSONObject.fromObject(getString());
		//如果BeanClass为内部类，则这里会报找不到构造函数的异常
		BeanClass beanClass = (BeanClass)JSONObject.toBean(jsonObject, BeanClass.class);
		Assert.assertNotNull(beanClass);
		
		BeanClass oriBean = getBeanClass();
		Assert.assertEquals(oriBean.isOk(), beanClass.isOk());
		Assert.assertEquals(oriBean.getIntValue(), beanClass.getIntValue());
		Assert.assertEquals(oriBean.getString(), beanClass.getString());
		Assert.assertEquals(oriBean.getList().size(), beanClass.getList().size());
		Assert.assertEquals(oriBean.getMap().size(), beanClass.getMap().size());
		Assert.assertEquals(oriBean.getListMap().size(), beanClass.getListMap().size());
		Assert.assertNotNull(beanClass.getInnerClass());
	}
	
	private BeanClass getBeanClass() {
		BeanClass beanClass = new BeanClass();
		beanClass.setOk(true);
		beanClass.setIntValue(100);
		beanClass.setString("string_value");
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		Map<String, String> map = new HashMap<>();
		map.put("1", "aa");
		map.put("2", "b");
		beanClass.setList(list);
		beanClass.setMap(map);
		Map<String, List<String>> listMap = new HashMap<>();
		listMap.put("100", list);
		listMap.put("200", list);
		listMap.put("300", list);
		beanClass.setListMap(listMap);
		
		BeanClass innerClass = new BeanClass();
		innerClass.setIntValue(101);
		innerClass.setString("abc");
		
		beanClass.setInnerClass(innerClass);
		
		
		return beanClass;
	}
	
	private String getString() {
		return "{\"innerClass\":{\"innerClass\":null,\"intValue\":101,\"list\":[],\"listMap\":null,\"map\":null,\"ok\":false,\"string\":\"abc\"},\"intValue\":100,\"list\":[\"a\",\"b\"],\"listMap\":{\"300\":[\"a\",\"b\"],\"200\":[\"a\",\"b\"],\"100\":[\"a\",\"b\"]},\"map\":{\"2\":\"b\",\"1\":\"aa\"},\"ok\":true,\"string\":\"string_value\"}";
	}

	
}
