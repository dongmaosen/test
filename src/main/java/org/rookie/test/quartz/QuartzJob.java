package org.rookie.test.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2018
 * 
 */
public class QuartzJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("QuartzJob Test : " + new Date().getTime());
	}

}
