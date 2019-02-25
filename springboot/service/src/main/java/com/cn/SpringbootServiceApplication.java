package com.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author yshh44
 */
@SpringBootApplication
// 拦截器
@ServletComponentScan("com.cn.jwt.configuration")
@MapperScan("com.cn.jwt.mapper")
public class SpringbootServiceApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {

		SpringApplication.run(SpringbootServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootApplication.class);
	}
}
