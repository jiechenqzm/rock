package com.nd.rock.common.net.bean;

import org.junit.Test;

import com.nd.rock.common.net.bean.impl.DefaultResponseBody;

public class DefaultResponseBodyTest {

	@Test
	public void toJSONString() {
		DefaultResponseBody<Object> defaultResponseBody = DefaultResponseBody.fail("abc");
		System.out.println(defaultResponseBody.toJSONString());
	}

}
