package com.veasymall.api.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.veasymall.api.pojo.Result;

@RestControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Object defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {

		e.printStackTrace();

		if (isAjax(request)) {
			return Result.errorException(e.getMessage());
		} else {
			// 非 ajax 请求方式，则返回数据到页面进行错误信息提示
			return response;
		}

	}

	/**
	 * 判断是否 ajax 请求
	 * 
	 * @param httpRequest
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}
