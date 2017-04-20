package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.anno.Notes;

@Notes(author="zxz",lastmodifier="zxz",date="2017-2-15",value="首页,和mapingname测试")
@Controller
public class TestController {

	@RequestMapping({"/","/index"})
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/mm",name="nameTest")
	@ResponseBody
	public String test(ModelMap mm){
		return "hello,this is mm";
	}
	
	@Notes("测试方法")
	public void testt(String str,String xx){
		
	}
}
