package com.example.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Configuration
public class MyErrorController implements ErrorController{
	
	private final static String ERROR_PATH="error";
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {    
	    return new EmbeddedServletContainerCustomizer(){        
	         @Override        
	         public void customize(ConfigurableEmbeddedServletContainer container) {
	        	//ErrorPage(HttpStatus status, String path),path指的是@RequestMapping(value)中的值
	        	//container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
	        	container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
	            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));            	                    
	        }    
	    };
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}	
	@RequestMapping(value=ERROR_PATH)
	public String handleError(){
		return "/error";
	}
	//下面也可以加上@responseBody,返回json类型的数据增加用户体验
	@RequestMapping("/500")
	public String handle500(){
		return "/500";
	}
	@RequestMapping("/404")
	public String handle404(){
		return "/404";
	}
	@RequestMapping("/403")
	public String handle403(){
		return "/403";
	}	
	
}
