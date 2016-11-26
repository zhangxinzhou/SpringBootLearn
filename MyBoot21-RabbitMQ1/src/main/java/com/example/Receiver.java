package com.example;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@RabbitListener(queues="my-quenue")
	public void receiveMessage(String message){
		System.out.println("Received<"+message+">");
	}
}
