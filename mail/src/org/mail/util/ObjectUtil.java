package org.mail.util;

/**
 * 对象工具类
 * @author VIC
 *
 */
public class ObjectUtil {
	
    /**
    *
    * 获取默认实例
    * @param entityClass
    * @param <T>
    * @return
    */
   public static <T> T getInstance(Class<T> entityClass) {
       try{
           return (T)entityClass.getConstructor().newInstance();
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
   }

}
