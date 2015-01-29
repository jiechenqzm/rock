package com.nd.rock.common.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import net.sf.json.JSONObject;

import org.junit.Test;

public class JsonLibTest {

	@Test
	public void fromBean2Json() {
		BeanClass beanClass = getBeanClass();

		JSONObject jsonObject = JSONObject.fromObject(beanClass);
		Assert.assertEquals(getString(), jsonObject.toString());

	}
	
	@Test
	public void fromJson2Bean() {
		JSONObject jsonObject = JSONObject.fromObject(getString());
		//如果BeanClass为内部类，则这里会报找不到构造函数的异常
		BeanClass beanClass = (BeanClass)jsonObject.toBean(jsonObject, BeanClass.class);
		Assert.assertNotNull(beanClass);
		
		BeanClass oriBean = getBeanClass();
		Assert.assertEquals(oriBean.getIntValue(), beanClass.getIntValue());
		Assert.assertEquals(oriBean.getString(), beanClass.getString());
		Assert.assertEquals(oriBean.getList().size(), beanClass.getList().size());
		Assert.assertEquals(oriBean.getMap().size(), beanClass.getMap().size());
	}
	
	private BeanClass getBeanClass() {
		BeanClass beanClass = new BeanClass();
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
		return beanClass;
	}
	
	private String getString() {
		return "{\"intValue\":100,\"list\":[\"a\",\"b\"],\"map\":{\"2\":\"b\",\"1\":\"aa\"},\"string\":\"string_value\"}";
	}

	
}
