package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
/*
 * 角色表
 */
@Entity
public class SysRole implements GrantedAuthority{	

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private String rolename;

	@Override
	public String getAuthority() {
		return this.rolename;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
