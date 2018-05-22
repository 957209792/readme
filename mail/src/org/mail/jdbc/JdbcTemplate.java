package org.mail.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC 泛型工具类(Dao模式)
 * @author VIC
 *
 */
public abstract class JdbcTemplate<T> extends ObjectMapper<T>  {
	
	
	public JdbcTemplate() {
		
	}

	// JDBC配置对象
	private JdbcConfig jdbcConfig = JdbcConfig.getInstance();
	
	/**
	 * 获取连接
	 * @return
	 */
	private Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 查询数据
	 * @param sql
	 * @param targetClass
	 * @param args
	 * @return
	 */
	public List<T> quary(String sql,Object...args){
		List<T> objects = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			System.out.println("获得连接");
			preparedStatement = connection.prepareStatement(sql);
			setingParameter(preparedStatement, args);
			//测试..
			resultSet = preparedStatement.executeQuery();
			// 将结果集映射为对象集
			objects = mapper(resultSet);
			System.out.println(objects.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			release(resultSet, preparedStatement, connection);
		}
		return objects;
	}
	
	/**
	 * 添加,删除,修改
	 * @param sql
	 * @param args
	 * @return
	 */
	public Integer execute(String sql,Object...args){
		int modify = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 获取连接
			connection = getConnection();
			// 创建执行预编译SQL的对象
			preparedStatement = connection.prepareStatement(sql);
			// 设置参数(如果有参数)
			setingParameter(preparedStatement, args);
			// 执行SQL
			modify = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭连接
			release(preparedStatement,connection);
		}
		return modify;
	}

	/**
	 * 查询数据是否存在
	 * @param searchSql
	 * @param objects
	 * @return
	 */
	public boolean exists(String searchSql, Object...args) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(searchSql);
			setingParameter(preparedStatement, args);
			resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			release(resultSet, preparedStatement, connection);
		}
		return result;
	}
	
	/**
	 * 根据属性查询数据
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String, Object> findByAttribute(String sql,Object...args){
		Map<String, Object> result = new HashMap<String, Object>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 获取连接
			connection = getConnection();
			// 创建执行预编译SQL对象
			preparedStatement = connection.prepareStatement(sql);
			// 设置设置参数
			setingParameter(preparedStatement, args);
			// 执行SQL
			resultSet = preparedStatement.executeQuery();
			// 解析结果
			List<Map<String,Object>> resultList = analysisResult(resultSet);
			// 如果有结果则返回第一条记录
			if(resultList.size()>0){
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 释放连接
			release(resultSet, preparedStatement, connection);
		}
		return result;
	}
	
	/**
	 * 解析结果集
	 * @param resultSet
	 * @return
	 */
	private List<Map<String,Object>> analysisResult(ResultSet resultSet) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			try{
				// 解析查询结果
				while (resultSet.next()) {
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int colomuCount = resultSetMetaData.getColumnCount();
					// 循环所有列并获取列值
					Map<String,Object> columnMap = new HashMap<String,Object>(); 
					for(int i = 0; i < colomuCount; i++){
						String columuName = resultSetMetaData.getColumnName(i+1);
						Object columuValule = resultSet.getObject(columuName);
						columnMap.put(columuName, columuValule);
					}
					// 将一行数据添加到返回结果集
					resultList.add(columnMap);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return resultList;
	}

	/**
	 * 设置参数值
	 * @param preparedStatement
	 * @param args
	 */
	private void setingParameter(PreparedStatement preparedStatement, Object...args) {
		try{
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i+1, args[i]);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放连接
	 * @param preparedStatement
	 * @param connection
	 */
	private void release(PreparedStatement preparedStatement, Connection connection) {
		release(null,preparedStatement,connection);
	}

	/**
	 * 释放连接
	 * @param resultSet
	 * @param preparedStatement
	 * @param connection
	 */
	private void release(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		try {
			if(null != resultSet){
				resultSet.close();
			}
			if(null != preparedStatement){
				preparedStatement.close();
			}
			if(null != connection){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
