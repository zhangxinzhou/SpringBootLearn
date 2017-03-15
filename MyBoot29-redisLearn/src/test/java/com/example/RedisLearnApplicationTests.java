package com.example;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLearnApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	RedisTemplate<String, User> userRedisTemplate;
	
	@Before
	public void init(){
		//清空所有数据
		stringRedisTemplate.getConnectionFactory().getConnection().flushAll();
	}
	
	@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("abc", "123");
		String value=stringRedisTemplate.opsForValue().get("abc");
		System.out.println(value);
	}
	
	@Test
	public void testss(){
		redisTemplate.opsForValue().set("String", "String");
		String value=redisTemplate.opsForValue().get("String");
		System.out.println(value);
	}

	@Test
	public void test(){
		User user01=new User(1001L, "小白", "女", "英雄");
		User user02=new User(1002L, "小黑", "女", "小偷");
		User user03=new User(1003L, "小7", "男", "警察");
		User user04=new User(1004L, "小8", "男", "坏蛋");
		userRedisTemplate.opsForValue().set(user01.getName(), user01);
		userRedisTemplate.opsForValue().set(user02.getName(), user02);
		userRedisTemplate.opsForValue().set(user03.getName(), user03);
		userRedisTemplate.opsForValue().set(user04.getName(), user04);
		
		User u= userRedisTemplate.opsForValue().get("小白");
		userRedisTemplate.expire("小白", 10L, TimeUnit.SECONDS);//十秒后过期		
		System.out.println(u);
	}
	
	
}
