package com.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConroller {

	@RequestMapping(value="/hello",method=RequestMethod.GET)
	@ResponseBody
	public String hello(@RequestParam String name){
		return "hello "+name;
	}
	
	@RequestMapping(value="/hello2",method=RequestMethod.GET)
	@ResponseBody
	public String hello2(@RequestParam String name){
		return "hello2 "+name;
	}
}
