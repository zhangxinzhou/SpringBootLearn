package com.example.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<Users, Long>{

	@Cacheable(key="#p0",condition="#p0.length()<10")
	Users findByName(String name);
}
