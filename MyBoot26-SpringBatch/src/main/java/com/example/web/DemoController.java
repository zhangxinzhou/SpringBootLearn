package com.example.web;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Autowired
	JobLauncher jobLuancher;
	@Autowired
	Job importJob;
	public JobParameters jobParameter;
	
	@RequestMapping("/imp")
	public String imp(String fileName) throws Exception{
		String path=fileName+".csv";
		jobParameter=new JobParametersBuilder()
				           .addLong("time", System.currentTimeMillis())
				           .addString("input.file.name", path)
				           .toJobParameters();
		jobLuancher.run(importJob, jobParameter);
		return "ok";
	}
}
