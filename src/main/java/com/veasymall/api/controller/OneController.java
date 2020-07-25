package com.veasymall.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.Result;

@RestController
@RequestMapping("two")
public class OneController {

	@RequestMapping("/test")
	public Result test() {

		return Result.ok();
	}
}
