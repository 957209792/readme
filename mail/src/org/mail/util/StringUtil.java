package org.mail.util;

import java.util.UUID;

/**
 * 字符串工具类
 * @author VIC
 *
 */
public class StringUtil {
	
	public static String generatorString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return null == str || str.trim().length() <= 0;
	}
	
	/**
	 * 字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return null != str && str.trim().length() > 0;
	}

	/**
	 * 将字符串以指定符号分隔转换成int数组
	 * @param str
	 * @param regex
	 * @return
	 */
	public static int[] toIntArray(String str, String regex) {
		String[] ids = str.split(regex);
		int[] temps = new int[ids.length];
		for(int i = 0; i < ids.length; i++){
			temps[i] = Integer.valueOf(ids[i]);
		}
		return temps;
	}
	
}
