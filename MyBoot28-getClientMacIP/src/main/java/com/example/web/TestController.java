package com.example.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/doTest")
	@ResponseBody
	public List<Map<String, Object>> doTest(){
		return testService.getMoreRequestMapping(WebController.class);
	}
	
	@RequestMapping("/doTest2")
	@ResponseBody
	public List<Map<String, Object>> doTest2(){
		return testService.getMoreRequestMapping2(WebController.class);
	}
	
}
