package com.veasymall.api.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.veasymall.api.utils.JsonUtils;

@Aspect
@Component
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	@Pointcut("execution (public * com.veasymall.api.controller.*.*(..))")
	public void log() {

	}

	// 前置通知：返回任意类型，指定包路径下的任意类，任意方法，任意参数
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = attributes.getRequest();

		logger.info("url={}", request.getRequestURL());

		logger.info("method={}", request.getMethod());

		logger.info("ip={}", request.getRemoteAddr());

		logger.info("class_method={}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		logger.info("args={}", JsonUtils.objectToJson(joinPoint.getArgs()));

		logger.info("before aspect");
	}

	@After("log()")
	public void doAfter() {

		logger.info("after aspect");
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object) {

		logger.info("response = {}", JsonUtils.objectToJson(object));

	}

}
