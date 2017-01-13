package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/rm1","/rm2"})
public class RMtestController {

	private final String backIndex="<a href=\"/index\">返回首页</a><br/>";
	
	@RequestMapping({"/path1","path2"})
	@ResponseBody
	public String Test01(){
		return backIndex+"<span style=\"color:red;\">@RequestMapping测试01<span>";
	}
	
	@RequestMapping({"/path"})
	@ResponseBody
	public String Test02(){
		return backIndex+"<span style=\"color:green;\">@RequestMapping测试02<span>";
	}
	
}
