package com.example;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBoot21RabbitMq1Application implements CommandLineRunner{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(MyBoot21RabbitMq1Application.class, args);
	}
	
	@Bean
	public Queue wiselyQueue(){
		return new Queue("my-quenue");
	}

	@Override
	public void run(String... arg0) throws Exception {
		rabbitTemplate.convertAndSend("my-quenue","来自rabbitmq的问候");
		
	}
	
	
}
