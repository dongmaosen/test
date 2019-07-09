package org.rookie.test.rhine;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.bool.rhine.RhineBootStrap;
import org.junit.Test;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class RhineInitTest {
	
	@Test
	public void rhineInitTest() {
		Future<String> future = RhineBootStrap.start();
		while (!future.isDone()) {
			try {
				future.get();
			} catch (ExecutionException e) {
				
			} catch (InterruptedException e) {
			
			}
		}
	}
}
