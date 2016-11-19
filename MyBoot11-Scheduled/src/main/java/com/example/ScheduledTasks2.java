package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;


//这里不要@Component,这样的话就需要在application中注册这个bean
public class ScheduledTasks2 {

	public static Logger log=Logger.getLogger(ScheduledTasks2.class);
	
	public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	/*@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
	  @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
	  @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
	  @Scheduled(cron="") ：通过cron表达式定义规则*/
	
	
	@Scheduled(fixedRate=5000)
	public void reportCurrentTime(){
		log.info("现在时间2:"+sdf.format(new Date()));
	}
	
	
	
	//每天16点21分执行
	@Scheduled(cron="0 21 16 ? * *")
	public void fixTimeExecution(){
		log.info("指定时间2:"+sdf.format(new Date()));
	}
	
}
