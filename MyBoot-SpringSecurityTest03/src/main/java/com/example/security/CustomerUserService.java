package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.SysUserRepository;
import com.example.domain.SysUser;

@Service
public class CustomerUserService implements UserDetailsService{
	
	@Autowired
	private SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user=userRepository.findByUsername(username);
		if(user==null){
			throw new UsernameNotFoundException(username+" is not found");
		}
		return user;
	}

}
