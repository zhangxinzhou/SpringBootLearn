package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Clazz {

	@Id
	@GeneratedValue
	private Long id;
	private String classname;
	private String gradename;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	@Override
	public String toString() {
		return "Clazz [id=" + id + ", classname=" + classname + ", gradename=" + gradename + "]";
	}
	
	
}
