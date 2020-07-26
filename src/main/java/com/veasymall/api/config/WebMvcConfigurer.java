package com.veasymall.api.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.veasymall.api.interceptor.BaseInterceptor;
import com.veasymall.api.interceptor.OneInterceptor;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//启用 swagger-ui 访问入口为： http://localhost:8080/context-path/swagger-ui/index.html
@EnableSwagger2
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

	/**
	 * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
	 */
	@Value("${swagger.restApi.enabled}")
	private Boolean enable;

	/**
	 * 项目应用名
	 */
	@Value("${application.name}")
	private String applicationName;

	/**
	 * 项目版本信息
	 */
	@Value("${application.version}")
	private String applicationVersion;

	/**
	 * 项目描述信息
	 */
	@Value("${application.description}")
	private String applicationDescription;

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public Docket createRestApi() {

		return new Docket(DocumentationType.SWAGGER_2)
				// 定义是否开启 swagger，false 为关闭，可以通过配置变量控制
				.enable(enable)
				// 将 api 的元信息设置为包含在 json ResourceListing 响应中。
				.apiInfo(apiInfo())
				// 选择哪些接口作为 swagger 的 doc 发布
				.select().apis(RequestHandlerSelectors.basePackage("com.veasymall.api.controller"))
				.paths(PathSelectors.any()).build()
				// 支持的通讯协议集合
				.protocols(newHashSet("https", "http"))
				// 授权信息设置，必要的 header token 等认证信息
				.securitySchemes(securitySchemes())
				// 授权信息全局应用
				.securityContexts(securityContexts());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("VeasyMall Api Doc")
				.description("This is a restful api document of VeasyMall").version("1.0").build();
	}

	/**
	 * 设置授权信息
	 */
	private List<SecurityScheme> securitySchemes() {
		return Collections.singletonList(new ApiKey("BASE_TOKEN", "token", "pass"));
	}

	/**
	 * 授权信息全局应用
	 */
	private List<SecurityContext> securityContexts() {
		return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(
				new SecurityReference("BASE_TOKEN", new AuthorizationScope[] { new AuthorizationScope("global", "") })))
				.build());
	}

	@SafeVarargs
	private final <T> Set<T> newHashSet(T... ts) {
		if (ts.length > 0) {
			return new LinkedHashSet<>(Arrays.asList(ts));
		}
		return null;
	}

	/**
	 * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**").excludePathPatterns("/csrf")
				.excludePathPatterns("/swagger-resources/**").excludePathPatterns("/webjars/**")
				.excludePathPatterns("/v2/**").excludePathPatterns("/doc.html");

		/**
		 * 拦截器按照顺序执行
		 */
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**").addPathPatterns("/two/**");
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
	}

	/**
	 * 允许跨域请求
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").allowCredentials(true).maxAge(3600);
	}

}
