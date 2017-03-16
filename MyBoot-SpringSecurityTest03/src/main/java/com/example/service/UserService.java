package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SysUserRepository;

@Service
public class UserService {

	@Autowired
	SysUserRepository userRepository;
	
	public SysUserRepository getUserRepository(){
		return userRepository;
	}
}
