package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //这里不一定非要是@Configuration,@Component也可以(@Configuration 已经包含了@Component)
                //个人理解,只能能把@bean注解,让spring识别到就ok
public class Task2Config {

	//这里注册bean
	@Bean
	public ScheduledTasks2 scheduledTasks2(){
		return new ScheduledTasks2();
	}
}
