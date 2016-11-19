package com.example;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot10ApplicationpropertiesApplicationTests {

	public static Logger log=Logger.getLogger(MyBoot10ApplicationpropertiesApplicationTests.class);
	
	@Autowired
	private MyProperties myProperties;
	
	//desc与desc123不需要相等
	@Value("${com.myspace.desc}")
	private String desc123;
	
	@Value("${com.myspace.value}")
	private String value;
	@Value("${com.myspace.number}")
	private Integer number;
	@Value("${com.myspace.bignumber}")
	private Long bignumber;
	@Value("${com.myspace.test1}")
	private Integer test1;
	@Value("${com.myspace.test2}")
	private Integer test2;
	
	
	//系统的也可以用,这个文件的值决定加载那个properties(dev,prod,test)文件
	@Value("${spring.profiles.active}")
	private String profiles;
	
	@Test
	public void contextLoads() {
		
		Assert.assertEquals(myProperties.getName(), "zxz"+profiles);
		Assert.assertEquals(myProperties.getAge().intValue(), 100);
		
		log.info(myProperties.getName());
		log.info(myProperties.getAge());
		log.info(desc123);
		log.info(value);
		log.info(number);
		log.info(bignumber);
		log.info(test1);
		log.info(test2);
	}

}
