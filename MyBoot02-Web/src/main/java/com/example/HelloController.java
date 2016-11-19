package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	
	
	@RequestMapping("/")
	public String index(ModelMap map){
		// 加入一个属性，用来在模板中读取
		map.addAttribute("host","https://aabbcc5050.github.io/");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
}
