package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/*
 * 一个学生只能属于一个班级
 */
@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String stunum;
	private String sex;
	private String birthday;
	private String stuStatus;
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
	public String getStunum() {
		return stunum;
	}
	public void setStunum(String stunum) {
		this.stunum = stunum;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStuStatus() {
		return stuStatus;
	}
	public void setStuStatus(String stuStatus) {
		this.stuStatus = stuStatus;
	}

	

	
	
	
}
