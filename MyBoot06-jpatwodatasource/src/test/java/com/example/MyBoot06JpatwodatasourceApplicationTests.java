package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.p.UserRepository;
import com.example.p.Users;
import com.example.s.Message;
import com.example.s.MessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot06JpatwodatasourceApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	
	@Test
	public void contextLoads() {
		userRepository.save(new Users("aaa", 10));
		userRepository.save(new Users("bbb", 20));
		userRepository.save(new Users("ccc", 30));
		userRepository.save(new Users("ddd", 40));
		userRepository.save(new Users("eee", 50));
		Assert.assertEquals(5, userRepository.findAll().size());
		messageRepository.save(new Message("o1", "aaaaaaaaaa"));
		messageRepository.save(new Message("o2", "bbbbbbbbbb"));
		messageRepository.save(new Message("o3", "cccccccccc"));
		Assert.assertEquals(3, messageRepository.findAll().size());
	}

}
