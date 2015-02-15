package com.nd.rock.common.util;

public class QStringUtil {
	
	/**
	 * 判断string是否为null
	 * @param string
	 * @return
	 */
	public static boolean isNull(String string) {
		return string == null;
	}
	
	/**
	 * 判断string是否为null或者为空字符串
	 * 
	 * @param string
	 * @return
	 */
	public static boolean nullOrEmpty(String string) {
		return isNull(string) ? true : string.trim().isEmpty();
	}
	
	/**
	 * 判断string是否为null或者为空字符串
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean notEmpty(String... strings) {
		boolean result = true;
		for(String string : strings) {
			result = result && !nullOrEmpty(string);
		}
		return result;
	}
	
	public static boolean equal(String string1, String string2) {
		if(string1 == null && string2 == null)
			return true;
		else if(string1 != null && string2 != null)
			return string1.equals(string2);
		return false;
	}

}