package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.AnnoTestServiceBean;
import com.example.service.BeanTestServiceBean;
import com.example.service.NoTestServiceBean;
import com.example.service.XmlTestServiceBean;

@Controller
public class WebController {
	
	@Autowired
	AnnoTestServiceBean annobean;
	
	@Autowired
	BeanTestServiceBean beantest;
	
	//项目启动的时候就报错,所以无法用这种方式来测试
	//@Autowired
	NoTestServiceBean nobean;
	
	@Autowired
	XmlTestServiceBean xmlbean;
	

	@RequestMapping
	@ResponseBody
	public String index(){
		return "this is index";
	}
	
	@RequestMapping("/anno")
	@ResponseBody
	public String annotest(){
		return annobean.test();
	}
	
	@RequestMapping("/bean")
	@ResponseBody
	public String beantest(){
		return beantest.test();
	}
	
	@RequestMapping("/no")
	@ResponseBody
	public String notest(){
		//没有注册成spring的bean,无法注入,所以new一个
		nobean=new NoTestServiceBean();
		return nobean.test();
	}
	
	@RequestMapping("/xml")
	@ResponseBody
	public String xmltest(){
		return xmlbean.test();
	}
}
