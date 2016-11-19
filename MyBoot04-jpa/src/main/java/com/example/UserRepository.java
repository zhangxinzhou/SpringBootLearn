package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long>{

	
	Users findByName(String name);
	
	Users findByNameAndAge(String name,Integer age);
	
	@Query("from Users u where u.name=:name")
	Users findUser(@Param("name") String name);


}
