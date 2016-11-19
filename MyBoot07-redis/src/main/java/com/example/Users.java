package com.example;

import java.io.Serializable;

public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Integer age;
	public Users() {
		super();
	}
	public Users(String username, Integer age) {
		super();
		this.username = username;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Users [username=" + username + ", age=" + age + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
