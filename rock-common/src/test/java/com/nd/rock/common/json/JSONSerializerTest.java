package com.nd.rock.common.json;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Test;

public class JSONSerializerTest {

	
	@Test
	public void fromBean2Json(){
		BeanClass beanClass = BeanClass.getBeanClass();

		JSON json = JSONSerializer.toJSON(beanClass);
		System.out.println(json.toString());
		Assert.assertEquals(BeanClass.getStringValue(), json.toString());
	}
	
	@Test
	public void fromJson2Bean() {
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( BeanClass.getStringValue() ); 
		JsonConfig jsonConfig = new JsonConfig();   
		jsonConfig.setRootClass( BeanClass.class );
		
		BeanClass beanClass = (BeanClass)JSONSerializer.toJava(jsonObject, jsonConfig);
		
		Assert.assertNotNull(beanClass);
		
		BeanClass oriBean = BeanClass.getBeanClass();
		Assert.assertEquals(oriBean.isOk(), beanClass.isOk());
		Assert.assertEquals(oriBean.getIntValue(), beanClass.getIntValue());
		Assert.assertEquals(oriBean.getString(), beanClass.getString());
		Assert.assertEquals(oriBean.getList().size(), beanClass.getList().size());
		Assert.assertEquals(oriBean.getMap().size(), beanClass.getMap().size());
		Assert.assertEquals(oriBean.getListMap().size(), beanClass.getListMap().size());
		Assert.assertNotNull(beanClass.getInnerClass());
		
		
		Map<String, List<BeanClass>> beanMap = beanClass.getBeanMap();
		System.out.println(beanMap);
	}

}
