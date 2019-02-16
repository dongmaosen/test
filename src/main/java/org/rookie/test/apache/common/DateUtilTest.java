package org.rookie.test.apache.common;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class DateUtilTest {
	@Test
	public void parseDate() {
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}
