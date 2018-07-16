package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ExternalTomcat3Application {

	public static void main(String[] args) {
		SpringApplication.run(ExternalTomcat3Application.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "可以使用外部tomcat启动,也可以使用springboot内置的tomcat";
	}
}
