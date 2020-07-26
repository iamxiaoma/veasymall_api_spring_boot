package com.veasymall.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.oas.annotations.EnableOpenApi;
// 注意要引用 tk.mybatis 的 MapperScan
import tk.mybatis.spring.annotation.MapperScan;

// 扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.veasymall.api.mapper")

// 开启定时任务
@EnableScheduling

// 开启异步调用方法
@EnableAsync

@EnableOpenApi
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApiApplication.class).run(args);
		// SpringApplication.run(ApiApplication.class, args); // 两句的写法等价
	}

}
