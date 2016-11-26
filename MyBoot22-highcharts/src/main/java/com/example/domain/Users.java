package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String sex;
	@Column
	private Integer age;
	@Column		
	private String occupation;
	
	public Users() {
		super();
	}
	
	public Users(String name, String sex, Integer age, String occupation) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.occupation = occupation;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", occupation=" + occupation
				+ "]";
	}
	
	
}
