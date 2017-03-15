package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/*
 * 学生
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
	
	/*
	 * 多个学生对应一个班级
	 */
	@ManyToOne
	private Clazz clazz;
	
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
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", stunum=" + stunum + ", sex=" + sex + ", birthday=" + birthday
				+ ", stuStatus=" + stuStatus + ", clazz=" + clazz + "]";
	}

	

	
	
	
}
