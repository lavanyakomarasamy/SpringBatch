package com.newt.exploration.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBatchApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String[] str = { "context.xml", "job_config.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(str);
		Job job = (Job) context.getBean("firstJob");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Execution Status: " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured: "+e);
		}
	}
}
