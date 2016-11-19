package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class MyBoot09ErrorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoot09ErrorApplication.class, args);
	}
	
	@RequestMapping("/errorTest")
	public String errorTest() throws Exception{
		throw new Exception("发生错误");
	}
	
	@RequestMapping("/jsonErrorTest")
	public String jsonErrorTest() throws Exception{
		throw new Exception("发生错误2");
	}
	
	@RequestMapping("hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	
	
}
