package com.example.csvdemo2;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration

public class BatchSchedulerConfig {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	

@Scheduled(cron="0 0/3 * 1/1 * ?")
public void jobScheduled() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
	File file = new File("C:/Users/ELCOT/Desktop/csvdemo2/src/main/resources/fiveLakhs.csv");
	
	System.err.println(file.exists());
	if(file.exists()) {
	Map<String,JobParameter>maps = new HashMap<>();
	maps.put("time", new JobParameter(System.currentTimeMillis()));
	JobParameters parameters=new JobParameters(maps);
	
	JobExecution jobExecution =  jobLauncher.run(job, parameters);
	System.out.println("JOB EXECUTION :" + jobExecution.getStatus());
	if(!jobExecution.getStatus().isUnsuccessful()) {
		file.delete();
		System.err.println("DELETED");
	}
	}
//	return jobExecution.getStatus();
	
}
}
