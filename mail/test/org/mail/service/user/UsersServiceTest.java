package org.mail.service.user;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mail.entity.Users;

/**
 * 用户业务类测试
 * @author VIC
 *
 */
public class UsersServiceTest {
	
	@Test
	public void testUsersServiceExecute(){
		UsersService usersService = new UsersService();
		
//		UID        NUMBER                         用户ID   
//		UNAME      VARCHAR2(98)                   用户名   
//		UACCOUNT   VARCHAR2(98)                   用户登录账号 
//		UPASSWORD  VARCHAR2(98)                   用户登录密码 
//		USEX       VARCHAR2(8)   Y                用户性别 
//		UAGE       NUMBER        Y                用户年龄 
//		UHEAD      VARCHAR2(128) Y                用户头像 
//		UEMAIL     VARCHAR2(128) Y                用户邮箱 
//		UPHONE     NUMBER        Y                用户手机 
//		STATE      VARCHAR2(32)  Y                用户状态 
//		CREATETIME TIMESTAMP(6)  Y                注册时间
		int modify  = usersService.execute("insert into USERS values(?,?,?,?,?,?,?,?,?,?,?)", 1,"jack","jack","jack","男",29,"head.jpg","jack@163.com","1333333","enabled",new Timestamp(System.currentTimeMillis()));
		Assert.assertTrue(modify > 0);
		
	}
	
	@Test
	public void testUsersServiceQuery(){
		UsersService usersService = new UsersService();
		List<Users> users = usersService.quary("select * from users");
		System.out.println(users);
		Assert.assertTrue(users.size()>0);
		
	}

}
