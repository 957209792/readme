package org.mail.util;

/**
 * 数组工具类
 * @author VIC
 *
 */
public class ArrayUtil {

	/**
	 * 判断数组为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(String[] array) {
		if(null == array){
			return true;
		}
		for(String str : array){
			if(StringUtil.isEmpty(str)){
				return true;
			}
		}
		return false;
	}

}
