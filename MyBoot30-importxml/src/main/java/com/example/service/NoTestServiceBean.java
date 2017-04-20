package com.example.service;

/*
 * 既不用注解也不用xml,应该会注入失败
 */
public class NoTestServiceBean {

	public String test(){
		return "既不用注解也不用xml";
	}
}
