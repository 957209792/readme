package org.mail.util;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 * JSON工具类: 该工具类依赖gson
 * @author VIC
 *
 */
public class JsonUtil {

	static Gson gson = new Gson();
	
	/**
	 * 对象转JSON字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object){
		return gson.toJson(object).toString();
	}

    public static <T> T fromJson(String json,Class<T> tClass){
        return gson.fromJson(json,tClass);
    }

    public static <T> T fromJson(String json, Type t){
        return gson.fromJson(json,t);
    }
	
}
