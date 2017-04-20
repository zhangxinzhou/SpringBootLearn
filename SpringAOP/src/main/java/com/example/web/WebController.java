package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.AopTest;
import com.example.service.TestService;

@Controller
public class WebController {

	/*
	 * 如果出现异常则记录到日志
	 */
	private TestService testService;
	
	
	@RequestMapping("/ok")
	@ResponseBody
	public String ok(){
		return "i am good";
	}
	
	@RequestMapping("/notok")
	@ResponseBody
	public String notok(){
		System.out.println(10/0);
		testService.divide(10, 10);
		
		return "i am not good";
	}
		
	@RequestMapping("/annotest")
	@ResponseBody
	@AopTest
	public String annotest(){
		return "annotest";
	}
	
	@RequestMapping("/exectest")
	@ResponseBody
	public String exectest(){
		return "exectest";
	}
	
	@RequestMapping("/methodtest")
	@ResponseBody
	public String methodtest(){
		return "methodtest";
	}
}
