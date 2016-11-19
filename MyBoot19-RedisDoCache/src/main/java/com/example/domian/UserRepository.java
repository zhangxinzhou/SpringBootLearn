package com.example.domian;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<Users, Long>{

    @Cacheable(key = "#p0")
    Users findByName(String name);

    
    //更新age的时候，通过@CachePut来让数据更新操作同步到缓存中
    @SuppressWarnings("unchecked")
	@CachePut(key = "#p0.name")
    Users save(Users user);
}
