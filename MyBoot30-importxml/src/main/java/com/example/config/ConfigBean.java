package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.example.service.BeanTestServiceBean;

@Configuration
@ImportResource(locations={"classpath:application-bean.xml"})
//@ComponentScan("com.example.service") 没有这个会影响MyBoot30ImportxmlApplicationTests测试结果,原因不明
//locations = {"file:d:/test/application-bean1.xml"};另一种方式
public class ConfigBean {

	
	@Bean
	public BeanTestServiceBean beanTestServiceBean(){
		return new BeanTestServiceBean();
	}
}
