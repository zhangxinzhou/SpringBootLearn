package com.example.config;

import org.apache.tomcat.jdbc.pool.DataSource;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
@Configuration
@ImportResource(locations={"classpath:application-jdbc.xml"})
public class Config {

	
	
	//bean方式配置jdbc
	//@Bean
	DataSource dataSource(){
		DataSource ds=new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	
}
