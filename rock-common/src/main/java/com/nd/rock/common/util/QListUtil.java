package com.nd.rock.common.util;

import java.util.List;

public class QListUtil {

	/**
	 * 判断list是否为null或者empty
	 * 
	 * @param list
	 * @return
	 */
	public static boolean nullOrEmpty(List<?> list) {
		return list == null ? true : list.isEmpty();
	}

	/**
	 * 判断两个list的值是否相等
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean equal(List<String> list1, List<String> list2) {
		if (list1 == null && list2 == null)
			return true;

		if (list1 != null && list2 != null) {
			if (list1.size() != list2.size())
				return false;
			for (String str : list1) {
				if (!list2.contains(str))
					return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 判断list中的每一个元素都不能为null或者empty
	 * @param list
	 * @return
	 */
	public static boolean notEmptyContent(List<String> list) {
		boolean result = true;
		for(String string : list)
			result = result && QStringUtil.nullOrEmpty(string);
		return result;
	}
}
