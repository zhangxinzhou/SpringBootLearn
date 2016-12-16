package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 角色
 * @author user
 *
 */
@Entity
public class SysRole {

	@Id
	private Long id;
	private String name;
	
	
	public SysRole() {
		super();
	}
	
	public SysRole(Long id, String name) {
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
		return "SysRole [id=" + id + ", name=" + name + "]";
	}
	
	
}
