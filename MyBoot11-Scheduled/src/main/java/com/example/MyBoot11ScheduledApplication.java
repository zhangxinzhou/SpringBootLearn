package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyBoot11ScheduledApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBoot11ScheduledApplication.class, args);
	}
	
	
	
	@Bean
	public ScheduledTasks3 scheduledTasks3(){
		return new ScheduledTasks3();
	}
	
}
