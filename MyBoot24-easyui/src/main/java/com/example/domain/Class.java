package com.example.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 班级
 * @author user
 *
 */
@Entity
public class Class {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length=10,nullable=false)
	private String name;
	//一个班级多个学生,多个学生一个班级,一对多
	// mappedBy="class": 指明class类为双向关系维护端，负责外键的更新
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//private List<Student> students;
	//一个班级多个老师,一个老师多个班级,多对多
	//@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	//private List<Teacher> teachers;
	public Class() {
		super();
	}
	public Class(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + "]";
	}
	


	
}
