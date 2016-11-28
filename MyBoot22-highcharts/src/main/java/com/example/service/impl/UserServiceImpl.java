package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.domain.Users;
import com.example.service.UserService;
import com.example.util.MyPage;
import com.example.util.SortUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserRepository getUserRepository() {
		return userRepository;
	}

	@Override
	public MyPage<Users> getConditionPage(Users u, Integer offset, Integer limit,String... order) {
		Example<Users> e=Example.of(u);
		Sort sort=SortUtil.getPage(order);
		PageRequest pageRequest=sort!=null?new PageRequest(offset/limit, limit, sort):new PageRequest(offset/limit, limit);
		Page<Users> pageU=userRepository.findAll(e, pageRequest);
		return new MyPage<>(pageU);
	}

}
