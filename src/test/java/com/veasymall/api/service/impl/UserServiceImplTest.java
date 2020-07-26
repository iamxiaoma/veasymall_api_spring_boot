package com.veasymall.api.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veasymall.api.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	public void findOneTest() {

		User user = userServiceImpl.queryUserById(1);

		Assert.assertEquals(new Integer(1), user.getId());

	}

}
