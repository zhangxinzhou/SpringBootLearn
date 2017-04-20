package com.example.domain;



/*
 * 科目表,暂时不考虑
 */
public class Subject {


	private Long id;
	private String name;
	private String remarks;
	
	
	
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", remarks=" + remarks + "]";
	}
	
	
}
