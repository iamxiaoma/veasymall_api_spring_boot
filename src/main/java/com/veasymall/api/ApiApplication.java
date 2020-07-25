package com.veasymall.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.veasymall.api.mapper")
public class ApiApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApiApplication.class).run(args);
		// SpringApplication.run(ApiApplication.class, args); // 两句的写法等价
	}

}
