package com.veasymall.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.Result;
import com.veasymall.api.pojo.User;
import com.veasymall.api.utils.JsonUtils;
import com.veasymall.api.utils.RedisOperator;

@RestController
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private StringRedisTemplate strRedis;

	@Autowired
	private RedisOperator redis;

	@RequestMapping("/test")
	public Result test() {

		strRedis.opsForValue().set("veasymall-cache", "hello veasymall", 30, TimeUnit.SECONDS);

		User user = new User();

		user.setName("marco");
		user.setPassword("124567");
		user.setBirthday(new Date());

		strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));

		User jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), User.class);

		return Result.ok(jsonUser);

	}

	@RequestMapping("/getJsonList")
	public Result getJsonList() {

		User user1 = new User();

		user1.setName("marco1");
		user1.setPassword("124567");
		user1.setBirthday(new Date());

		User user2 = new User();

		user2.setName("marco2");
		user2.setPassword("124567");
		user2.setBirthday(new Date());

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);

		String userListJson = redis.get("json:info:userlist");

		List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);

		return Result.ok(userListBorn);
	}

}
