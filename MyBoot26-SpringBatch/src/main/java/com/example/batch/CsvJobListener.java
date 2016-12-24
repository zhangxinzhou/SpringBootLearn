package com.example.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CsvJobListener implements JobExecutionListener{

	private Logger log=Logger.getLogger(getClass());
	private Long startTime;
	private Long endTime;
	
	@Override
	public void afterJob(JobExecution arg0) {
		endTime=System.currentTimeMillis();
		log.info("afterJob:任务结束");
		log.info("afterJob:耗时:"+(endTime-startTime)+"ms");
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		startTime=System.currentTimeMillis();
		log.info("afterJob:任务开始");
	}

}
