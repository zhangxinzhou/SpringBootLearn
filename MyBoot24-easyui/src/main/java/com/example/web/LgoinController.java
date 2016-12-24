package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LgoinController {

	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "logout";
	}
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
}
