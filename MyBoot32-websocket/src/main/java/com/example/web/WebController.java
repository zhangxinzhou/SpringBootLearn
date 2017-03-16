package com.example.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.WiselyMessage;
import com.example.domain.WiselyResponse;

@Controller
public class WebController {

	@RequestMapping
	public String index(){
		return "index";
	}
	
	@RequestMapping("/ws")
	public String ws(){
		return "ws";
	}
	
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message){
		return new WiselyResponse("welcome,"+message.getName()+"!");
	}
}
