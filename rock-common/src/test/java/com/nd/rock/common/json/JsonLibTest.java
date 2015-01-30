package com.nd.rock.common.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * JSON-LIB可以支持二级数据结构类似 Map<String, List<String>> JSON-LIB可以支持对象引用结构类型
 * classA.classB
 * 
 * @author QiuZongming
 *
 */
public class JsonLibTest {

	@Test
	public void fromBean2Json() {
		BeanClass beanClass = BeanClass.getBeanClass();

		JSONObject jsonObject = JSONObject.fromObject(beanClass);
		System.out.println(jsonObject.toString());
		Assert.assertEquals(BeanClass.getStringValue(), jsonObject.toString());

	}

	@Test
	public void fromJson2Bean() {
		JSONObject jsonObject = JSONObject.fromObject(BeanClass.getStringValue());
		// 如果BeanClass为内部类，则这里会报找不到构造函数的异常
		
//		Map<String, Class> classMap = new HashMap<>();
//		classMap.put("mapMapMap", Map.Entry<String, Map.class>.classMap);
		BeanClass beanClass = (BeanClass) JSONObject.toBean(jsonObject,
				BeanClass.class);
		Assert.assertNotNull(beanClass);

		BeanClass oriBean = BeanClass.getBeanClass();
		Assert.assertEquals(oriBean.isOk(), beanClass.isOk());
		Assert.assertEquals(oriBean.getIntValue(), beanClass.getIntValue());
		Assert.assertEquals(oriBean.getString(), beanClass.getString());
		Assert.assertEquals(oriBean.getList().size(), beanClass.getList()
				.size());
		Assert.assertEquals(oriBean.getMap().size(), beanClass.getMap().size());
		Assert.assertEquals(oriBean.getListMap().size(), beanClass.getListMap()
				.size());
		Assert.assertNotNull(beanClass.getInnerClass());
		
		for(Map.Entry<String, HashMap<String, HashMap<String, String>>> map : beanClass.getMapMapMap().entrySet()) {
			for(Map.Entry<String, HashMap<String, String>> map1 : map.getValue().entrySet()) {
				for(Map.Entry<String, String> map2 : map1.getValue().entrySet()) {
					System.out.println(map.getKey() + "##" + map1.getKey() + "##" + map2.getKey() + "##" + map2.getValue());
				}
			}
		}
		
	}

	

}
