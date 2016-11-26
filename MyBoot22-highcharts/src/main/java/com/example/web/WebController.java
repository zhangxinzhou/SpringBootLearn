package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

	//测试
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "test success!";
	}
	
	//首页
	@RequestMapping("/index")
	public String index(){
		return "index";
	}	
}
