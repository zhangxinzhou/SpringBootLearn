package com.example.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 学生
 * @author user
 *
 */
@Entity
public class Student {

	@Id
	@GeneratedValue  //id自增
	private Long id;
	@Column(length=10,nullable=false)
	private String name;
	@Column(length=2,nullable=false)//length指的是字节,一个汉字两个字节,所以设为2
	private String sex;
	//TemporalType.DATE表示日期 2010-02-02 
	//TemporalType.TIME表示时间 09:30 
	//TemporalType.TIMESTAMP表示时间戳 2010-02-02  09:30 
	@Column(nullable=true)
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	//一个学生对应一个班级,一个班级多个学生,多对一
	//optional=true：可选，表示此对象可以没有，可以为null；false表示必须存在
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},optional=true)
	@JoinColumn(name="classid")
	private Class cla;

	public Student() {
		super();
	}
	
	public Student(Long id, String name, String sex, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Student(Long id, String name, String sex, Date birthday, Class cla) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.cla = cla;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Class getCla() {
		return cla;
	}

	public void setCla(Class cla) {
		this.cla = cla;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", cla=" + cla
				+ "]";
	}
	
	
	
}
