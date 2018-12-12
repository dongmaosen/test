package org.rookie.test.quartz;
/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2018
 * 
 */

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {
	public static Scheduler getScheduler() throws SchedulerException {
		return new StdSchedulerFactory().getScheduler();
	}
}
