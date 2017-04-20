package com.example.service;

/*
 * 在config中@bean的方式,弄成spring管理的bean
 */
public class BeanTestServiceBean {


	public String test(){
		return "在config中@bean注册成spring的bean";
	}
}
