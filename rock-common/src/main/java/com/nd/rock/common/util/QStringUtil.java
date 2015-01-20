package com.nd.rock.common.util;

public class QStringUtil {
	
	/**
	 * 判断string是否为null或者为空字符串
	 * 
	 * @param string
	 * @return
	 */
	public static boolean nullOrEmpty(String string) {
		return string == null ? true : string.trim().isEmpty();
	}

}