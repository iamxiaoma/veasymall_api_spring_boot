package com.veasymall.api.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.Result;
import com.veasymall.api.pojo.User;
import com.veasymall.api.resource.QiniuResource;
import com.veasymall.api.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private QiniuResource qiniuResource;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, path = "/list")
	@ResponseBody
	public Result getUserList(Integer page) {

		if (page == null) {
			page = 1;
		}

		int pageSize = 2;

		User user = new User();

		List<User> userList = userService.queryUserListPaged(user, page, pageSize);

		return Result.ok(userList);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get_user")
	@ResponseBody
	public Result getUser() {

		User user = new User();

		user.setName("marco");
		user.setPassword("123456");
		user.setBirthday(new Date());
		user.setAccount("admin");

		return Result.ok(user);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get_qiniu_resource")
	@ResponseBody
	public Result getQiniuResource() {

		// 进行属性拷贝
		QiniuResource bean = new QiniuResource();
		BeanUtils.copyProperties(qiniuResource, bean);

		return Result.ok(bean);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/saveUser")
	@ResponseBody
	public Result saveUser() throws Exception {

		// log.info("保存用户，当前时间：{}，操作人：{}", new Date(), "Marco");

		// String userId = sid.nextShort();

		User user = new User();
		user.setName("marco");
		user.setPassword("123456");
		user.setCreateTime(new Date());

		userService.saveUser(user);

		return Result.ok("保存成功");

	}

}
