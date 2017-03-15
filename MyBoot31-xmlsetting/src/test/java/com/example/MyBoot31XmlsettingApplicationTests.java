package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot31XmlsettingApplicationTests {
	
	@Autowired
	JdbcTemplate jdbc;

	@Test
	public void contextLoads() {
		String sql="select NOW()";
		String now=jdbc.queryForObject(sql,String.class);
		System.out.println("NOW : "+now);
	}

}
