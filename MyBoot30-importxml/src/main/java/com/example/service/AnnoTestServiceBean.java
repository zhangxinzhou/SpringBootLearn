package com.example.service;

import org.springframework.stereotype.Service;

/*
 * 用@Service注解成为spring管理的bean
 */
@Service
public class AnnoTestServiceBean {

	public String test(){
		return "该bean使用了@Service";
	}
}
