package com.example.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class SysUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String password;
	private boolean enable;
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	private Set<SysRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {//帐户是否已过期;
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {//帐户是否已被锁定;
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {//帐户凭据是否(密码)已过期;
		return true;
	}

	@Override
	public boolean isEnabled() {//帐户是否可用.
		return this.enable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
