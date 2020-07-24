package com.veasymall.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("err")
public class ErrorController {

	@RequestMapping("/error")
	public String error() {

		int a = 1 / 0;

		return "error";

	}

}
