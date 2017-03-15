package com.example;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.config.ConfigBean;
import com.example.service.AnnoTestServiceBean;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot30ImportxmlApplicationTests {
	
	@Autowired
	AnnoTestServiceBean annotest;

	@Test
	public void contextLoads() {
		//获取配置
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConfigBean.class);
		System.out.println(context);
		Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
	
	}

}
