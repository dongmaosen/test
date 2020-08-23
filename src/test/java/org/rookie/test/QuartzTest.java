package org.rookie.test;

import java.util.Date;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.rookie.test.quartz.QuartzJob;
import org.rookie.test.quartz.QuartzScheduler;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2018
 * 
 */
public class QuartzTest {
	//@Test
	public void testSimpleJob() throws SchedulerException, InterruptedException {
		// 任务
		JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity("testJob", "testGroup").build();
		// 触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testJob", "testGroup")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
		Scheduler scheduler = QuartzScheduler.getScheduler();
		// 将任务及触发器放入调度器
		scheduler.scheduleJob(jobDetail, trigger);
		// 调度器开始调度任务
		scheduler.start();
		Thread.sleep(60 * 1000);
	}

	@Test
	public void testCrontabJob() throws SchedulerException, InterruptedException {
		JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("testJob", "testGroup").build();
		//当前时间10秒后开始执行
		Date startTime = DateBuilder.nextGivenSecondDate(null, 1);
		//run every 20 seconds infinite loop
		CronTrigger crontrigger = TriggerBuilder.newTrigger()
									.withIdentity("TwentySec", "testGroup").startAt(startTime)
									.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
									.build();
		Scheduler scheduler = QuartzScheduler.getScheduler();
		scheduler.scheduleJob(job, crontrigger);
		scheduler.start();
		System.out.println("开始执行任务");
		Thread.sleep(10 * 1000);
		//
		System.out.println("清除所有任务");
		scheduler.clear();
		
		Thread.sleep(1000 * 50);
	}
}
