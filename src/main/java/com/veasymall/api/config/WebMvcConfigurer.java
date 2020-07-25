package com.veasymall.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.veasymall.api.interceptor.OneInterceptor;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {

		/**
		 * 拦截器按照顺序执行
		 */
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**").addPathPatterns("/two/**");
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");

		// 拦截所有请求
		// registry.addInterceptor(new OneInterceptor()).addPathPatterns("/*/**");

		super.addInterceptors(registry);
	}

}
