package com.veasymall.api.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.JSONResult;
import com.veasymall.api.pojo.User;
import com.veasymall.api.resource.QiniuResource;

@RestController
public class UserController {

	@Autowired
	private QiniuResource qiniuResource;

	@RequestMapping(method = RequestMethod.GET, path = "/get_user")
	@ResponseBody
	public JSONResult getUser() {

		User user = new User();

		user.setName("marco");
		user.setPassword("123456");
		user.setBirthday(new Date());
		user.setAccount("admin");

		return JSONResult.ok();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get_qiniu_resource")
	@ResponseBody
	public JSONResult getQiniuResource() {

		// 进行属性拷贝
		QiniuResource bean = new QiniuResource();
		BeanUtils.copyProperties(qiniuResource, bean);

		return JSONResult.ok(bean);
	}

}
