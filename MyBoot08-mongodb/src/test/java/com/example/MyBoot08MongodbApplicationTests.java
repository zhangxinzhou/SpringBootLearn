package com.example;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot08MongodbApplicationTests {

	private static Logger logger=Logger.getLogger(MyBoot08MongodbApplicationTests.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp(){
		userRepository.deleteAll();
	}
	
	@Test
	public void contextLoads() {
		// 创建三个User，并验证User总数
		userRepository.save(new Users(1L, "didi", 30));
		userRepository.save(new Users(2L, "mama", 40));
		userRepository.save(new Users(3L, "kaka", 50));
		Assert.assertEquals(3, userRepository.findAll().size());	
		logger.info(userRepository.findAll());
		
		// 删除一个User，再验证User总数
		Users u = userRepository.findOne(1L);
		userRepository.delete(u);
		Assert.assertEquals(2, userRepository.findAll().size());
		logger.info(userRepository.findAll());
		
		// 删除一个User，再验证User总数
		u = userRepository.findByUsername("mama");
		userRepository.delete(u);
		Assert.assertEquals(1, userRepository.findAll().size());
		logger.info(userRepository.findAll());		
	}

}
