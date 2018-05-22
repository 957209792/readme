package org.mail.jdbc;

import java.util.Properties;

import org.mail.util.LoggerUtil;

/**
 * JDBC 配置类(单列模式)
 * @author VIC
 *
 */
public class JdbcConfig {
	
	private String driver;
	private String url;
	private String username;
	private String password;
	
	private static JdbcConfig jdbcConfig;
	private LoggerUtil logger = LoggerUtil.getLogger(JdbcConfig.class);
	
	private JdbcConfig(){
		// 初始化数据库连接信息
		try {
			Properties properties = new Properties();
			properties.load(JdbcConfig.class.getResourceAsStream("/jdbc.properties"));
			driver=properties.getProperty("jdbc.driver");
			url=properties.getProperty("jdbc.url");
			username=properties.getProperty("jdbc.username");
			password=properties.getProperty("jdbc.password");
			logger.debug("初始化数据库连接信息完成");
		} catch (Exception e) {
			logger.error("初始化数据库连接信息异常");
			e.printStackTrace();
		}
		
		try {
			// 加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.debug("加载数据库驱动完成");
		} catch (Exception e) {
			logger.error("加载数据库驱动异常");
			e.printStackTrace();
		}
	}
	
	public static synchronized JdbcConfig getInstance(){
		if(null == jdbcConfig){
			jdbcConfig = new JdbcConfig();
		}
		return jdbcConfig;
	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JdbcConfig [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ "]";
	}

}
