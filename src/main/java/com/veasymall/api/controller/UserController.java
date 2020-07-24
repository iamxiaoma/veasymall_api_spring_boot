package com.veasymall.api.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.JSONResult;
import com.veasymall.api.pojo.User;

@RestController
public class UserController {

	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	@ResponseBody
	public JSONResult index() {

		User user = new User();

		user.setName("marco");
		user.setPassword("123456");
		user.setDesc("sdfasf");
		user.setBirthday(new Date());
		user.setAccount("admin");
		user.setAge(22);

		return JSONResult.ok(user);
	}
}
