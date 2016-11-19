package com.example;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.domian.UserRepository;
import com.example.domian.Users;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot19RedisDoCacheApplicationTests {

	private Logger log=Logger.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CacheManager cacheManager;
	
	@Before
	public void before(){
		userRepository.save(new Users("AAA",10));
	}
	
	@Test
	public void contextLoads() {
		Users u1=userRepository.findByName("AAA");
		log.info("第一次查询"+u1.getAge());
		
		Users u2=userRepository.findByName("AAA");
		log.info("第二次查询"+u2.getAge());
		
		
		u1.setAge(20);
		userRepository.save(u1);
		Users u3=userRepository.findByName("AAA");
		log.info("第三次查询"+u3.getAge());
		
		
		

		if(cacheManager!=null){
			log.info("cacheName:"+cacheManager.getCacheNames());
		}else{
			log.info("cacheName is null");
		}	
	}

}
