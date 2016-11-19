package com.example;





import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot07RedisApplicationTests {

	public static Logger logger=Logger.getLogger(MyBoot07RedisApplicationTests.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, Users> redisTemplate;
	
	@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testUsers(){
		Users user=new Users("超人",20);
		redisTemplate.opsForValue().set(user.getUsername(), user);
		
		user = new Users("蝙蝠侠", 30);
		redisTemplate.opsForValue().set(user.getUsername(), user);
		
		user = new Users("蜘蛛侠", 40);
		redisTemplate.opsForValue().set(user.getUsername(), user);
		

		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
		
		logger.info(redisTemplate.opsForValue().get("超人"));
		logger.info(redisTemplate.opsForValue().get("蝙蝠侠"));
		logger.info(redisTemplate.opsForValue().get("蜘蛛侠"));
	}
}
