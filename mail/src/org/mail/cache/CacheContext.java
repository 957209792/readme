package org.mail.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存上下文(内存实现)
 * 
 * @author VIC
 *
 */
public class CacheContext {
	
	public static enum Keys {
		_AUTH_CACHE_KEY,_CACHE_KEY,DEFAULT_AUTHORITY_KEY
	}
	
	/**
	 * 缓存(后期可使用缓存服务器存储)
	 */
	private static Map<String, Object> cacheMap = Collections.synchronizedMap(new HashMap<String, Object>());
	
	@SuppressWarnings("unchecked")
	public static <T> T getCache(String key,Class<T> entityClass){
		if(cacheMap.containsKey(key)){
			return (T)cacheMap.get(key);
		}
		return null;
	}
	
	public static void setCache(String key,Object value){
		cacheMap.put(key, value);
	}
	
	public static boolean removeCache(String key){
		cacheMap.remove(key);
		return true;
	}

}
