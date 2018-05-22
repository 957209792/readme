package org.mail.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象映射：使用反射将ResultSet结果集映射成对象或对象集合
 * @author VIC
 *
 * @param <T>
 */
public abstract class ObjectMapper<T> {

	
	public ObjectMapper(){}
	
	/**
	 * 创建T泛型实例对象
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("hiding")
	public <T> T getTInstnce() throws InstantiationException, IllegalAccessException {
		// 获取泛型类型
		@SuppressWarnings("unchecked")
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		// 创建实例对象
		return (T) entityClass.newInstance();
	}

	protected List<T> mapper(ResultSet resultSet) {
		List<T> mapperObjects = new ArrayList<T>();
		if (null != resultSet) {
			try {
				// 提取数据放入到对象中
				while (resultSet.next()) {
					// 使用反射创建对象
					// T t = (T)clz.newInstance();
					T t = getTInstnce();
					// 获取结果集元数据
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					// 获取结果集返回的列数
					int columnCount = resultSetMetaData.getColumnCount();
					
					
					// 获取类字段
					Field[] fields = t.getClass().getDeclaredFields();
					// 提取结果集中的数据
					for (int i = 0; i < columnCount; i++) {
						// 获取列名
						String columnName = resultSetMetaData.getColumnName(i + 1);
						
						String columnTypeName = resultSetMetaData.getColumnTypeName(i + 1);
						// 查找与类名对应的属性字段
						for (Field field : fields) {
							// 破解私有属性访问限制
							field.setAccessible(true);
							// 如果列名和对象字段属性名是否相同
							if (field.getName().toUpperCase().equals(columnName)) {
								// 根据类型从结果集中获取值设置到对象字段属性
								Object value = getValueByType(columnTypeName, columnName, resultSet);
								field.set(t, value);
								break;
							}
						}
					}
					// 将映射好的对象添加到映射对象集合中
					mapperObjects.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回映射对象集合
		return mapperObjects;
	}

	/**
	 * 根据类型使用对应的方法从结果集中获取数据
	 * 
	 * @param columnTypeName
	 *            字段类型
	 * @param columnName
	 *            字段名称
	 * @param resultSet
	 *            结果集
	 * @return 字段值
	 */
	private Object getValueByType(String columnTypeName, String columnName, ResultSet resultSet) {

		Object value = new Object();
		try {
			switch (columnTypeName) {
			case "NUMBER":
				value = resultSet.getInt(columnName);// NUMBER类型则getInt
				break;
			case "VARCHAR2":
				value = resultSet.getString(columnName);// VARCHAR2类型则getString
				break;
			case "TIMESTAMP":
				value = resultSet.getDate(columnName);// VARCHAR2类型则getString
				break;
			default:
				value = resultSet.getObject(columnName);// 其他类型则getObject
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
