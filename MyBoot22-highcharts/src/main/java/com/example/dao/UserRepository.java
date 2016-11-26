package com.example.dao;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	
	@Query("select u from Users u")
	public List<Map<String, Object>> getUL();
	
}
