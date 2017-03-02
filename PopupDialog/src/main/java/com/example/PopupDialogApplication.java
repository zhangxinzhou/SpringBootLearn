package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class PopupDialogApplication implements ErrorController{

	public static void main(String[] args) {
		SpringApplication.run(PopupDialogApplication.class, args);
	}
	
	private final static String ERROR_PATH="error";
	
	@Override
	public String getErrorPath() {//只有一个地址,那就是主页,如果输错了,还是返回主页
		return ERROR_PATH;
	}
	
	@RequestMapping({"/","/index"})
	public String index(){
		return "index";
	}
	
	@RequestMapping(value=ERROR_PATH)
	public String error(){
		return "index";
	}


}
