package org.rookie.test.parallel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class Allocator {
	
	private List<Object> als = new ArrayList<Object>();
	//
	public synchronized void apply(Object from, Object to) {
		while (als.contains(from) || als.contains(to)) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		als.add(from);
		als.add(to);
	}
	//
	public synchronized void free(Object from, Object to) {
		als.remove(from);
		als.remove(to);
		notifyAll();
	}
	
	private static volatile Allocator instance;
	
	public static synchronized Allocator getInstance() {
		if (instance == null) {
			instance = new Allocator();
		}
		return instance;
	}

}
