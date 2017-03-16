package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SysRoleRepository;

@Service
public class RoleService {

	@Autowired
	SysRoleRepository roleRepository;
	
	public SysRoleRepository getRoleRepository(){
		return roleRepository;
	}
}
