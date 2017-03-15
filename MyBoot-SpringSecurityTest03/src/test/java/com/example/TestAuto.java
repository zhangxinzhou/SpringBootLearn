package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.ClazzRepository;
import com.example.service.ClazzService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuto {

	@Autowired
	ClazzService clazzService;
	
	@Autowired
	ClazzRepository clazzRepository;
	
	@Test
	public void test(){

		long count=clazzService.getClazzRepository().count() ;
		System.out.println(count);
		System.out.println(clazzRepository==clazzService.getClazzRepository());
	}
	
}
