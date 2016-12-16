package com.example.domain;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 教师
 * @author user
 *
 */
@Entity
public class Teacher {

	@Id
	@GeneratedValue  //id自增
	private Long id;
	@Column(length=10,nullable=false)
	private String name;
	@Column(length=2,nullable=false)//length指的是字节,一个汉字两个字节,所以设为2
	private String sex;
	// TemporalType.DATE表示日期 2010-02-02
	// TemporalType.TIME表示时间 09:30
	// TemporalType.TIMESTAMP表示时间戳 2010-02-02 09:30
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date birthday;
	//一个老师对应多个班级,一个班级对应多个老师,多对多的关系
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	private List<Class> classes;
	public Teacher() {
		super();
	}
	
	public Teacher(Long id, String name, String sex, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Teacher(Long id, String name, String sex, Date birthday, List<Class> classes) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.classes = classes;
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

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", classes="
				+ classes + "]";
	}
	

	
	
}
