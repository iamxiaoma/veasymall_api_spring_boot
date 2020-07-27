package com.veasymall.api.controller.admin;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.Result;
import com.veasymall.api.pojo.User;
import com.veasymall.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "总后台用户相关接口文档")
@RestController("admin_user")
@RequestMapping("admin/user")
public class UserController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ApiOperation(value = "分页获取用户列表")
	@RequestMapping(method = RequestMethod.GET, path = "/list")
	@ResponseBody
	public Result getUserList(@ApiParam(value = "页码", required = false) Integer page) {

		if (page == null) {
			page = 1;
		}

		int pageSize = 2;

		User user = new User();

		List<User> userList = userService.queryUserListPaged(user, page, pageSize);

		return Result.ok(userList);
	}

	@ApiOperation(value = "新增用户")
	@RequestMapping(method = RequestMethod.POST, path = "/saveUser")
	@ResponseBody
	public Result saveUser(@ApiParam(value = "用户实体", required = true) User user) throws Exception {

		user.setName("marco");
		user.setPassword("123456");
		user.setCreateTime(new Date());

		userService.saveUser(user);

		return Result.ok("保存成功");

	}

}
