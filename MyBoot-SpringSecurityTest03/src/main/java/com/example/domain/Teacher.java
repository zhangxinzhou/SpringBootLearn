package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * 教师
 * 一个老师可能教多个班级
 */
@Entity
public class Teacher {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String tecnum;
	private String sex;
	private String birthday;
	private String tchStatus;
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
	public String getTecnum() {
		return tecnum;
	}
	public void setTecnum(String tecnum) {
		this.tecnum = tecnum;
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
	public String getTchStatus() {
		return tchStatus;
	}
	public void setTchStatus(String tchStatus) {
		this.tchStatus = tchStatus;
	}

	
	
}
