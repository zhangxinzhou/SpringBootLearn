package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, Long>{

	Users findByUsername(String username);
}
