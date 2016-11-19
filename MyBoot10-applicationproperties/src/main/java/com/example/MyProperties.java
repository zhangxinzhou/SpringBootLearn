package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyProperties {

	@Value("${com.myspace.name}")
	private String name;
	@Value("${com.myspace.age}")
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "MyProperties [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
