package org.rookie.test.distribute;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 *
 *
 * Author: 不二   
 *
 * Copyright @ 2018
 * 
 */
public class ZKDistributeLock implements Lock, Watcher{

	public void lock() {
		// TODO Auto-generated method stub

	}

	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	public void unlock() {
		// TODO Auto-generated method stub

	}

	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
