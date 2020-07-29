/**
 * @Description: TODO(描述）
 * @author machaoyi
 * @date 2020-07-30 06:35:34
 */
package com.veasymall.api.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.Result;
import com.veasymall.api.service.WebSocketService;

import io.swagger.annotations.Api;

/**
 * @author machaoyi
 *
 */
@RestController
@Api(value = "用户相关接口")
@RequestMapping("ws")
public class WebSocketController {

	@RequestMapping("/sendMsg")
	public Result sendMessage(@PathParam("merchant_id") String merchantId, @PathParam("message") String message) {

		WebSocketService.sendMessage(merchantId, message);

		return Result.ok("发送成功");

	}

}
