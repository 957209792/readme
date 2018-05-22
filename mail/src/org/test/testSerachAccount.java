package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.mail.entity.Users;
import org.mail.service.user.UsersService;

public class testSerachAccount {
	@Test
	public void SearchAccount(){
		UsersService usersService = new UsersService();
		Users users = usersService.searchUserByAccount("shiqi");
		System.out.println(users);
		Assert.assertTrue(null!=users);
	}
}
