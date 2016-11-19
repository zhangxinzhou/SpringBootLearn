package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  //让@Async注解能够生效
public class MyBoot12AsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoot12AsyncApplication.class, args);
	}
}
