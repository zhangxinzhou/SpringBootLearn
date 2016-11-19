package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(String name, Integer age) {
		String sql="insert into USERS(NAME, AGE) values(?, ?)";
		jdbcTemplate.update(sql,name,age);
	}

	@Override
	public void deleteByName(String name) {
		String sql="delete from USERS where NAME = ?";
		jdbcTemplate.update(sql,name);		
	}

	@Override
	public Integer getAllUsers() {
		String sql="select count(1) from USERS";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public void deleteAllUser() {
		String sql="delete from USERS";
		jdbcTemplate.update(sql);
	}
	
	
}
