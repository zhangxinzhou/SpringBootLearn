package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class MyBoot25SpringStateMachineApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MyBoot25SpringStateMachineApplication.class, args);
	}
	
	@Autowired
	public StateMachine<States, Events> stateMachine;

	@Override
	public void run(String... arg0) throws Exception {		
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.REVEIVE);
	}
	
	
	
}
