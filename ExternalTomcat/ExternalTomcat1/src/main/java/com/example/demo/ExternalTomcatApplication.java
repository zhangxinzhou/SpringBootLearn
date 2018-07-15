package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ExternalTomcatApplication extends SpringBootServletInitializer {

	// 启动类继承SpringBootServletInitializer重写方法configure指向原启动类
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ExternalTomcatApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExternalTomcatApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "可以使用外部tomcat启动,但是用springboot方式启动会报错";
	}
}
