package org.mail.jdbc;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试JDBC配置加载
 * @author VIC
 *
 */
public class JdbcConfigTest {
	
	@Test
	public void testJdbcConfig(){
		JdbcConfig jdbcConfig = JdbcConfig.getInstance();
		Assert.assertNotNull(jdbcConfig);
	}

}
