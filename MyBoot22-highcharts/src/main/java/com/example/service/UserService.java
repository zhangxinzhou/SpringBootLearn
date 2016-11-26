package com.example.service;

import com.example.dao.UserRepository;
import com.example.domain.Users;
import com.example.util.MyPage;

public interface UserService {

	public UserRepository getUserRepository();
	
	public MyPage<Users> getConditionPage(Users u,Integer offset,Integer limit,String... order);
}
