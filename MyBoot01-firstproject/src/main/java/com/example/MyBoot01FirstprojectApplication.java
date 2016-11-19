package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class MyBoot01FirstprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoot01FirstprojectApplication.class, args);
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
}
