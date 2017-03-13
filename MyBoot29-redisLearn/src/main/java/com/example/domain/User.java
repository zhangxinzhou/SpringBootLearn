package com.example.domain;

import java.io.Serializable;
/**
 * 实现序列化,否则无法存入redis
 * @author user
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String sex;
	private String others;
	
	
	public User() {
		super();
	}
	
	public User(Long id, String name, String sex, String others) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.others = others;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", others=" + others + "]";
	}
	
	
}
