package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(ModelMap map,String error){
		if(error!=null){
			map.put("msg", "登录失败,用户名或密码错误!");
		}
		return "login";
	}
		
}
